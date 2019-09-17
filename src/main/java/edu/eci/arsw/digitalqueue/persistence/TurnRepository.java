package edu.eci.arsw.digitalqueue.persistence;

import edu.eci.arsw.digitalqueue.model.Turn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TurnRepository extends JpaRepository<Turn, Long> {

    Turn findByCode(String code);

}
