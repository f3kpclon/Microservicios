package com.Prueba.ITEMS.Controller;

import com.Prueba.ITEMS.MODEL.Item;
import com.Prueba.ITEMS.Service.ItemService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.microservicio.commos.commons.Entities.Product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RefreshScope
@RestController
public class ItemController  {

    private static Logger log = LoggerFactory.getLogger(ItemController.class);
    @Autowired
    private Environment environment;
    @Autowired
    @Qualifier("feignService")
    private ItemService itemService;
    @Value("${configuracion.texto}")
    private String text;

    /**
     * ResponseEntity</?> generico
     */
    @GetMapping("/obtener-config")
    public ResponseEntity<?> getConfig(@Value("${server.port}") String port){

        log.info(text);
        Map<String,String> stringMap= new HashMap<>();
        stringMap.put("texto",text);
        stringMap.put("port",port);

        if (environment.getActiveProfiles().length>=0 && environment.getActiveProfiles()[0].equals("dev")){
            stringMap.put("autor", environment.getProperty("configuracion.autor.nombre"));
            stringMap.put("Email", environment.getProperty("configuracion.autor.email"));
        }

        return  new ResponseEntity<Map<String,String>>(stringMap, HttpStatus.OK);
    }


    @GetMapping("/listar")
    public List<Item> getItems(){
        return itemService.getAll();
    }

    @HystrixCommand(fallbackMethod = "metodoAlternativo")
    @GetMapping("/item/{id}/cantidad/{cant}")
    public Item getItem(@PathVariable("id") Long id,@PathVariable("cant") Integer cant){
        return itemService.getItem(id,cant);
    }
    @PostMapping("/create")
    @ResponseStatus(value = HttpStatus.OK, reason = "Producto Item Creado!")
    public Product savedItemProd(@RequestBody Product product){
        return itemService.savePro(product);
    }
    @PutMapping("/edit/{id}")
    @ResponseStatus(value = HttpStatus.OK, reason = "Producto Item UPDATED!")
    public Product editProductItem(@RequestBody Product product, @PathVariable("id") Long id){
        return itemService.updatePro(product,id);
    }
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(value =HttpStatus.OK, reason = "Producto item DELETED")
    public void deleteProdItem(@PathVariable("id")Long id){
        itemService.deletePro(id);
    }
    public Item metodoAlternativo(Long id, Integer cant){
        Item item = new Item();
        Product product = new Product();
        item.setCant(cant);
        product.setId(id);
        product.setName("sony");
        product.setPrice(700.0);
        item.setProduct(product);

        return item;

    }
}
