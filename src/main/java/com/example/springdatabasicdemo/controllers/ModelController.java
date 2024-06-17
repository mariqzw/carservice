package com.example.springdatabasicdemo.controllers;

import com.example.springdatabasicdemo.services.BrandService;
import com.example.springdatabasicdemo.services.ModelService;
import com.example.springdatabasicdemo.services.dtos.AddModelDto;
import jakarta.validation.Valid;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequestMapping("/models")
public class ModelController {
    private static final Logger LOG = LogManager.getLogger(Controller.class);
    private ModelService modelService;
    private BrandService brandService;

    @Autowired
    public ModelController(BrandService brandService, ModelService modelService) {
        this.brandService = brandService;
        this.modelService = modelService;
    }

    @GetMapping("/add")
    public String addModel(Model model) {
        model.addAttribute("brandList", brandService.findAll());
        return "model-add";
    }

    @ModelAttribute("modelModel")
    public AddModelDto initModel() {
        return new AddModelDto();
    }

    @PostMapping("/add")
    public String addModel(@Valid AddModelDto modelModel, BindingResult bindingResult, RedirectAttributes redirectAttributes, Principal principal) {
        LOG.log(Level.INFO, "Add model by " + principal.getName());
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("modelModel", modelModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.modelModel",
                    bindingResult);
            return "redirect:/models/add";
        }
        modelService.register(modelModel);
        return "redirect:/models/list";
    }


    @GetMapping("/list")
    public String showAllModels(Model model, Principal principal) {
        LOG.log(Level.INFO, "Show all models for " + principal.getName());

        model.addAttribute("modelAdmin", modelService.getAll());
        model.addAttribute("brands", brandService.findAll());

        return "models-list";
    }

    @GetMapping("/all")
    public String showAllModelsForUser(Model model, Principal principal) {
        LOG.log(Level.INFO, "Show all models for " + principal.getName());

        model.addAttribute("modelUser", modelService.getAll());
        model.addAttribute("brands", brandService.findAll());

        return "models-list-user";
    }


    @GetMapping("/edit/{model-name}")
    public String editModelForm(@PathVariable("model-name") String modelName, Model model) {

        AddModelDto modelDto = modelService.findModelByName(modelName);
        model.addAttribute("availableBrands", brandService.findAll());

        model.addAttribute("model", modelDto);
        return "model-edit";
    }
    @PostMapping("/edit/{model-name}")
    public String editModel(@PathVariable("model-name") String modelName, @Valid AddModelDto modelDto, BindingResult result, Model model) {

        model.addAttribute("availableBrands", brandService.findAll());
        if (result.hasErrors()) {
            model.addAttribute("model", modelDto);
            return "model-edit";
        }

        modelService.updateModel(modelName, modelDto);
        return "redirect:/models/list";
    }

}