package com.example.springdatabasicdemo.controllers;

import com.example.springdatabasicdemo.services.ModelService;
import com.example.springdatabasicdemo.services.OfferService;
import com.example.springdatabasicdemo.services.UserService;
import com.example.springdatabasicdemo.services.dtos.AddOfferDto;
import com.example.springdatabasicdemo.services.dtos.OfferDto;
import jakarta.validation.Valid;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/offers")
public class OfferController {
    private static final Logger LOG = LogManager.getLogger(Controller.class);
    private final OfferService offerService;
    private final UserService userService;
    private final ModelService modelService;
    @Autowired
    public OfferController(OfferService offerService, UserService userService, ModelService modelService) {
        this.offerService = offerService;
        this.userService = userService;
        this.modelService = modelService;
    }

    @GetMapping("/add")
    public String addOffer(Model model) {
        model.addAttribute("listModels", modelService.getAll());
        model.addAttribute("userList", userService.getAll());
        return "offer-add";
    }

    @ModelAttribute("offerModel")
    public AddOfferDto initOffer() {
        return new AddOfferDto();
    }


    @PostMapping("/add")
    public String addOffer(@Valid AddOfferDto offerModel, BindingResult bindingResult, RedirectAttributes redirectAttributes, ModelMap modelMap, Principal principal) {
        LOG.log(Level.INFO, "Add offer by " + principal.getName());
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("offerModel", offerModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.offerModel",
                    bindingResult);
            return "redirect:/offers/add";
        }

        offerService.register(offerModel, principal.getName());

        return "redirect:/offers/all";
    }

    @GetMapping("/user")
    public String getOffersForUser(Model model, Principal principal) {
        List<OfferDto> userOffers = offerService.findOffersByUsername(principal.getName());
        model.addAttribute("userOffers", userOffers);
        return "offer-user";
    }

    @GetMapping("/all")
    public String showAllOffers(Principal principal, Model model) {
        LOG.log(Level.INFO, "Show all offers for " + principal.getName());
        model.addAttribute("allOffers", offerService.getAll());

        return "offer-list";
    }

    @GetMapping("/searchByYear")
    public String searchByYear(@RequestParam(name = "year") Integer year, Model model) {
        model.addAttribute("searchResults", offerService.getOffersByYear(year));
        return "fragments/offer-search-results";
    }


    @GetMapping("/searchByMileage")
    public String searchByMileage(@RequestParam(name = "mileage") Integer mileage, Model model) {
        model.addAttribute("searchByMileageResults", offerService.getOffersByMileage(mileage));
        return "fragments/offer-search";
    }

    @GetMapping("/top3expensive")
    public String showTop3ExpensiveOffers(Model model) {
        model.addAttribute("top3ExpensiveOffers", offerService.getTop3ExpensiveOffers());
        return "top3expensive-offers";
    }



    @GetMapping("/delete/{id}")
    public String deleteOffer(@PathVariable("id") UUID id) {
        offerService.delete(id);
        return "redirect:/offers/user";
    }

}
