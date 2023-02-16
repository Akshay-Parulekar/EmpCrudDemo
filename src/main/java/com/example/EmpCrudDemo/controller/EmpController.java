package com.example.EmpCrudDemo.controller;

import com.example.EmpCrudDemo.dao.EmpDao;
import com.example.EmpCrudDemo.entity.Emp;
import com.example.EmpCrudDemo.helper.FileUploader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class EmpController {

    @Autowired
    EmpDao empDao;

    @Autowired
    FileUploader fileUploader;

    @GetMapping("/")
    public String home(Model model)
    {
        int curPage = 1;
        int maxSize = 5;

        Page<Emp> pageEmp = empDao.findAll(PageRequest.of(curPage-1, maxSize, Sort.by("name")));
        List<Emp> listEmp = pageEmp.toList();

        model.addAttribute("listEmp", listEmp);
        model.addAttribute("curPage", curPage);
        model.addAttribute("totalPages", pageEmp.getTotalPages());

        return "index";
    }

    @GetMapping("/emp/{curPage}/")
    public String page(Model model, @PathVariable int curPage)
    {
        int maxSize = 5;

        Page<Emp> pageEmp = empDao.findAll(PageRequest.of(curPage-1, maxSize, Sort.by("name")));
        List<Emp> listEmp = pageEmp.toList();
        model.addAttribute("listEmp", listEmp);
        model.addAttribute("curPage", curPage);
        model.addAttribute("totalPages", pageEmp.getTotalPages());

        return "index";
    }

    @PostMapping("/emp/save/")
    public String saveEmp(Model model, Emp emp, MultipartFile file)
    {
        Emp empSaved = empDao.save(emp);

        String fileNameOld = file.getOriginalFilename();
        String extension = fileNameOld.substring(fileNameOld.indexOf("."));
        String fileNameNew = empSaved.getId() + extension;

        fileUploader.uploadFile(file, fileNameNew);

        int curPage = 1;
        int maxSize = 5;

        Page<Emp> pageEmp = empDao.findAll(PageRequest.of(curPage-1, maxSize, Sort.by("name")));
        List<Emp> listEmp = pageEmp.toList();
        model.addAttribute("listEmp", listEmp);
        model.addAttribute("curPage", curPage);
        model.addAttribute("totalPages", pageEmp.getTotalPages());

        return "index";
    }

    @GetMapping("/emp/delete/{id}/")
    public String deleteEmp(Model model, @PathVariable int id)
    {
        empDao.deleteById(id);

        int curPage = 1;
        int maxSize = 5;

        Page<Emp> pageEmp = empDao.findAll(PageRequest.of(curPage-1, maxSize, Sort.by("name")));
        List<Emp> listEmp = pageEmp.toList();
        model.addAttribute("listEmp", listEmp);
        model.addAttribute("curPage", curPage);
        model.addAttribute("totalPages", pageEmp.getTotalPages());

        return "index";
    }
}

