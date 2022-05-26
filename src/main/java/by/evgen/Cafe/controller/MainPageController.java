package by.evgen.Cafe.controller;

import by.evgen.Cafe.exception.MealNotFoundException;
import by.evgen.Cafe.model.impl.MealCategory;
import by.evgen.Cafe.service.CafeMealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/main_page")
public class MainPageController {

    private final CafeMealService mealService;

    @Autowired
    public MainPageController(CafeMealService mealService) {
        this.mealService = mealService;
    }

    @GetMapping()
    public String mainPage(Model model) {
        model.addAttribute("categories", MealCategory.values());
        return "cafe/html/main_page/main-page";
    }

    @GetMapping("/registration")
    public String registrationForm() {
        return "cafe/html/authentication/registration";
    }

    @GetMapping("/{category}/meals")
    public String viewMeals(@PathVariable("category") String categoryName, Model model) {
        model.addAttribute("meals", mealService.findAll());
        return "cafe/html/menu/meals";
    }
}
