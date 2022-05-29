package by.evgen.Cafe.controller;

import by.evgen.Cafe.exception.UserNotFoundException;
import by.evgen.Cafe.model.impl.CafeUserModel;
import by.evgen.Cafe.service.CafeUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizationPageController {
    private final CafeUserService userService;

    public AuthorizationPageController(CafeUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String authorizationForm(Model model) {
        model.addAttribute("user", new CafeUserModel());
        return "cafe/html/general/authentication/authorization";
    }

    @PostMapping("/login")
    public String accountConfirmation(@RequestParam("login") String login, @RequestParam("password") String password,
                                      Model model) throws UserNotFoundException {
        CafeUserModel authorizedUser = userService.findByLoginAndPassword(login, password);
        if (authorizedUser == null) {
            return "cafe/html/general/authentication/authorization";
        }
        model.addAttribute("authorizedUser", authorizedUser);
        String accountUrl = "/admin/" + authorizedUser.getId();
        return "redirect:" + accountUrl;
    }
}
