package name.fastovezz.controller;

import name.fastovezz.model.Product;
import name.fastovezz.service.ProductService;
import name.fastovezz.service.parse.ProductSearchResultsParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

@Controller
public class ProductsController {

    @Autowired
    ProductSearchResultsParser parser;

    @Autowired
    ProductService productService;

    @RequestMapping("/products")
    public String products(Model model) {

        model.addAttribute("list", productService.listAll());
        return "products";
    }

    @RequestMapping("/deleteAll")
    public String delete(Model model) {

        productService.deleteAll();
        model.addAttribute("list", productService.listAll());
        return "products";
    }

    @RequestMapping("/go")
    public String search(@RequestParam(value="searchString", required=false) String searchString, Model model) {

        List<Product> productsList;
        try {
            productsList = parser.parse(searchString);
            for(Product product : productsList) {
                Product persistedProduct = productService.findByName(product.getName());
                if(persistedProduct == null) {
                    productService.save(product);
                } else {
                    persistedProduct.setRating(product.getRating());
                    persistedProduct.setCommentsCount(product.getCommentsCount());
                    persistedProduct.setPrice(product.getPrice());
                    persistedProduct.setDescription(product.getDescription());
                    productService.save(persistedProduct);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        model.addAttribute("list", productService.listAll());
        return "products";
    }

}
