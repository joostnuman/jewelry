package nl.miwnn.ch16.joost.goudsmiddemo.repositories;

import nl.miwnn.ch16.joost.goudsmiddemo.model.Design;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface DesignRepository extends JpaRepository<Design, Long> {
}
