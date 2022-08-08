package com.mockproject.controller.admin;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.mockproject.entity.modeljson.UserDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mockproject.entity.model.Users;
import com.mockproject.service.UsersService;

@Controller
@RequestMapping("/admin/user")
public class UserController {

    @Autowired
    private UsersService userService;

    @GetMapping("")
    public String doGetIndex(Model model) {
        List<Users> users = userService.findAll();
        List<UserDataModel> userDataModels = userService.findAll_2();
        userDataModels = userDataModels.stream().map(x -> {
                    String a = users.stream()
                            .filter(y -> y.getId() == x.getId())
                            .flatMap(y -> y.getRoles().stream().map(z -> z.getName()))
                            .collect(Collectors.toList())
                            .toString();
                    x.setRoles(a);
                    return x;
                }
        ).collect(Collectors.toList());
        model.addAttribute("users", userDataModels);
        model.addAttribute("userRequest", new UserDataModel());
        return "admin/user";
    }

    // /admin/user/delete?username={...}
    @GetMapping("/delete")
    public String doGetDelete(@RequestParam("username") String username,
                              RedirectAttributes redirectAttributes) {
        try {
            System.out.println("username: "+username);
            userService.deleteLogical(username);
            redirectAttributes.addFlashAttribute("succeedMessage", "User " + username + " was deleted");
        } catch (Exception ex) {
            ex.printStackTrace();
            redirectAttributes.addFlashAttribute("errorMessage", "Cannot delete user " + username);
        }
        return "redirect:/admin/user";
    }

    @GetMapping("/edit")
    public String doGetEditUser(@RequestParam("username") String username, Model model) {
        System.out.println("username: "+username);

        Users userRequest = userService.findByUsername(username);
        model.addAttribute("userRequest", userRequest);
        return "admin/user::#form";
    }

    @PostMapping("/create")
    public String doPostCreateUser(@ModelAttribute("userRequest") Users userRequest,
                                   RedirectAttributes redirectAttributes) {
        try {
            userService.save(userRequest);
            redirectAttributes.addFlashAttribute("succeedMessage", "User " + userRequest.getUsername() + " was created successfully");
        } catch (Exception ex) {
            ex.printStackTrace();
            redirectAttributes.addFlashAttribute("errorMessage", "Cannot create user: " + userRequest.getUsername());
        }
        return "redirect:/admin/user";
    }

    @PostMapping("/edit")
    public String doPostEditUser(@Valid @ModelAttribute("userRequest") Users userRequest,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> System.out.println(error.getDefaultMessage()));
            redirectAttributes.addFlashAttribute("errorMessage", "User is not valid");
        } else {
            try {
                 System.out.println("request: "+userRequest);
                userService.update(userRequest);
                redirectAttributes.addFlashAttribute("succeedMessage", "User " + userRequest.getUsername() + " has been edited successfully");
            } catch (Exception ex) {
                ex.printStackTrace();
                redirectAttributes.addFlashAttribute("errorMessage", "Cannot update user: " + userRequest.getUsername());
            }
        }
        return "redirect:/admin/user";
    }
}
