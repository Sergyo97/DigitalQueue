package edu.eci.arsw.digitalqueue.repository;

import edu.eci.arsw.digitalqueue.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String> {
}
