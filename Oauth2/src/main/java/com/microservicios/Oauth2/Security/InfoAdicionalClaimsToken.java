package com.microservicios.Oauth2.Security;

import com.commons.user.Entities.User;
import com.microservicios.Oauth2.Service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class InfoAdicionalClaimsToken implements TokenEnhancer {

    @Autowired
    private IUserService iUserService;

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {
        Map<String,Object> info = new HashMap<String, Object>();
        User userOP = iUserService.findByUserName(oAuth2Authentication.getName());
        info.put("name", userOP.getNombre());
        info.put("lastName",userOP.getApellido());
        info.put("email",userOP.getEmail());

        ((DefaultOAuth2AccessToken)oAuth2AccessToken).setAdditionalInformation(info);

        return oAuth2AccessToken;
    }
}
