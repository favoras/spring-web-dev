package com.myspringweb.springwebapp.product.controller;

import com.myspringweb.springwebapp.product.exception.ProductNotFoundException;
import com.myspringweb.springwebapp.product.model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductsController {

    List<Product> productList = new ArrayList<>();


    @GetMapping("/{id}")
    public String getSingleProduct(@PathVariable long id, Model model){
        model.addAttribute("product", getProductById(id));
        return "product/single-product";
    }

    @GetMapping
    public String getAllProducts(Model model){
        model.addAttribute("products", productList);
        return "product/product-list";
    }

    @GetMapping("/new")
    public String getNewProductForm(Model model){
        model.addAttribute("product", new Product());
        return "product/new-product";
    }

    @PostMapping
    public String addProduct(@ModelAttribute("product") Product product){
        long newId = 1;
        if (!productList.isEmpty()){
            newId = productList.get(productList.size() - 1).getId() + 1;
        }
        product.setId(newId);
        productList.add(product);
        return "redirect:/product";
    }

    @GetMapping("/edit-name/{id}")
    public String getNewProductForm(@PathVariable long id,  Model model) {
        Product product = getProductById(id);
        model.addAttribute("product", product);
        return "product/edit-product-name";
    }

    @PostMapping("/edit-name")
    public String updateProductName(@ModelAttribute("product") Product product, Model model) {
        Product oldProduct = getProductById(product.getId());
        productList.remove(oldProduct);
        product.setPrice(oldProduct.getPrice());
        product.setInStock(oldProduct.getInStock());
        product.setDescription(oldProduct.getDescription());
        productList.add(product);
        model.addAttribute("products", productList);
        return "product/product-list";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable long id, Model model) {
        Product product = getProductById(id);
        productList.remove(product);
        model.addAttribute("products", productList);
        return "redirect:/product";
    }

    private Product getProductById(long id) {
        return productList.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElseThrow(ProductNotFoundException::new);
    }

}
