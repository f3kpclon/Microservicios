package com.Prueba.ITEMS.MODEL;
import com.microservicio.commos.commons.Entities.Product;
public class Item {
    private Product product;
    private Integer cant;

    public Item(Product product, Integer cant) {
        this.product = product;
        this.cant = cant;
    }

    public Item() {
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getCant() {
        return cant;
    }

    public void setCant(Integer cant) {
        this.cant = cant;
    }

    public Double getTotal(){
        return product.getPrice() * cant.doubleValue();
    }

    @Override
    public String toString() {
        return "Item{" +
                "product=" + product +
                ", cant=" + cant +
                '}';
    }
}
