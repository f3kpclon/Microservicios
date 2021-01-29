package com.Prueba.microServicios.Service;

import com.microservicio.commos.commons.Entities.Product;

import java.util.List;

public interface IProductService {
    List<Product>getAll();
    Product findById(Long idProduct);
    Product saveProduct(Product product);
    void deleteById(Long idProd);
}
