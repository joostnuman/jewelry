package nl.miwnn.ch16.joost.goudsmiddemo.repositories;

import nl.miwnn.ch16.joost.goudsmiddemo.model.Design;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DesignRepository extends JpaRepository<Design, Long> {
}
