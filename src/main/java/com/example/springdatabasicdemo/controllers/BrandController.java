package com.example.springdatabasicdemo.controllers;

import com.example.springdatabasicdemo.services.dtos.BrandDto;
import com.example.springdatabasicdemo.services.BrandService;
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
@RequestMapping("/brands")
public class BrandController {
    private static final Logger LOG = LogManager.getLogger(Controller.class);
    private BrandService brandService;

    @Autowired
    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping("/add")
    public String addBrand() {
        return "brand-add";
    }

    @ModelAttribute("brandModel")
    public BrandDto initBrand() {
        return new BrandDto();
    }

    @PostMapping("/add")
    public String addBrand(@Valid BrandDto brandModel, BindingResult bindingResult, RedirectAttributes redirectAttributes, Principal principal) {
        LOG.log(Level.INFO, "Add brand by " + principal.getName());
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("brandModel", brandModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.brandModel",
                    bindingResult);
            return "redirect:/brands/add";
        }
        brandService.addBrand(brandModel);

        return "redirect:/brands/list";
    }


    @GetMapping("/all")
    public String showAllBrands(Principal principal, Model model) {
        if (principal != null) {
            LOG.log(Level.INFO, "Show all brands for " + principal.getName());
        }
        model.addAttribute("brandInfos", brandService.findAll());
        return "brand-all";
    }

    @GetMapping("/list")
    public String showAllBrandsForAdmin(Principal principal, Model model) {
        if (principal != null) {
            LOG.log(Level.INFO, "Show all brands for " + principal.getName());
        }
        model.addAttribute("brandInfos", brandService.findAll());
        return "brand-admin";
    }

    @GetMapping("/edit/{brand-name}")
    public String editBrandForm(@PathVariable("brand-name") String brandName, Model model) {

        BrandDto brandDto = brandService.findBrandByName(brandName);
        model.addAttribute("availableBrands", brandService.findAll());

        model.addAttribute("brand", brandDto);
        return "brand-edit";
    }
    @PostMapping("/edit/{brand-name}")
    public String editBrand(@PathVariable("brand-name") String brandName, @Valid BrandDto brandDto, BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("brand", brandDto);
            return "brand-edit";
        }

        brandService.updateBrand(brandName, brandDto);
        return "redirect:/brands/list";
    }

}
