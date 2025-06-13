package nl.miwnn.ch16.joost.goudsmiddemo.repositories;

import nl.miwnn.ch16.joost.goudsmiddemo.model.PieceOfJewelry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PieceOfJewelryRepository extends JpaRepository<PieceOfJewelry, Long> {
}
