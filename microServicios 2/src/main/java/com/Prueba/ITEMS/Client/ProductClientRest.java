package com.Prueba.ITEMS.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import com.microservicio.commos.commons.Entities.Product;


import java.util.List;

@FeignClient(value = "servicio-productos")
public interface ProductClientRest {
   @GetMapping("/listar")
   List<Product> getAllProducts();
   @GetMapping("/product/{id}")
   Product getOne(@PathVariable("id") Long idProducto);
   @PostMapping("/create")
   Product saveProd(@RequestBody Product product);
   @PutMapping("/edit/{id}")
   Product updateProd(@RequestBody Product product ,@PathVariable("id") Long idProd);
   @DeleteMapping("/delete/{id}")
   void deleteProd(@PathVariable("id")Long idProd);




}
