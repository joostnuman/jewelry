package nl.miwnn.ch16.joost.goudsmiddemo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

/*
 * @Author: Joost Numan
 * The concept of the design of a piece of jewelry
 */

@Entity
public class Design {

    @Id @GeneratedValue
    private Long designId;

    private String naam;
    private String beschrijving;

    @Override
    public String toString() {
        return String.format("%s - %s", this.naam, this.beschrijving);
    }

    public long getDesignId() {
        return designId;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }
}
