package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.User;
import web.service.UserService;

@Controller
@RequestMapping("/")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("users", userService.findAll());
        return "index";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "newUser";
    }

    @GetMapping("/edit")
    public String editUser(@RequestParam("id") Long id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "editUser";
    }

    @PostMapping("/add")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute("user") User user) {
        userService.update(user);
        return "redirect:/";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam("id") Long id) {
        userService.delete(id);
        return "redirect:/";
    }
}