package com.example.EmpCrudDemo.controller;

import com.example.EmpCrudDemo.dao.EmpDao;
import com.example.EmpCrudDemo.entity.Emp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class EmpController {

    @Autowired
    EmpDao empDao;

    @GetMapping("/")
    public String home(Model model)
    {
        List<Emp> listEmp = empDao.findAll();
        model.addAttribute("listEmp", listEmp);
        return "index";
    }

    @PostMapping("/emp/save/")
    public String saveEmp(Model model, Emp emp)
    {
        empDao.save(emp);
        List<Emp> listEmp = empDao.findAll();
        model.addAttribute("listEmp", listEmp);
        return "index";
    }

    @GetMapping("/emp/delete/{id}/")
    public String deleteEmp(Model model, @PathVariable int id)
    {
        empDao.deleteById(id);
        List<Emp> listEmp = empDao.findAll();
        model.addAttribute("listEmp", listEmp);
        return "index";
    }
}

