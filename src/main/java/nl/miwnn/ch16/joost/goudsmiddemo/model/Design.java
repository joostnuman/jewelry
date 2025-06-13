package nl.miwnn.ch16.joost.goudsmiddemo.model;

import jakarta.persistence.*;

import java.util.Set;
import java.util.stream.Collectors;

/*
 * @Designer: Joost Numan
 * The concept of the design of a piece of jewelry
 */

@Entity
public class Design {

    @Id @GeneratedValue
    private Long designId;

    private String naam;
    private String beschrijving;

    @ManyToMany
    private Set<Designer> designers;

    @OneToMany(mappedBy = "design", cascade = CascadeType.ALL)
    private Set<PieceOfJewelry> PiecesOfJewelry;

    public int getNumberOfCopies() {
        return PiecesOfJewelry.size();
    }

    public String getDesignerNames() {
        return designers.stream().map(Designer::getName).sorted().collect(Collectors.joining(", "));
    }

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

    public Set<Designer> getDesigners() {
        return designers;
    }

    public void setDesigners(Set<Designer> designers) {
        this.designers = designers;
    }

    public Set<PieceOfJewelry> getPiecesOfJewelry() {
        return PiecesOfJewelry;
    }

    public void setPiecesOfJewelry(Set<PieceOfJewelry> piecesOfJewelry) {
        PiecesOfJewelry = piecesOfJewelry;
    }
}
