package by.evgen.Cafe.controller;

import by.evgen.Cafe.model.MealType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class AccountPageController {
    @GetMapping("/users")
    public String viewUsers() {
        return "cafe_user/users";
    }

    @GetMapping("/users/{id}")
    public String viewUser(@PathVariable("id") String id) {
        return "cafe_user/user";
    }

    @GetMapping("/menu")
    public String viewMenu(){
        return "cafe_menu/menu";
    }

    @GetMapping("/menu/{type}")
    public String viewMeals(@PathVariable("type") MealType type) {
        return "cafe_menu/meals";
    }

    @GetMapping("/menu/{type}/{id}")
    public String viewMeal(@PathVariable("type") MealType type, @PathVariable("id") String id){
        return "cafe_menu/meal";
    }

}
