package nl.miwnn.ch16.joost.goudsmiddemo.model;

/*
 * @Author: Joost Numan
 * This program describes a piece of jewelry based on a design
 */

import jakarta.persistence.*;

@Entity
public class PieceOfJewelry {
    @Id @GeneratedValue
    private Long pieceOfJewelryId;

    private String finalSize;
    private String finalMetal;
    private String finalStone;

    @ManyToOne
    private Design design;

    public PieceOfJewelry(Design design) {
        this.design = design;
    }

    public PieceOfJewelry() {
    }

    public Design getDesign() {
        return design;
    }

    public void setDesign(Design design) {
        this.design = design;
    }

    public Long getPieceOfJewelryId() {
        return pieceOfJewelryId;
    }

    public void setPieceOfJewelryId(Long pieceOfJewelryId) {
        this.pieceOfJewelryId = pieceOfJewelryId;
    }

    public String getFinalSize() {
        return finalSize;
    }

    public void setFinalSize(String size) {
        this.finalSize = size;
    }

    public String getFinalMetal() {
        return finalMetal;
    }

    public void setFinalMetal(String metal) {
        this.finalMetal = metal;
    }

    public String getFinalStone() {
        return finalStone;
    }

    public void setFinalStone(String stone) {
        this.finalStone = stone;
    }


}
