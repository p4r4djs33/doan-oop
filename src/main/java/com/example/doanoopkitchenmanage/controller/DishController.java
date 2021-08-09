package com.example.doanoopkitchenmanage.controller;


import com.example.doanoopkitchenmanage.model.Dish;
import com.example.doanoopkitchenmanage.model.Employee;
import com.example.doanoopkitchenmanage.service.dish.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class DishController {
    @Autowired
    DishService dishService;


    @GetMapping("/dishes")
    public ModelAndView dishes(@PageableDefault(size = 5) Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("/dishes/dishes");
        Page<Dish> listDish = dishService.findAll(pageable);
        modelAndView.addObject("dishes", listDish);
        modelAndView.addObject("dishes2", dishService.findAll());
        return modelAndView;
    }

    @GetMapping("/dishes/create")
    public ModelAndView create() {
        ModelAndView modelAndView = new ModelAndView("dishes/createDishes");
        modelAndView.addObject("dishes", new Dish());
        return modelAndView;
    }
    @PostMapping("/dishes/save")
    public String save(Dish dish, RedirectAttributes redirectAttributes) {

        dishService.save(dish);
        redirectAttributes.addFlashAttribute("message", "Created dish successfully!");
        return "redirect:/dishes";
    }
    @GetMapping("/dishes/{id}/edit")
    public ModelAndView edit(@PathVariable("id") Long id) {
        Optional<Dish> dish = dishService.findById(id);
        ModelAndView modelAndView = new ModelAndView("dishes/editDishes");
        modelAndView.addObject("dish", dish.get());
        return modelAndView;
    }
    @PostMapping("/dishes/update")
    public String update(Dish dish, RedirectAttributes redirect) {
        dishService.save(dish);
        redirect.addFlashAttribute("message", "Edit dish successfully!");
        return "redirect:/dishes";
    }
    @GetMapping("/dishes/{id}/delete")
    public ModelAndView delete(@PathVariable("id") Long id) {
        Optional<Dish> dish = dishService.findById(id);
        ModelAndView modelAndView = new ModelAndView("dishes/deleteDishes");
        modelAndView.addObject("dishes",dish.get());
        return modelAndView;
    }
    @PostMapping("/dishes/delete")
    public String delete(Employee employee, RedirectAttributes redirect) {
        dishService.remove(employee.getId());
        redirect.addFlashAttribute("message", "Delete dish successfully!");
        return "redirect:/dishes";
    }
    @GetMapping("/dishes/{id}/view")
    public String view(@PathVariable("id") Long id, Model model) {
        Optional<Dish> dish = dishService.findById(id);
        String status ="";
        if (dish.get().getStatus() == 0) {
            status = "Không đủ nguyên liệu";
        } else {
            status="Đủ nguyên liệu";
        }
        model.addAttribute("status", status);
        model.addAttribute("dishes", dish.get());
        return "dishes/viewDishes";
    }
}
