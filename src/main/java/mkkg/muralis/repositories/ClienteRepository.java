package mkkg.muralis.repositories;

import mkkg.muralis.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    List<Cliente> findAllByOrderByNome();

    List<Cliente> findByNomeOrCpf(String nome, String cpf);

    Optional<Cliente> findByNomeAndCpf(String nome, String cpf);
}
