package me.yummykang.oauth;

import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;

/**
 * desc the file.
 *
 * @author demon
 * @Date 2016/12/22 17:02
 */
public class ClientResources {
    @NestedConfigurationProperty
    private AuthorizationCodeResourceDetails details = new AuthorizationCodeResourceDetails();

    @NestedConfigurationProperty
    private ResourceServerProperties resource = new ResourceServerProperties();

    public AuthorizationCodeResourceDetails getClient() {
        return details;
    }

    public ResourceServerProperties getResource() {
        return resource;
    }
}
