package info.lemuu.apiclients.client.repository;

import info.lemuu.apiclients.client.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Lemuel Brenner
 */
public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findByEmail(String email);

    Optional<Client> findByRG(String RG);

    Optional<Client> findByCPF(String CPF);

}
