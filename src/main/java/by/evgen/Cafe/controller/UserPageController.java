package by.evgen.Cafe.controller;

import by.evgen.Cafe.exception.MealNotFoundException;
import by.evgen.Cafe.exception.UserNotFoundException;
import by.evgen.Cafe.model.impl.CafeUserModel;
import by.evgen.Cafe.model.impl.MealCategory;
import by.evgen.Cafe.service.CafeMealService;
import by.evgen.Cafe.service.CafeUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserPageController {
    private final CafeMealService mealService;
    private final CafeUserService userService;

    public UserPageController(CafeMealService mealService, CafeUserService userService) {
        this.mealService = mealService;
        this.userService = userService;
    }

    @GetMapping("/{login}")
    public String account(@PathVariable String login, Model model) throws UserNotFoundException {
        CafeUserModel authorizedUser = userService.findByLogin(login);
        model.addAttribute("authorizedUser", authorizedUser);
        return "cafe/html/account/account";
    }

    @GetMapping("/main_page")
    public String mainPage(Model model) {
        model.addAttribute("categories", MealCategory.values());
        return "cafe/html/user/main_page/main-page";
    }

    /**
     * Функция get запроса, которая отображает блюда выбранной категории
     *
     * @param categoryName - название категории блюда
     * @param model        - все блюда выбранной категории
     * @return возвращает название html файла, который отображает блюда выбранной категории
     */
    @GetMapping("/menu/{category}/meals")
    public String viewMeals(@PathVariable("category") String categoryName, Model model) {
        model.addAttribute("meals", mealService.findAll());
        return "cafe/html/user/menu/meals";
    }


    /**
     * Функция get запроса, которая отображает блюдо выбранное пользователем конкретной категории
     *
     * @param categoryName - название категории блюда
     * @param id           - идентификатор блюда
     * @param model        - выбранное блюдо
     * @return возвращает название html файла, который отображает блюдо выбранное пользователем данной категории
     * @throws MealNotFoundException - исключение, выбрасывающееся в случаях, когда производится доступ к блюду,
     *                               которго не сущетвует.
     */
    @GetMapping("/menu/{category}/meals/{id}")
    public String viewMeal(@PathVariable("category") String categoryName,
                           @PathVariable("id") Long id, Model model) throws MealNotFoundException {
        model.addAttribute("meal", mealService.findById(id));
        return "cafe/html/user/menu/meal";
    }

}
