package spring.la.mia.pizzeria.crud.spring_la_mia_pizzeria_crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import spring.la.mia.pizzeria.crud.spring_la_mia_pizzeria_crud.model.Pizza;
import spring.la.mia.pizzeria.crud.spring_la_mia_pizzeria_crud.repository.PizzaRepository;

import org.springframework.ui.Model;



@Controller
@RequestMapping("/pizzas")


public class PizzaController {

    @Autowired
    private PizzaRepository repository;

    @GetMapping
    public String index(@RequestParam(name="name", required = false) String name,  Model model) {

        List<Pizza> pizzas; //= repository.findAll();

        if(name !=null && !name.isBlank()){
            pizzas = repository.findByNameContainingIgnoreCase(name);
        }else{
            pizzas=repository.findAll();
        }

        model.addAttribute("pizzas", pizzas);
        model.addAttribute("name", name);
        return "pizzas/index";
    }
    
    @GetMapping("/{id}")
    public String show(@PathVariable("id") Integer id, Model model){

        Pizza pizza = repository.findById(id).get();
        model.addAttribute("pizza", pizza);
        return "pizzas/show";
    }
}
