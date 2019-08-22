package controllers;

import entity.Basket;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import service.BasketService;
import service.UserService;

import java.util.Optional;

@Controller
@RequestMapping("/admin/users")
public class UsersController {
    private UserService userService;
    private BasketService basketService;

    @Autowired
    public UsersController(UserService userService, BasketService basketService) {
        this.userService = userService;
        this.basketService = basketService;
    }

    @GetMapping
    public String showAllUsers(Model model) {
        model.addAttribute("usersList", userService.getAll());
        return "users";
    }

    @GetMapping("/registration")
    public ModelAndView showAddUserPage() {
        return new ModelAndView("registration", "user", new User());
    }

    @PostMapping("/add")
    public ModelAndView addUser(@ModelAttribute("user") User user,
                                @RequestParam("repeatPassword") String repeatPassword,
                                ModelMap model) {
        String email = user.getEmail();
        String login = user.getLogin();
        String password = user.getPassword();
        String role = user.getAccessRole();
        Optional<User> optionalPresentUserByLogin = userService.getUserByLogin(login);
        Optional<User> optionalPresentUserByEmail = userService.getUserByLogin(login);
        if (email.isEmpty() || login.isEmpty() || password.isEmpty() || role == null) {
            model.addAttribute("error", "Empty fields!");
        } else if (optionalPresentUserByLogin.isPresent() || optionalPresentUserByEmail.isPresent()) {
            model.addAttribute("error", "User with this login or email already exist");
        } else if (!password.equals(repeatPassword) || password.length() > 20) {
            model.addAttribute("error", "Passwords are not equals or too big!");
        } else {
            userService.add(user);
            Optional<User> optionalNewUser = userService.getUserByLogin(login);
            if (optionalNewUser.isPresent()) {
                Basket basket = new Basket(optionalNewUser.get());
                basketService.createBasket(basket);
            }
            return new ModelAndView("redirect:/admin/users");
        }
        return new ModelAndView("registration", model);
    }

    @GetMapping("/edit/{id}")
    public String showEditUserPage(@PathVariable("id") Long userID, Model model) {
        Optional<User> optionalUser = userService.getUserById(userID);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            model.addAttribute("user", user);
        }
        return "edit_user";
    }

    @PostMapping("/edit")
    public String applyEditUser(@ModelAttribute("user") User user,
                                @RequestParam("confirmPassword") String confirmPassword,
                                ModelMap model) {
        String login = user.getLogin();
        String email = user.getEmail();
        String password = user.getPassword();
        String role = user.getAccessRole();
        if (email.isEmpty() || login.isEmpty() || password.isEmpty() || role.isEmpty()) {
            model.addAttribute("error", "Empty fields!");
            model.addAttribute("user", user);
        } else if (!(password.equals(confirmPassword))) {
            model.addAttribute("error", "Passwords are not equals!");
            model.addAttribute("user", user);
        } else {
            userService.update(user);
            return "redirect:/admin/users";
        }
        return "edit_user";
    }

    @GetMapping("/remove/{id}")
    public String removeUser(@PathVariable("id") Long userID) {
        userService.removeUser(userID);
        return "redirect:/admin/users";
    }
}
