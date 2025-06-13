package nl.miwnn.ch16.joost.goudsmiddemo.controller;

import nl.miwnn.ch16.joost.goudsmiddemo.model.Designer;
import nl.miwnn.ch16.joost.goudsmiddemo.repositories.DesignerRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Joost Numan
 * Handle all requests related primarily to designers
 */

@Controller
@RequestMapping("/designer")
public class DesignerController {
    private final DesignerRepository designerRepository;

    public DesignerController(DesignerRepository designerRepository) {
        this.designerRepository = designerRepository;
    }

    @GetMapping("/overview")
    private String showDesignerOverview(Model datamodel) {
        datamodel.addAttribute("allDesigners", designerRepository.findAll());
        datamodel.addAttribute("formDesigner", new Designer());

        return "designerOverview";
    }

    @PostMapping("/save")
    private String saveOrUpdateDesigner(@ModelAttribute("formDesigner") Designer designerToBeSaved, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            designerRepository.save(designerToBeSaved);
        }

        return "redirect:/designer/overview";
    }
}
