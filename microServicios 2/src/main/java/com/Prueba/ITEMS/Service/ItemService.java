package com.Prueba.ITEMS.Service;

import com.Prueba.ITEMS.MODEL.Item;
import com.microservicio.commos.commons.Entities.Product;
import java.util.List;

public interface ItemService {
    List<Item> getAll();
    Item getItem(Long id, Integer cant);
    Product savePro(Product product);
    Product updatePro(Product product,Long id);
    void deletePro(Long id);
}
