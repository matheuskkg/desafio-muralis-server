package mkkg.muralis.repositories;

import mkkg.muralis.dtos.response.ContatoResponse;
import mkkg.muralis.entities.Contato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Integer> {

    Optional<Contato> findByClienteIdAndValor(Integer clienteId, String valor);

    @Query("select new mkkg.muralis.dtos.response.ContatoResponse(c.id, c.tipo.tipo, c.valor, c.observacao) from Contato c where c.cliente.id = ?1")
    List<ContatoResponse> findByClienteId(Integer id);

    @Modifying
    @Query("delete from Contato c where c.cliente.id = ?1")
    void deleteAllByClienteId(Integer id);

    @Modifying
    @Query("delete from Contato c where c.id = ?1")
    void deleteContatoById(Integer id);
}
