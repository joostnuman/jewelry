package nl.miwnn.ch16.joost.goudsmiddemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.LocalDateTime;

/*
 * @Author: Joost Numan
 * This program handles all request about jewelry
 */


@Controller
public class JewelryController {

    @GetMapping("/")
    private String showJewelryOverview(Model datamodel) {
        datamodel.addAttribute("nu", LocalDateTime.now());
        return "jewelryOverview";
    }
}
