package com.Prueba.microServicios.Controller;


import com.microservicio.commos.commons.Entities.Product;
import com.Prueba.microServicios.Service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
public class ProductController {
    @Autowired
    private IProductService productService;
   /* @Autowired
    private Environment environment;*/

    @Value("${server.port}")
    private Integer port;

    @GetMapping("/listar")
    public List<Product>getALL(){
        /*return productService.getAll().stream().map(product->{
            product.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
            return product;
        }).collect(Collectors.toList());*/
        return productService.getAll().stream().map(product -> {
            product.setPort(port);
            return product;
        }).collect(Collectors.toList());
    }

    @GetMapping("/product/{id}")
    public Product getOne(@PathVariable("id") Long idProduct){
        Product product = productService.findById(idProduct);
        //product.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
        product.setPort(port);

        /*try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        return product;
    }
    @PostMapping("/create")
    @ResponseStatus(value = HttpStatus.CREATED,
    reason = "Producto creado!")
    public Product createProd(@RequestBody Product product){
        Product productSaved = productService.saveProduct(product);
        return productSaved;
    }
    @PutMapping("/edit/{id}")
    @ResponseStatus(value = HttpStatus.CREATED,
    reason = "Producto Actiualizado!")
    public  Product putProd(@PathVariable("id")Long idProd,@RequestBody Product product){
        Product prodDB = productService.findById(idProd);
        prodDB.setName(product.getName());
        prodDB.setPrice(product.getPrice());

        Product prodSaved = productService.saveProduct(prodDB);
        return  prodDB;
    }
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT,
    reason = "Producto Eliminado!")
    public void deleteProd(@PathVariable("id") Long idProd){
        productService.deleteById(idProd);
    }
}
