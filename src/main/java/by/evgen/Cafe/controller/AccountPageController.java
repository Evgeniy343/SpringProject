package by.evgen.Cafe.controller;

import by.evgen.Cafe.exception.MealNotFoundException;
import by.evgen.Cafe.exception.UserNotFoundException;
import by.evgen.Cafe.handler.CafeModelNotFoundExceptionHandler;
import by.evgen.Cafe.model.impl.CafeUserModel;
import by.evgen.Cafe.model.impl.MealCategory;
import by.evgen.Cafe.model.impl.MealModel;
import by.evgen.Cafe.service.CafeMealService;
import by.evgen.Cafe.service.CafeUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/account")
@CafeModelNotFoundExceptionHandler
public class AccountPageController {

    private final CafeMealService mealService;
    private final CafeUserService userService;

    @Autowired
    public AccountPageController(CafeMealService mealService, CafeUserService userService) {
        this.mealService = mealService;
        this.userService = userService;
    }


    @PostMapping("/authorization")
    public String accountConfirmation(@RequestParam("login") String login, @RequestParam("password") String password,
                                      Model model) throws UserNotFoundException {
        CafeUserModel authorizedUser = userService.findByLoginAndPassword(login, password);
        if(authorizedUser == null){
            return "cafe_authentication/get/authorization";
        }
        model.addAttribute("authorizedUser",authorizedUser);
        String accountUrl = "/account/" + authorizedUser.getId();
        return "redirect:" + accountUrl;
    }

    @GetMapping("/{id}")
    public String account(@PathVariable Long id,Model model) throws UserNotFoundException {
        CafeUserModel authorizedUser = userService.findById(id);
        model.addAttribute("authorizedUser", authorizedUser);
        return "cafe_account/get/account";
    }

    @GetMapping("/users")
    public String viewUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "cafe_user/get/users";
    }

    @GetMapping("/users/{id}")
    public String viewUser(@PathVariable("id") Long id, Model model) throws UserNotFoundException {
        model.addAttribute("user", userService.findById(id));
        return "cafe_user/get/user";
    }

    @GetMapping("/menu")
    public String viewMenu(Model model) {
        model.addAttribute("categories", MealCategory.values());
        return "cafe_menu/get/categories";
    }

    @GetMapping("/menu/{category}/meals")
    public String viewMeals(@PathVariable("category") String categoryName, Model model) {
        model.addAttribute("meals", mealService.findAll());
        return "cafe_menu/get/meals";
    }

    @GetMapping("/menu/{category}/meals/{id}")
    public String viewMeal(@PathVariable("category") String categoryName,
                           @PathVariable("id") Long id, Model model) throws MealNotFoundException {
        model.addAttribute("meal", mealService.findById(id));
        return "cafe_menu/get/meal";
    }

    @GetMapping("/menu/create")
    public String createMealForm(Model model) {
        model.addAttribute("meal",new MealModel());
        model.addAttribute("mealCategories", MealCategory.values());
        return "cafe_menu/post/create-meal";
    }

    @PostMapping("/menu/create")
    public String createMeal(@ModelAttribute("meal") @Valid MealModel meal, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "cafe_menu/post/create-meal";
        mealService.save(meal);
        return "redirect:/account/menu";
    }

    @PostMapping("/menu/{category}/meals/{id}/delete")
    public String deleteMeal(@PathVariable("id") Long id, @PathVariable("category") String categoryName) {
        mealService.deleteById(id);
        return "redirect:/account/menu/{category}/meals";
    }

    @GetMapping("/menu/{category}/meals/{id}/edit")
    public String editMealForm(@PathVariable("category") MealCategory category,
                               @PathVariable("id") Long id, Model model) throws MealNotFoundException {
        MealModel meal = mealService.findById(id);
        model.addAttribute("meal", meal);
        return "cafe_menu/post/edit-meal";
    }


    @PostMapping("/menu/{category}/meals/{id}/edit")
    public String editMeal(@ModelAttribute("meal") @Valid MealModel meal, BindingResult bindingResult,
                           @PathVariable("id") Long id,
                           @PathVariable("category") String categoryName) throws MealNotFoundException {
        if (bindingResult.hasErrors())
            return "cafe_menu/post/edit-meal";
        mealService.update(meal);
        return "redirect:/account/menu/{category}/meals";
    }


}
