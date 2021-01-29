package com.Prueba.microServicios.Service;

import com.microservicio.commos.commons.Entities.Product;
import com.Prueba.microServicios.Repository.ProductDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private ProductDAO productDAO;
    @Override
    @Transactional(readOnly = true)
    public List<Product> getAll() {
        return (List<Product>) productDAO.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Product findById(Long idProduct) {
        Optional<Product> optionalProduct = productDAO.findById(idProduct);
        if(optionalProduct.isPresent()){
            return  optionalProduct.get();
        }
        return null;
    }

    @Override
    @Transactional
    public Product saveProduct(Product product) {
        Product productSaved = productDAO.save(product);
        return productSaved;
    }

    @Override
    @Transactional
    public void deleteById(Long idProd) {
        productDAO.deleteById(idProd);
    }
}
