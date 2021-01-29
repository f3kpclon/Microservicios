package com.Prueba.microServicios.Repository;

import com.microservicio.commos.commons.Entities.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDAO extends CrudRepository<Product,Long> {
}
