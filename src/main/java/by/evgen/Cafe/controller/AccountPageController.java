package by.evgen.Cafe.controller;

import by.evgen.Cafe.exception.MealNotFoundException;
import by.evgen.Cafe.exception.UserNotFoundException;
import by.evgen.Cafe.handler.CafeModelNotFoundExceptionHandler;
import by.evgen.Cafe.model.impl.MealCategory;
import by.evgen.Cafe.model.impl.MealModel;
import by.evgen.Cafe.service.CafeUserService;
import by.evgen.Cafe.service.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;

@Controller
@RequestMapping("/account")
@CafeModelNotFoundExceptionHandler
public class AccountPageController {

    private final MealService mealService;
    private final CafeUserService userService;

    @Autowired
    public AccountPageController(MealService mealService, CafeUserService userService) {
        this.mealService = mealService;
        this.userService = userService;
    }

    @GetMapping("/users")
    public String viewUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "cafe_user/users";
    }

    @GetMapping("/users/{id}")
    public String viewUser(@PathVariable("id") Long id, Model model) throws UserNotFoundException {
        model.addAttribute("user", userService.findById(id));
        return "cafe_user/user";
    }

    @GetMapping("/menu")
    public String viewMenu(Model model) {
        model.addAttribute("categories", MealCategory.values());
        return "cafe_menu/categories";
    }

    @GetMapping("/menu/{category}/meals")
    public String viewMeals(@PathVariable("category") String categoryName, Model model) {
        model.addAttribute("meals", mealService.findAll());
        model.addAttribute("categoryName", categoryName);
        return "cafe_menu/meals";
    }

    @GetMapping("/menu/{category}/meals/{id}")
    public String viewMeal(@PathVariable("category") String categoryName,
                           @PathVariable("id") Long id, Model model) throws MealNotFoundException {
        model.addAttribute("meal", mealService.findById(id));
        return "cafe_menu/meal";
    }

    @GetMapping("/menu/create")
    public String createMealForm() {
        return "cafe_menu/create-meal";
    }

    @PostMapping("/menu")
    public String createMeal(@RequestParam String name, @RequestParam Double price, @RequestParam MealCategory category,
                             @Valid MealModel meal, BindingResult bindingResult)
            throws MealNotFoundException {
        if (bindingResult.hasErrors())
            return "cafe_menu/create-meal";
        mealService.save(meal);
        return "redirect:/menu";
    }

    @PostMapping("/{id}")
    public String deleteMeal(@PathVariable("id") Long id) {
        mealService.deleteById(id);
        return "redirect:/menu/{category}/meals";
    }

    @GetMapping("/menu/{category}/meals/{id}/edit")
    public String editMealForm(@PathVariable("category") MealCategory category,
                               @PathVariable("id") Long id, Model model) throws MealNotFoundException {
        model.addAttribute("meal", mealService.findById(id));
        return "cafe_menu/edit-meal";
    }


    @PostMapping("/{id}")
    public String editMeal(@ModelAttribute("meal") @Valid MealModel meal, BindingResult bindingResult,
                           @PathVariable("id") Long id) throws MealNotFoundException {
        if (bindingResult.hasErrors())
            return "cafe_menu/edit-meal";
        mealService.save(meal);
        return "redirect:/menu/{category}/meals";
    }


}
