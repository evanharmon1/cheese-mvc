package org.launchcode.controllers;

import org.launchcode.models.Cheese;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.apache.commons.lang3.StringUtils;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by LaunchCode
 */

@Controller
@RequestMapping(value = "cheese")
public class CheeseController {

    static ArrayList<Cheese> cheeses = new ArrayList<>();

    // Request path: cheese/
    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("cheeses", cheeses);
        model.addAttribute("title", "My Cheeses");
        return "cheese/index";
    }

    // Request path: cheese/add
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddCheeseForm(Model model, @RequestParam(value="error", required = false) String error) {

        model.addAttribute("title", "Add Cheese");
        model.addAttribute("error", error);
        return "cheese/add";
    }

    // Request path: cheese/add
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddCheeseForm(@RequestParam String cheeseName, @RequestParam String cheeseDescription) {

        if (cheeseName.isEmpty()) {
            String errorMessage = "Name field can't be blank";
            return "redirect:/cheese/add?error=" + errorMessage;
        }
        if (!StringUtils.isAlphaSpace(cheeseName)) {
            String errorMessage = "Name can contain only alphabetic characters and spaces";
            return "redirect:/cheese/add?error=" + errorMessage;
        }

        Cheese newCheese = new Cheese(cheeseName, cheeseDescription);
        cheeses.add(newCheese);

        // Redirect to cheese/
        return "redirect:";
    }

    @RequestMapping(value = "remove")
    public String displayRemoveCheese(Model model) {
        model.addAttribute("title", "Remove Cheese");
        model.addAttribute("cheeses", cheeses);
        return "cheese/remove";
    }

    // Remove cheeses via select dropdown assignment goal
    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String removeCheese(@RequestParam String cheeseRemove) {
        for (Cheese cheese : cheeses) {
            if (cheese.getName().equals(cheeseRemove)) {
                cheeses.remove(cheese);
            }
        }
        return "redirect:";
    }
}

/*
Remove cheeses via checkboxes assignment goal using Thymeleaf template form:
<form method="POST">
    <input th:each="cheese : ${cheeses}" type="checkbox" name="cheesesRemove" th:value="${cheese.key}" th:text="${cheese.key + ' ' + cheese.value}" />
    <input type="submit" value="Remove" />
</form>

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String removeCheese(@RequestParam ArrayList<String> cheesesRemove) {
        for (String cheese : cheesesRemove) {
            cheeses.remove(cheese);
        }

        return "redirect:";
    }
*/




