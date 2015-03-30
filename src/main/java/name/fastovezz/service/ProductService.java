package name.fastovezz.service;

import name.fastovezz.model.Product;
import name.fastovezz.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    @Transactional
    public List<Product> listAll() {
        return productRepo.findAll();
    }

    @Transactional
    public Product findByName(String n) {
        return productRepo.findByName(n);
    }

    @Transactional
    public Product findById(Long id) {
        return productRepo.findOne(id);
    }

    @Transactional
    public Product save(Product s) {
        return productRepo.saveAndFlush(s);
    }

    @Transactional
    public void delete(Product s) {
        productRepo.delete(s);
    }

    @Transactional
    public void deleteAll() {
        productRepo.deleteAll();
    }

    @Transactional
    public void delete(Long id) {
        productRepo.delete(id);
    }
}
