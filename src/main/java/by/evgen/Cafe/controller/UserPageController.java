package by.evgen.Cafe.controller;

import by.evgen.Cafe.exception.MealNotFoundException;
import by.evgen.Cafe.exception.UserNotFoundException;
import by.evgen.Cafe.model.impl.CafeUserModel;
import by.evgen.Cafe.model.impl.MealCategory;
import by.evgen.Cafe.model.impl.MealModel;
import by.evgen.Cafe.service.CafeMealService;
import by.evgen.Cafe.service.CafeUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        return "cafe/html/general/account/account";
    }

    @GetMapping("/main_page")
    public String mainPage(Model model) {
        model.addAttribute("categories", MealCategory.values());
        model.addAttribute("meal", new MealModel());
        return "cafe/html/general/main_page/main-page";
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
        return "cafe/html/general/menu/meals";
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
        return "cafe/html/general/menu/meal";
    }

    @PostMapping("/menu/find")
    public String findMeal(@ModelAttribute("meal") MealModel meal, RedirectAttributes redirect) throws MealNotFoundException {
        MealModel findMeal = mealService.findByName(meal.getName());
        redirect.addAttribute("meal", findMeal);
        String category = findMeal.getCategory().getName();
        String mealUrl = "/user/menu/" + category + "/meals/" + findMeal.getId();
        return "redirect:" + mealUrl;
    }

}
