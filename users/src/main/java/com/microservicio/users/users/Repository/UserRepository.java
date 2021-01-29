package com.microservicio.users.users.Repository;

import com.commons.user.Entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

/**
 * anotación para exportar mis metodos del repositorio
 */
@RepositoryRestResource(path = "users")
public interface UserRepository extends PagingAndSortingRepository<User,Long> {
    @RestResource(path="getByUsername")
    User findByUsername(@Param("username") String username);

    /**
     *
     * @param username
     * @return User by name
     */
    @Query("select u from  User u where u.nombre =?1")
    User buscarPorNombre(String username);
}
