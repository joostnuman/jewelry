package nl.miwnn.ch16.joost.goudsmiddemo.controller;

import nl.miwnn.ch16.joost.goudsmiddemo.model.Design;
import nl.miwnn.ch16.joost.goudsmiddemo.model.Designer;
import nl.miwnn.ch16.joost.goudsmiddemo.model.PieceOfJewelry;
import nl.miwnn.ch16.joost.goudsmiddemo.repositories.DesignRepository;
import nl.miwnn.ch16.joost.goudsmiddemo.repositories.DesignerRepository;
import nl.miwnn.ch16.joost.goudsmiddemo.repositories.PieceOfJewelryRepository;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
 * @Author: Joost Numan
 * zet hier wat het programma doet
 */

@Controller
public class InitializeController {
    private final DesignerRepository designerRepository;
    private final DesignRepository designRepository;
    private final PieceOfJewelryRepository pieceOfJewelryRepository;

    public InitializeController(DesignerRepository designerRepository, DesignRepository designRepository, PieceOfJewelryRepository pieceOfJewelryRepository) {
        this.designerRepository = designerRepository;
        this.designRepository = designRepository;
        this.pieceOfJewelryRepository = pieceOfJewelryRepository;
    }
    
    @EventListener
    private void seed(ContextRefreshedEvent ignoredEvent) {
        if (designerRepository.count() == 0) {
            initializeDB();
        }
    }

    private void initializeDB() {
        Designer joost = makeDesigner("joost");
        Designer ruben = makeDesigner("ruben");
        Designer numan = makeDesigner("numan");

        Design ring = makeDesign("ring", joost);
        Design necklace = makeDesign("necklace", ruben);
        Design bracelet = makeDesign("bracelet", numan);
        Design earring = makeDesign("earring", joost);

        makeMultiplePiecesOfJewelry(ring, 2);
        makeMultiplePiecesOfJewelry(necklace, 3);
        makeMultiplePiecesOfJewelry(bracelet, 5);
        makeMultiplePiecesOfJewelry(earring, 8);
    }

    private Designer makeDesigner(String name) {
        Designer designer = new Designer();
        designer.setName(name);
        designerRepository.save(designer);
        return designer;
    }

    private Design makeDesign(String name, Designer... designers) {
        String beschrijving = "This is a " + name;

        Set<Designer> designerSet = new HashSet<>(Arrays.asList(designers));

        Design design = new Design();
        design.setBeschrijving(beschrijving);
        design.setNaam(name);
        design.setDesigners(designerSet);

        designRepository.save(design);
        return design;
    }

    private PieceOfJewelry makePieceOfJewelry(Design design, String size, String metal, String stone) {
        PieceOfJewelry pieceOfJewelry = new PieceOfJewelry();

        pieceOfJewelry.setDesign(design);
        pieceOfJewelry.setFinalSize(size);
        pieceOfJewelry.setFinalMetal(metal);
        pieceOfJewelry.setFinalStone(stone);

        pieceOfJewelryRepository.save(pieceOfJewelry);
        return pieceOfJewelry;
    }

    private void makeMultiplePiecesOfJewelry(Design design, int amountOfPieces) {
        String[] sizes = {"S", "M", "L", "XL"};
        String[] metals = {"Silver", "Gold", "Brass", "Platinum"};
        String[] stones = {"Turquoise", "Onyx", "Malachite", "Jade"};

        for (int i = 0; i < amountOfPieces; i++) {
            makePieceOfJewelry(design, sizes[i % 4], metals[i % 4], stones[i % 4]);
        }
    }
}
