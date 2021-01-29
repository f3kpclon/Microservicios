package com.microservicios.Oauth2.Service;

import com.commons.user.Entities.User;

public interface IUserService {
    User findByUserName(String username);
    User update(User usuario, Long id);
}
