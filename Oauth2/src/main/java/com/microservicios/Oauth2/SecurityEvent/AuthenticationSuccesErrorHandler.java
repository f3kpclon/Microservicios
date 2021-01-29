package com.microservicios.Oauth2.SecurityEvent;

import com.commons.user.Entities.User;
import com.microservicios.Oauth2.Service.UserService;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationSuccesErrorHandler implements AuthenticationEventPublisher {
    private Logger logger = LoggerFactory.getLogger(AuthenticationSuccesErrorHandler.class);

    @Autowired
    private UserService userService;

    @Override
    public void publishAuthenticationSuccess(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        logger.info("Success Login: "+userDetails.getUsername());
        logger.info(String.format("USERNAME: %s",authentication.getName()));

        try{
        User usuario = userService.findByUserName(authentication.getName());
        logger.info("INTENTOS: "+ usuario.getTries());
        if (usuario.getTries() != null && usuario.getTries()>0){
            usuario.setTries(0);
            userService.update(usuario,usuario.getId());
            logger.info("INTENTOS: "+ usuario.getTries());
        }
        }catch (FeignException ex){
            logger.error(String.format("El usuario %s no existe", authentication.getName()));
        }
    }

    @Override
    public void publishAuthenticationFailure(AuthenticationException e, Authentication authentication) {
        logger.info("Error Login: "+ e.getMessage());

        try{
            User usuario = userService.findByUserName(authentication.getName());
            if (usuario.getTries() == null){
                usuario.setTries(0);
                logger.info("INTENTO Nª"+usuario.getTries());
            }
            usuario.setTries(usuario.getTries()+1);
            logger.info("INTENTO Nª"+usuario.getTries());
            if (usuario.getTries() >= 3) {
                logger.info("USERNAME: " + authentication.getName());
                logger.info("INTENTO Nª"+usuario.getTries());
                logger.error(String.format("El usuario %s deshabilitado por máximo de intentos",authentication.getName()));
                usuario.setEnabled(false);

            }
            userService.update(usuario,usuario.getId());
        }catch (FeignException ex){

            logger.error(String.format("El usuario %s no existe", authentication.getName()));
        }
    }
}
