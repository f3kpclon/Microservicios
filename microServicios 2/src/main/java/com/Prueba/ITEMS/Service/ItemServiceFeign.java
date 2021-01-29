package com.Prueba.ITEMS.Service;

import com.Prueba.ITEMS.Client.ProductClientRest;
import com.Prueba.ITEMS.MODEL.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.microservicio.commos.commons.Entities.Product;


import java.util.List;
import java.util.stream.Collectors;
@Service("feignService")
public class ItemServiceFeign  implements ItemService{

    @Autowired
    private ProductClientRest productClientRest;

    @Override
    public List<Item> getAll() {
        return productClientRest.getAllProducts().stream().map(product -> new Item(product,1)).collect(Collectors.toList());
    }

    @Override
    public Item getItem(Long id, Integer cant) {
        return new Item(productClientRest.getOne(id),cant);
    }

    @Override
    public Product savePro(Product product) {
        
        return productClientRest.saveProd(product);
    }

    @Override
    public Product updatePro(Product product, Long id) {
        return productClientRest.updateProd(product,id);
    }

    @Override
    public void deletePro(Long id) {
        productClientRest.deleteProd(id);
    }
}
