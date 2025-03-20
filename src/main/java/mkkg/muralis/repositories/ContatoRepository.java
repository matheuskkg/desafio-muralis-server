package mkkg.muralis.repositories;

import mkkg.muralis.entities.Cliente;
import mkkg.muralis.entities.Contato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Integer> {

    Boolean existsByClienteAndValor(Cliente cliente, String valor);

    List<Contato> findByClienteId(Integer id);

    void deleteAllByClienteId(Integer id);

}
