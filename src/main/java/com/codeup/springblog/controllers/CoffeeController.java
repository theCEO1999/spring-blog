package com.codeup.springblog.controllers;
import com.codeup.springblog.models.Coffee;
import com.codeup.springblog.models.Customer;
import com.codeup.springblog.models.Supplier;
import com.codeup.springblog.repositories.CoffeeRepository;
import com.codeup.springblog.repositories.CustomerRepository;
import com.codeup.springblog.repositories.SupplierRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/coffee")
public class CoffeeController {

    private final CoffeeRepository coffeeDao;
    private final SupplierRepository suppliersDao;

    private final CustomerRepository customersDao;

    public CoffeeController(CoffeeRepository coffeeDao, SupplierRepository suppliersDao, CustomerRepository customersDao){
        this.coffeeDao = coffeeDao;
        this.suppliersDao = suppliersDao;
        this.customersDao = customersDao;
    }

    @GetMapping
    public String coffee(){
        return "coffee";
    }
    @GetMapping("/{roast}")
    public String roast(@PathVariable String roast, Model model){
        Coffee selection = new Coffee(roast, "CoolBeans");
        Coffee selection2 = new Coffee(roast, "Coffee Bros");
        selection.setOrigin("Ethiopia");
        selection2.setOrigin("Vietnam");
        List<Coffee> selections = new ArrayList<>(List.of(selection, selection2));
        model.addAttribute("selections",selections);
        return "coffee";
    }

    @PostMapping
    public String signUp(@RequestParam(name="email") String email, Model model){
        model.addAttribute("email", email);
        return "coffee";
    }

    @GetMapping("/all-coffees")
    public String allCoffees(Model model){
        List<Coffee> coffees = coffeeDao.findAll();
        model.addAttribute("coffees", coffees);
        return "all-coffees";
    }

    @GetMapping("/new")
    public String addCoffeeForm(Model model){
        List<Supplier> suppliers = suppliersDao.findAll();
        model.addAttribute("suppliers", suppliers);
        model.addAttribute("coffee", new Coffee());
        return "create-coffee";
    }

    @PostMapping("/new")
    public String addCoffee(@ModelAttribute Coffee coffee)  {
//        Supplier supplier = suppliersDao.findById(id);
//        Coffee coffee = new Coffee(roast, origin, brand, supplier);
        coffeeDao.save(coffee);
        return "redirect:/coffee/all-coffees";
    }

    @GetMapping("/suppliers")
    public String showSuppliersForm(Model model){
        List<Supplier> suppliers = suppliersDao.findAll();
        model.addAttribute("suppliers", suppliers);
        model.addAttribute("supplier", new Supplier());
        return "/suppliers";
    }

    @PostMapping("/suppliers")
    public String insertSupplier(@ModelAttribute Supplier supplier){
//        Supplier supplier = new Supplier(name);
        suppliersDao.save(supplier);
        return "redirect:coffee/suppliers";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        model.addAttribute("customer", new Customer());
        return "/registration";
    }

    @PostMapping("/customer/new")
    public String registerCustomer(@ModelAttribute Customer customer){
        customersDao.save(customer);
        return "redirect:/coffee";
    }

//    @PostMapping("/customer/new")
//    public String registerCustomer(@RequestParam(name = "name") String name, @RequestParam(name = "email")String email){
//        customersDao.save(new Customer(name,email));
//        return "redirect:/coffee";
//    }

    @PostMapping("/customer/{customerId}/favorite/{coffeeId}")
    public String favoriteCoffee(@PathVariable long customerId, @PathVariable long coffeeId){
        Customer customer = customersDao.findById(customerId);
        List<Coffee> favorites = customer.getCoffeeList();
        favorites.add(coffeeDao.findById(coffeeId));
        customer.setCoffeeList(favorites);
        customersDao.save(customer);
        return "redirect:/coffee";
    }

}
