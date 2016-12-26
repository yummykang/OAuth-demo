package me.yummykang;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.Map;

@EnableAutoConfiguration
@Configuration
@EnableOAuth2Sso
@RestController("/")
@Order(6)
public class OauthServerClientApplication {

	@RequestMapping("/")
	public String home(Principal user) {
		return "Hello " + user.getName();
	}

	@RequestMapping({"/user", "/me"})
	public Map<String, String> user(Principal principal) {
		Map<String, String> map = new LinkedHashMap<>();
		map.put("map", principal.getName());
		return map;
	}

	@RequestMapping("/unauthenticated")
	public String unauthenticated() {
		return "redirect:/?error=true";
	}

	public static void main(String[] args) {
//		SpringApplication.run(OauthServerClientApplication.class, args);
		new SpringApplicationBuilder(OauthServerClientApplication.class)
				.properties("spring.config.name=client").run(args);
	}

	@Configuration
	@EnableResourceServer
	protected static class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
		@Override
		public void configure(HttpSecurity http) throws Exception {
			http.antMatcher("/me")
					.authorizeRequests().anyRequest().authenticated();
		}
	}
}
