package com.Prueba.ITEMS.Service;

import com.microservicio.commos.commons.Entities.Product;
import com.Prueba.ITEMS.MODEL.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Qualifier("restTemplate")
public class ItemServiceImpl implements ItemService {


    @Autowired
    private RestTemplate clientRest;


    @Override
    public List<Item> getAll() {
        List<Product> productList = Arrays.asList(clientRest.getForObject("http://servicio-productos/listar", Product[].class));
        return productList.stream().map(
                product -> new Item(product,1)
        ).collect(Collectors.toList());
    }

    @Override
    public Item getItem(Long id, Integer cant) {
        Map<String,String> pathVariable = new HashMap<String,String>();
        pathVariable.put("id",id.toString());
        Product product = clientRest.getForObject("http://servicio-productos/product/{id}",Product.class,pathVariable);
        return new Item(product,cant);
    }

    @Override
    public Product savePro(Product product) {
        HttpEntity<Product>body = new HttpEntity<Product>(product);
        ResponseEntity<Product> response= clientRest.exchange("http://servicio-productos/create", HttpMethod.POST,body,Product.class);
        Product prodResponse = response.getBody();

        return prodResponse;
    }

    @Override
    public Product updatePro(Product product, Long id) {
        HttpEntity<Product> body = new HttpEntity<Product>(product);
        Map<String,String> pathVariable = new HashMap<String,String>();
        pathVariable.put("id",id.toString());
        ResponseEntity<Product> responseEntity = clientRest.exchange("http://servicio-productos/edit/{id}",
                HttpMethod.PUT,body,Product.class,pathVariable);
        return responseEntity.getBody();
    }

    @Override
    public void deletePro(Long id) {
        Map<String,String> pathVariable= new HashMap<>();
        pathVariable.put("id",id.toString());
        clientRest.delete("http://servicio-productos/delete/{id}",pathVariable);
    }
}
