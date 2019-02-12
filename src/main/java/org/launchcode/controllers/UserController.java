package org.launchcode.controllers;

import org.launchcode.models.User;
import org.launchcode.models.UserData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.apache.commons.lang3.StringUtils;

import javax.validation.Valid;

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
    public String add(Model model, @ModelAttribute @Valid User user, Errors errors, String verify) {

        if (errors.hasErrors()) {
            user.setPassword("");
            model.addAttribute("user", user);
            model.addAttribute("title", "Add User");
            return "user/add";
        }
        if (verify.isEmpty()) {
            user.setPassword("");
            model.addAttribute("user", user);
            model.addAttribute("title", "Add User");
            model.addAttribute("verifyError", "Please enter your password again to verify");
            return "user/add";
        }
        if (!user.getPassword().equals(verify)) {
            model.addAttribute("user", user);
            model.addAttribute("title", "Add User");
            model.addAttribute("verifyError", "Passwords did not match");
            return "user/add";
        }
        model.addAttribute("newUser", user);
        UserData.add(user);
        model.addAttribute("users", UserData.getAll());
        return "user/index";

    }


    @RequestMapping(value = "view/{id}")
    public String view(@PathVariable int id, Model model) {

        model.addAttribute("user", UserData.getById(id));
        return "user/view";

    }
}
