package com.microservicios.Oauth2.Clients;

import com.commons.user.Entities.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "servicio-users")
public interface UserFeignClient {
    @GetMapping("/users/search/getByUsername")
    User findByUserName(@RequestParam String username);
    @PutMapping("/users/{id}")
    User update(@RequestBody User usuario, @PathVariable Long id);
}
