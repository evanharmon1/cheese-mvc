package org.launchcode.controllers;

import org.launchcode.models.User;
import org.launchcode.models.UserData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.apache.commons.lang3.StringUtils;

@Controller
@RequestMapping("user")
public class UserController {

    @RequestMapping(value = "add")
    public String add(Model model) {
        model.addAttribute("title", "Add User");
        model.addAttribute(new User());
        return "user/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(Model model, @ModelAttribute User user, String verify) {

        //  Check username length
        if (user.getUsername().length() < 5 || user.getUsername().length() > 15) {
            model.addAttribute("user", user);
            model.addAttribute("usernameError", "Username must be between 5 & 15 characters");
            return "user/add";
        }
        // Check if email is empty
        if (user.getEmail().isEmpty()) {
            model.addAttribute("user", user);
            model.addAttribute("emailError", "You left the email blank");
            return "user/add";
        }
        // Check username is alphabetic
        if (!StringUtils.isAlpha(user.getUsername())) {
            model.addAttribute("user", user);
            model.addAttribute("usernameError", "Username must be only alphabetic characters");
            return "user/add";
        }
        // Check password matching
        if (!user.getPassword().equals(verify)) {
            model.addAttribute("user", user);
            model.addAttribute("verifyError", "Passwords did not match");
            return "user/add";
        }
        else {
            model.addAttribute("newUser", user);
            UserData.add(user);
            model.addAttribute("users", UserData.getAll());
            return "user/index";
        }
    }


    @RequestMapping(value = "view/{id}")
    public String view(@PathVariable int id, Model model) {

        model.addAttribute("user", UserData.getById(id));
        return "user/view";

    }
}
