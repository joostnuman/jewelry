package nl.miwnn.ch16.joost.goudsmiddemo.repositories;

import nl.miwnn.ch16.joost.goudsmiddemo.model.Designer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DesignerRepository extends JpaRepository<Designer, Long> {
}