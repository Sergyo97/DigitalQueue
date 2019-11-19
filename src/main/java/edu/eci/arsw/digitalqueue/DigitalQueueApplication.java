package edu.eci.arsw.digitalqueue;

import edu.eci.arsw.digitalqueue.model.Role;
import edu.eci.arsw.digitalqueue.model.builder.RoleBuilder;
import edu.eci.arsw.digitalqueue.model.builder.UserBuilder;
import edu.eci.arsw.digitalqueue.repository.RoleRepository;
import edu.eci.arsw.digitalqueue.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class DigitalQueueApplication {

	public static void main(String[] args) {
		SpringApplication.run(DigitalQueueApplication.class, args);
	}

	@Autowired
    PasswordEncoder passwordEncoder;

	@Bean
	public ApplicationRunner initializer(UserRepository userRepository, RoleRepository roleRepository) {
	    Role adminRole = RoleBuilder.aRole().withName("ADMIN").build();
	    roleRepository.save(adminRole);
        Set<Role> roles = new HashSet<>();
        roles.add(adminRole);
		return args -> userRepository.save(
                UserBuilder.anUser()
                        .withId((long) 1)
                        .withEmail("admin@domain.com")
                        .withName("admin")
                        .withPassword(passwordEncoder.encode("password"))
                        .withRoles(roles)
                        .build()
		);
	}

}
