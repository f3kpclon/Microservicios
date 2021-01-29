package com.microservicios.Oauth2.Service;

import com.commons.user.Entities.User;
import com.microservicios.Oauth2.Clients.UserFeignClient;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class UserService implements UserDetailsService,IUserService {
    private Logger log = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private UserFeignClient client;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        try {
            User usuario = client.findByUserName(username);

            List<GrantedAuthority> authorities = usuario.getRols()
                    .stream()
                    .map(rol -> new SimpleGrantedAuthority(rol.getRoles()))
                    .peek(authority -> log.info("Rol: " + authority.getAuthority()))
                    .collect(Collectors.toList());

            return new UserDetails() {
                @Override
                public Collection<? extends GrantedAuthority> getAuthorities() {
                    return authorities;
                }

                @Override
                public String getPassword() {
                    return usuario.getPassword();
                }

                @Override
                public String getUsername() {
                    return usuario.getUsername();
                }

                @Override
                public boolean isAccountNonExpired() {
                    return usuario.getEnabled();
                }

                @Override
                public boolean isAccountNonLocked() {
                    return true;
                }

                @Override
                public boolean isCredentialsNonExpired() {
                    return true;
                }

                @Override
                public boolean isEnabled() {
                    return true;
                }
            };
        } catch (FeignException e) {
            throw new UsernameNotFoundException("Error en login, no existe usuario '" + username + "'");
        }

    }

    @Override
    public User findByUserName(String username) {
        return client.findByUserName(username);
    }

    @Override
    public User update(User usuario, Long id) {
        return client.update(usuario,id);
    }
}
