package nl.miwnn.ch16.joost.goudsmiddemo.repositories;

import nl.miwnn.ch16.joost.goudsmiddemo.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
