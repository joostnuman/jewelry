package nl.miwnn.ch16.joost.goudsmiddemo.controller;

import nl.miwnn.ch16.joost.goudsmiddemo.model.Design;
import nl.miwnn.ch16.joost.goudsmiddemo.model.PieceOfJewelry;
import nl.miwnn.ch16.joost.goudsmiddemo.repositories.DesignRepository;
import nl.miwnn.ch16.joost.goudsmiddemo.repositories.ImageRepository;
import nl.miwnn.ch16.joost.goudsmiddemo.repositories.PieceOfJewelryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/*
 * @Author: Joost Numan
 * This program controls the requests about pieces of jewelry
 */

@Controller
@RequestMapping("/pieceOfJewelry")
public class PieceOfJewelryController {
    private final DesignRepository designRepository;
    private final PieceOfJewelryRepository pieceOfJewelryRepository;
    private final ImageRepository imageRepository;

    public PieceOfJewelryController(DesignRepository designRepository, PieceOfJewelryRepository pieceOfJewelryRepository, ImageRepository imageRepository) {
        this.designRepository = designRepository;
        this.pieceOfJewelryRepository = pieceOfJewelryRepository;
        this.imageRepository = imageRepository;
    }

    @GetMapping("/new/{designId}")
    private String showNewDesignForm(@PathVariable("designId") Long designId, Model datamodel) {
        String[] sizes = {"S", "M", "L", "XL"};
        String[] metals = {"Silver", "Gold", "Brass", "Platinum"};
        String[] stones = {"Turquoise", "Onyx", "Malachite", "Jade"};

        Optional<Design> optionalDesign = designRepository.findById(designId);

        if (optionalDesign.isPresent()) {
            PieceOfJewelry pieceOfJewelry = new PieceOfJewelry(optionalDesign.get());

            datamodel.addAttribute("masterDesign", optionalDesign);
            datamodel.addAttribute("formPieceOfJewelry", pieceOfJewelry);
            datamodel.addAttribute("sizes", sizes);
            datamodel.addAttribute("metals", metals);
            datamodel.addAttribute("stones", stones);
        }

        return "buyPieceOfJewelry";
    }

    @PostMapping("/new/")
    private String createNewPieceOfJewelry(@ModelAttribute("formPieceOfJewelry") PieceOfJewelry pieceOfJewelryToBeSaved,
                                           BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            System.err.println(bindingResult.getAllErrors());
        } else {
                pieceOfJewelryRepository.save(pieceOfJewelryToBeSaved);
        }

        return "redirect:/";
    }
}
