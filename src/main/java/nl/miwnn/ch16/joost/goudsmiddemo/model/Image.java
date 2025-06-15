package nl.miwnn.ch16.joost.goudsmiddemo.model;

/*
 * @Author: Joost Numan
 * This program describes an image that can be shown on a website
 */

import jakarta.persistence.*;

@Entity
public class Image {

    @Id @GeneratedValue
    private Long imageId;

    private String description;
    private String imageUrl;

    @ManyToOne
    private Design design;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
