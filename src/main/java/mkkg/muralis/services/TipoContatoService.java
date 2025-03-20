package mkkg.muralis.services;

import mkkg.muralis.entities.TipoContato;
import mkkg.muralis.repositories.TipoContatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TipoContatoService {

    @Autowired
    private TipoContatoRepository tipoContatoRepository;

    public Optional<TipoContato> buscarPorTipo(String tipo) {
        return tipoContatoRepository.findByTipo(tipo);
    }

}
