package com.example.doanoopkitchenmanage.controller;

import com.example.doanoopkitchenmanage.model.Checklist;
import com.example.doanoopkitchenmanage.model.Employee;
import com.example.doanoopkitchenmanage.model.Ingredient;
import com.example.doanoopkitchenmanage.model.MainIngredient;
import com.example.doanoopkitchenmanage.service.checklist.ChecklistService;
import com.example.doanoopkitchenmanage.service.employee.EmployeeService;
import com.example.doanoopkitchenmanage.service.ingredient.IngredientService;
import com.example.doanoopkitchenmanage.service.mainIngredient.MainIngredientService;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;

@Controller
public class ChecklistController {
    @Autowired
    private ChecklistService checklistService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private IngredientService ingredientService;
    @Autowired
    private MainIngredientService mainIngredientService;


    @ModelAttribute("employees")
    public Iterable<Employee> employees() {
        return employeeService.findAll();
    }

    @ModelAttribute("checklists")
    public Iterable<Checklist> checklists() {
        return checklistService.findAll();
    }

    @GetMapping("/home/checklist")
    public String index(Model model) {
        model.addAttribute("checklists", checklistService.findAll());
        return "checklist/list";
    }

    //-----CREATE NEW CHECKLIST
    @GetMapping("/home/checklist/create")
    public ModelAndView create() {
        ModelAndView modelAndView = new ModelAndView("checklist/create");
        modelAndView.addObject("checklist", new Checklist());
        return modelAndView;
    }

    @PostMapping("/home/checklist/save")
    public String save(Checklist checklist, RedirectAttributes redirectAttributes) {
        checklist.setStatus("0");
        checklistService.save(checklist);
        redirectAttributes.addFlashAttribute("message", "Created checklist successfully!");
        return "redirect:/home/checklist";
    }

    //-----VIEW CHECKLIST
    @GetMapping("/home/checklist/{id}/view")
    public ModelAndView viewChecklist(@PathVariable("id") Long id) {
        Optional<Checklist> checklist = checklistService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/checklist/view");
        modelAndView.addObject("id", checklist.get().getId());
        modelAndView.addObject("checklist", checklist.get());
        modelAndView.addObject("data", checklist.get().getData());
        modelAndView.addObject("mainIngredients", mainIngredientService.findAll());
        return modelAndView;
    }


    @PostMapping("/home/checklist/update")
    public ModelAndView update(Checklist checklist) throws ParseException {
        checklist.setStatus("1");
        String data = checklist.getData();

        System.out.println("data: " + data);

        JSONObject obj = (JSONObject) new JSONParser().parse(data);
        System.out.println(obj);
        JSONArray userList = (JSONArray) obj.get("data");
        for (Object userObj : userList) {
            JSONObject userJSONObject = (JSONObject) userObj;
            String Id = (String) userJSONObject.get("ID");
            String amout = (String) userJSONObject.get("Số lượng còn lại");

            Optional<MainIngredient> infoMainIngredient = mainIngredientService.findById(Long.parseLong(Id));
            MainIngredient mainIngredient = infoMainIngredient.get();
            mainIngredient.setAmount(amout);

            mainIngredientService.save(mainIngredient);

            System.out.println("ID " + Id);
            System.out.println("Số lượng còn lại " + amout);
        }


        checklistService.save(checklist);
        ModelAndView modelAndView = new ModelAndView("redirect:/home/checklist");
        return modelAndView;
    }

/*

    //-----CREATE NEW INGREDIENT IN CHECKLIST
    @GetMapping("/home/checklist/{id}/create/ingredient")
    public ModelAndView createIngredient(@PathVariable Long id) {

        ModelAndView modelAndView = new ModelAndView("checklist/create-ingredient");
        modelAndView.addObject("id", id);
        Optional<Checklist> checklist = checklistService.findById(id);
        Ingredient ingredient = new Ingredient();
        ingredient.setChecklist(checklist.get());
        modelAndView.addObject("ingredient", ingredient);

        return modelAndView;
    }

    @PostMapping("/home/checklist/{id}/save/ingredient")
    public String save(@PathVariable Long id, Ingredient ingredient, RedirectAttributes redirectAttributes) {
        ingredientService.save(ingredient);
        redirectAttributes.addFlashAttribute("message", "Created checklist successfully!");
        return "redirect:/home/checklist/{id}/create/ingredient";
    }


    //-----EDIT INGREDIENT IN CHECKLIST
    @GetMapping("/home/checklist/{id}/view/edit/{id2}")
    public ModelAndView editIngredient(@PathVariable("id") Long id, @PathVariable("id2") Long id2) {
        Optional<Ingredient> ingredient = ingredientService.findById(id2);
        ModelAndView modelAndView = new ModelAndView("checklist/edit-ingredient");
        modelAndView.addObject("id", id);
        modelAndView.addObject("ingredient", ingredient.get());
        return modelAndView;
    }

    @PostMapping("/home/checklist/{id}/view/update/{id2}")
    public String update(@PathVariable("id") Long id, @PathVariable("id2") Long id2, Ingredient ingredient, RedirectAttributes redirectAttributes) {
        ingredientService.save(ingredient);
        redirectAttributes.addFlashAttribute("message", "Update ingredient successfully");
        return "redirect:/home/checklist/{id}/view/edit/{id2}";
    }

    //-----DELETE INGREDIENT IN CHECKLIST
    @GetMapping("/home/checklist/{id}/view/delete/{id2}")
    public ModelAndView deleteIngredient(@PathVariable("id") Long id, @PathVariable("id2") Long id2) {
        Optional<Ingredient> ingredient = ingredientService.findById(id2);
        ModelAndView modelAndView = new ModelAndView("checklist/delete-ingredient");
        modelAndView.addObject("id", id);
        modelAndView.addObject("ingredient", ingredient.get());
        return modelAndView;
    }

    @PostMapping("/home/checklist/{id}/view/delete/{id2}")
    public String deleteIngredient(@PathVariable("id") Long id, @PathVariable("id2") Long id2, Ingredient ingredient, RedirectAttributes redirectAttributes) {
        ingredientService.remove(id2);
        redirectAttributes.addFlashAttribute("message", "Delete ingredient successfully");
        return "redirect:/home/checklist/{id}/view";
    }
*/

    //-----DELETE CHECKLIST
    @GetMapping("/home/checklist/{id}/delete")
    public ModelAndView delete(@PathVariable("id") Long id) {
        Optional<Checklist> checklist = checklistService.findById(id);
        ModelAndView modelAndView = new ModelAndView("checklist/delete");
        modelAndView.addObject("checklist", checklist.get());
        return modelAndView;
    }

    @PostMapping("/home/checklist/delete")
    public String delete(Checklist checklist, RedirectAttributes redirectAttributes) {
        checklistService.remove(checklist.getId());
        redirectAttributes.addFlashAttribute("message", "Delete checklist successfully");
        return "redirect:/home/checklist";
    }

}
