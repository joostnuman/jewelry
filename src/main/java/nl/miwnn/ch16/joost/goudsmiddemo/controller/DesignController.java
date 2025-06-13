package nl.miwnn.ch16.joost.goudsmiddemo.controller;

import nl.miwnn.ch16.joost.goudsmiddemo.model.Design;
import nl.miwnn.ch16.joost.goudsmiddemo.repositories.DesignRepository;
import nl.miwnn.ch16.joost.goudsmiddemo.repositories.DesignerRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/*
 * @Author: Joost Numan
 * This program handles all request about jewelry
 */


@Controller
public class DesignController {
    private final DesignerRepository designerRepository;
    private final DesignRepository designRepository;

    public DesignController(DesignerRepository designerRepository, DesignRepository designRepository) {
        this.designerRepository = designerRepository;
        this.designRepository = designRepository;
    }

    @GetMapping({"/", "/design/overview"})
    private String showJewelryOverview(Model datamodel) {
        datamodel.addAttribute("allDesigns", designRepository.findAll());
        datamodel.addAttribute("allDesigners", designerRepository.findAll());

        return "jewelryOverview";
    }

    @GetMapping("/design/new")
    private String showNewDesignForm(Model datamodel) {
        datamodel.addAttribute("formDesign", new Design());
        datamodel.addAttribute("allDesigners", designerRepository.findAll());

        return "newDesignForm";
    }

    @PostMapping("/design/new")
    private String saveOrUpdateDesign(@ModelAttribute("formDesign") Design designToBeSaved,
                                      BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.err.println(bindingResult.getAllErrors());
        } else {
            designRepository.save(designToBeSaved);
        }

        return "redirect:/";
    }

    @GetMapping("/design/delete/{designId}")
    private String deleteDesign(@PathVariable("designId") Long designIdId) {
        designRepository.deleteById(designIdId);
        return "redirect:/design/overview";
    }
}
