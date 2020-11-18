package com.myspringweb.springwebapp.product.controller;

import com.myspringweb.springwebapp.product.model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductsController {

    private List<Product> productList = new ArrayList<>();

    @GetMapping("/{id}")
    public String getSingleProduct(@PathVariable long id, Model model){
        Product product = new Product(); // var product = new Product();
        product.setId(id);
        product.setName("Telefonas Samsung");
        product.setDescription("Galingas Samsung Note 10 Plus telefonas");
        product.setInStock(10);
        product.setPrice(BigDecimal.valueOf(1299.99)); // setPrice(new BigDecimal("1299.99"));

        model.addAttribute("product", product);
        return "product/single-product";
    }
}
