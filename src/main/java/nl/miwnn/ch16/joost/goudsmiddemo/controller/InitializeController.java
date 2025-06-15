package nl.miwnn.ch16.joost.goudsmiddemo.controller;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import nl.miwnn.ch16.joost.goudsmiddemo.model.Design;
import nl.miwnn.ch16.joost.goudsmiddemo.model.Designer;
import nl.miwnn.ch16.joost.goudsmiddemo.model.Image;
import nl.miwnn.ch16.joost.goudsmiddemo.model.PieceOfJewelry;
import nl.miwnn.ch16.joost.goudsmiddemo.repositories.DesignRepository;
import nl.miwnn.ch16.joost.goudsmiddemo.repositories.DesignerRepository;
import nl.miwnn.ch16.joost.goudsmiddemo.repositories.ImageRepository;
import nl.miwnn.ch16.joost.goudsmiddemo.repositories.PieceOfJewelryRepository;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
 * @Designer: Joost Numan
 * zet hier wat het programma doet
 */

@Controller
public class InitializeController {
    private final DesignerRepository designerRepository;
    private final DesignRepository designRepository;
    private final PieceOfJewelryRepository pieceOfJewelryRepository;
    private final ImageRepository imageRepository;

    private final Map<String, Designer> designerCache = new HashMap<>();
    private final Map<String, Image> imageCache = new HashMap<>();


    public InitializeController(DesignerRepository designerRepository, DesignRepository designRepository, PieceOfJewelryRepository pieceOfJewelryRepository, ImageRepository imageRepository) {
        this.designerRepository = designerRepository;
        this.designRepository = designRepository;
        this.pieceOfJewelryRepository = pieceOfJewelryRepository;
        this.imageRepository = imageRepository;
    }
    
    @EventListener
    private void seed(ContextRefreshedEvent ignoredEvent) {
        if (designerRepository.count() == 0) {
            initializeDB();
        }
    }

    private void initializeDB() {
        try {
            loadDesigners();
            loadDesigns();
            loadImages();
        } catch (IOException | CsvValidationException e) {
            throw new RuntimeException("Failed to initialize database from CSV files", e);
        }
    }

    private void loadDesigners() throws IOException, CsvValidationException {
        try (CSVReader reader = new CSVReader(new InputStreamReader(
                new ClassPathResource("/example_data/designers.csv").getInputStream()))) {

            // Skip header
            reader.skip(1);

            for (String[] designerLine : reader) {
                Designer designer = new Designer();

                designer.setName(designerLine[0]);
                designer.setImageUrl(designerLine[1]);

                designerRepository.save(designer);
                designerCache.put(designer.getName(), designer);
            }
        }
    }

    private void loadImages() throws IOException, CsvValidationException {
        try (CSVReader reader = new CSVReader(new InputStreamReader(
                new ClassPathResource("/example_data/images.csv").getInputStream()))) {

            // Skip header
            reader.skip(1);

            for (String[] imageLine : reader) {
                Image image = new Image();

                image.setDescription(imageLine[0]);
                image.setImageUrl(imageLine[1]);

                imageRepository.save(image);
                imageCache.put(image.getDescription(), image);
                System.out.println(image);
            }
        }
    }

    private void loadDesigns() throws IOException, CsvValidationException {
        try (CSVReader reader = new CSVReader(new InputStreamReader(
                new ClassPathResource("/example_data/designs.csv").getInputStream()))) {

            // Skip header
            reader.skip(1);

            for (String[] designLine : reader) {
                Design design = new Design();
                design.setNaam(designLine[0]);
                design.setBeschrijving(designLine[1]);


                Set<Image> imageUrlsSet = new HashSet<>();
                String[] imageUrl = designLine[2].split(",");
                for (String description : imageUrl) {
                    imageUrlsSet.add(imageCache.get(description));
                }
                design.setImageUrls(imageUrlsSet);

                Set<Designer> designers = new HashSet<>();
                String[] designerNames = designLine[3].split(",");
                for (String designerName : designerNames) {
                    designers.add(designerCache.get(designerName));
                }
                design.setDesigners(designers);

                designRepository.save(design);

                // Create copies
                makeMultiplePiecesOfJewelry(design, 4);
            }
        }
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
