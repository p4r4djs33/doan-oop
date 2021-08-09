package com.example.doanoopkitchenmanage.controller;

import com.example.doanoopkitchenmanage.model.Checklist;
import com.example.doanoopkitchenmanage.model.Employee;
import com.example.doanoopkitchenmanage.model.Ingredient;
import com.example.doanoopkitchenmanage.service.checklist.ChecklistService;
import com.example.doanoopkitchenmanage.service.ingredient.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class IngredientController {
    @Autowired
    IngredientService ingredientService;
    @Autowired
    ChecklistService checklistService;

    @ModelAttribute("checklists")
    public Iterable<Checklist> checklists() {
        return checklistService.findAll();
    }

}