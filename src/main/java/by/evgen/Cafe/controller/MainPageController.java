package by.evgen.Cafe.controller;

import by.evgen.Cafe.model.impl.CafeUserModel;
import by.evgen.Cafe.model.impl.MealCategory;
import by.evgen.Cafe.model.impl.RoleType;
import by.evgen.Cafe.service.CafeMealService;
import by.evgen.Cafe.service.CafeUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/main_page")
public class MainPageController {

    private final CafeUserService userService;
    private final CafeMealService mealService;

    @Autowired
    public MainPageController(CafeUserService userService, CafeMealService mealService) {
        this.userService = userService;
        this.mealService = mealService;
    }

    @GetMapping()
    public String mainPage(Model model) {
        model.addAttribute("categories", MealCategory.values());
        return "cafe/html/main_page/main-page";
    }

    @GetMapping("/registration")
    public String registrationForm(Model model) {
        model.addAttribute("user", new CafeUserModel());
        return "cafe/html/authentication/registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("user") @Valid CafeUserModel user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "cafe/html/authentication/registration";
        }
        user.setRole(RoleType.USER);
        userService.save(user);
        return "redirect:/main_page";
    }

    @GetMapping("/{category}/meals")
    public String viewMeals(@PathVariable("category") String categoryName, Model model) {
        model.addAttribute("meals", mealService.findAll());
        return "cafe/html/menu/meals";
    }
}
