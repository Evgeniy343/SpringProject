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

/**
 * Класс-контроллер для работы с аккаунтом администратора
 *
 * @author Churai Evegniy
 */
@Controller
@RequestMapping("/admin")
@CafeModelNotFoundExceptionHandler
public class AdminPageController {

    /**
     * Поле сервиса для работы с блюдами
     */
    private final CafeMealService mealService;
    /**
     * Поле сервиса для работы с пользователями
     */
    private final CafeUserService userService;

    @Autowired
    public AdminPageController(CafeMealService mealService, CafeUserService userService) {
        this.mealService = mealService;
        this.userService = userService;
    }

    @GetMapping("/{login}")
    public String account(@PathVariable String login, Model model) throws UserNotFoundException {
        CafeUserModel authorizedUser = userService.findByLogin(login);
        model.addAttribute("authorizedUser", authorizedUser);
        return "cafe/html/account/account";
    }
//
//    @GetMapping("/users")
//    public String viewUsers(Model model) {
//        model.addAttribute("users", userService.findAll());
//        return "cafe_user/get/users";
//    }
//
//    @GetMapping("/users/{id}")
//    public String viewUser(@PathVariable("id") Long id, Model model) throws UserNotFoundException {
//        model.addAttribute("user", userService.findById(id));
//        return "cafe_user/get/user";
//    }

    @GetMapping("/main_page")
    public String mainPage(Model model) {
        model.addAttribute("categories", MealCategory.values());
        return "cafe/html/admin/main_page/main-page";
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
        return "cafe/html/admin/menu/meals";
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
        return "cafe/html/admin/menu/meal";
    }

    /**
     * Функция get запроса, которая отображает форму для создания нового блюда
     *
     * @param model - включает пустой объект блюда и категорию блюда
     * @return возвращает название html файла, который отображает форму для создания нового блюда
     */
    @GetMapping("/menu/create")
    public String createMealForm(Model model) {
        model.addAttribute("meal", new MealModel());
        model.addAttribute("mealCategories", MealCategory.values());
        return "cafe/html/admin/menu/create-meal";
    }

    /**
     * Функция post запроса, которая создаёт блюдо и добавляет его в БД
     *
     * @param meal          - блюдо, созданное из данных, введённых пользователем в форму для создания нового блюда
     * @param bindingResult - проверка на корректность введённых данных при создании блюда
     * @return возвращает строку, в которой происходит перенаправление на страницу категорий, а в случае неккоректно
     * введённых данных возвращается название html файла, который отображает форму для создания нового блюда
     */
    @PostMapping("/menu/create")
    public String createMeal(@ModelAttribute("meal") @Valid MealModel meal, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "cafe/html/admin/menu/create-meal";
        mealService.save(meal);
        return "redirect:/admin/main_page";
    }


    /**
     * Функция post запроса, которая удаляет блюдо из БД
     *
     * @param id           - идентификатор блюда
     * @param categoryName - название категории блюда
     * @return возвращает строку, в которой происходит перенаправление на страницу блюд текущей категории
     */
    @PostMapping("/menu/{category}/meals/{id}/delete")
    public String deleteMeal(@PathVariable("id") Long id, @PathVariable("category") String categoryName) {
        mealService.deleteById(id);
        return "redirect:/admin/main_page";
    }

    /**
     * Функиция get запроса, которая отображает форму для редактирования блюда
     *
     * @param category - категория блюда
     * @param id       - идентификатор блюда
     * @param model    - блюдо, нуждающееся в редактировании
     * @return возвращает название html файла, который отображает форму для редактирования блюда
     * @throws MealNotFoundException исключение, выбрасывающееся в случаях, когда производится доступ к блюду,
     *                               которго не сущетвует.
     */
    @GetMapping("/menu/{category}/meals/{id}/edit")
    public String editMealForm(@PathVariable("category") String category,
                               @PathVariable("id") Long id, Model model) throws MealNotFoundException {
        MealModel meal = mealService.findById(id);
        model.addAttribute("meal", meal);
        return "cafe/html/admin/menu/edit-meal";
    }


    /**
     * Функция post запроса, которая редактирует блюдо и обновляет данные в БД
     *
     * @param meal          - блюдо
     * @param bindingResult - проверка на корректность введённых данных при редактировании блюда
     * @param id            - идентификатор блюда
     * @param categoryName  - название категории блюда
     * @return возвращает строку, в которой происходит перенаправление на страницу с блюдами текущей категории,
     * а в случае неккоректно введённых данных возвращается название html файла, который отображает форму для
     * редактирования блюда
     * @throws MealNotFoundException исключение, выбрасывающееся в случаях, когда производится доступ к блюду,
     *                               которго не сущетвует.
     */
    @PostMapping("/menu/{category}/meals/{id}/edit")
    public String editMeal(@ModelAttribute("meal") @Valid MealModel meal, BindingResult bindingResult,
                           @PathVariable("id") Long id,
                           @PathVariable("category") String categoryName) throws MealNotFoundException {
        if (bindingResult.hasErrors())
            return "cafe/html/admin/menu/edit-meal";
        mealService.update(meal);
        return "redirect:/admin/main_page";
    }


}
