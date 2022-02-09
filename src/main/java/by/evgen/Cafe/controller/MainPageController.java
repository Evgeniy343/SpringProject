package by.evgen.Cafe.controller;

import by.evgen.Cafe.model.impl.CafeUserModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/main_page")
public class MainPageController {
    @GetMapping("/authorization")
    public String authorizationForm(Model model){
        model.addAttribute("user",new CafeUserModel());
        return "cafe_authentication/get/authorization";
    }

    @GetMapping("/registration")
    public String registrationForm(Model model){
        return "cafe_authentication/get/registration";
    }
}
