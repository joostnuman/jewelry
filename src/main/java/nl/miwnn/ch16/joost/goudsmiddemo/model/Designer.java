package nl.miwnn.ch16.joost.goudsmiddemo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

/**
 * @author Joost Numan
 * An entity that is responsible for the writing of a book
 */

@Entity
public class Designer {

    @Id
    @GeneratedValue
    private Long designerId;

    private String name;

    public Long getDesignerId() {
        return designerId;
    }

    public void setDesignerId(Long designerId) {
        this.designerId = designerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
