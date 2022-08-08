package com.mockproject.controller.admin;

import com.mockproject.entity.model.Users;
import com.mockproject.entity.modeljson.ProductDataModel;
import com.mockproject.entity.modeljson.UserDataModel;
import com.mockproject.response.ProductDataModelResponse;
import com.mockproject.service.ProductsService;
import com.mockproject.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller(value = "ProductAdminController")
@RequestMapping("/admin/product")
public class ProductController {

    @Autowired
    private ProductsService productsService ;

    @GetMapping("")
    public String getAllProduct(Model model) throws Exception{
        List<ProductDataModel> productDataModels = productsService.SpGetListProduct(0,"");
        List<ProductDataModelResponse> response = new ProductDataModelResponse().maptolistDataModel(productDataModels);
        productDataModels.stream().map(x->x.getCreatedAt()).forEach(x-> System.out.println(x));
        model.addAttribute("products", response);
        model.addAttribute("productRequest", new ProductDataModelResponse());
        return "admin/product";
    }


}
