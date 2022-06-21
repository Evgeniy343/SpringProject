package by.evgen.cafe.controller;

import by.evgen.cafe.aspect.LoggingMainPage;
import by.evgen.cafe.exception.MealNotFoundException;
import by.evgen.cafe.model.impl.CafeUserModel;
import by.evgen.cafe.model.impl.MealCategory;
import by.evgen.cafe.model.impl.MealModel;
import by.evgen.cafe.model.impl.RoleType;
import by.evgen.cafe.service.CafeMealService;
import by.evgen.cafe.service.CafeUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@LoggingMainPage
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
        model.addAttribute("meal", new MealModel());
        return "cafe/html/general/main_page/main-page";
    }

    @GetMapping("/registration")
    public String registrationForm(Model model) {
        model.addAttribute("user", new CafeUserModel());
        return "cafe/html/general/authentication/registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("user") @Valid CafeUserModel user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "cafe/html/general/authentication/registration";
        }
        user.setRole(RoleType.USER);
        userService.save(user);
        return "redirect:/main_page";
    }

    @GetMapping("/{category}/meals")
    public String viewMeals(@PathVariable("category") String categoryName, Model model) {
        model.addAttribute("meals", mealService.findAll());
        return "cafe/html/general/menu/meals";
    }

    @GetMapping("/{category}/meals/{id}")
    public String viewMeal(@PathVariable("id") Long id, @PathVariable String category, Model model)
            throws MealNotFoundException {
        model.addAttribute("meal", mealService.findById(id));
        return "cafe/html/general/menu/meal";
    }

    @PostMapping("/menu/find")
    public String findMeal(@ModelAttribute("meal") MealModel meal, RedirectAttributes redirect) throws MealNotFoundException {
        MealModel findMeal = mealService.findByName(meal.getName());
        redirect.addAttribute("meal", findMeal);
        String category = findMeal.getCategory().getName();
        String mealUrl = "/main_page/" + category + "/meals/" + findMeal.getId();
        return "redirect:" + mealUrl;
    }
}
