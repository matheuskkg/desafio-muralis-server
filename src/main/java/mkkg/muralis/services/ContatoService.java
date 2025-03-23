package mkkg.muralis.services;

import jakarta.transaction.Transactional;
import mkkg.muralis.dtos.request.ContatoRequest;
import mkkg.muralis.dtos.response.ContatoResponse;
import mkkg.muralis.entities.Cliente;
import mkkg.muralis.entities.Contato;
import mkkg.muralis.entities.TipoContato;
import mkkg.muralis.exceptions.ContatoDuplicadoException;
import mkkg.muralis.exceptions.ContatoInvalidoException;
import mkkg.muralis.repositories.ContatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContatoService {

    @Autowired
    private ContatoRepository contatoRepository;

    @Autowired
    private TipoContatoService tipoContatoService;

    private final String PATTERN_EMAIL = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$";

    private final String PATTERN_TELEFONE = "^\\d+$";

    private void validarEmail(String email) {
        if (!email.matches(PATTERN_EMAIL)) {
            throw new ContatoInvalidoException("E-mail inválido.");
        }
    }

    private void validarTelefone(String telefone) {
        if (!telefone.matches(PATTERN_TELEFONE)) {
            throw new ContatoInvalidoException("Telefone inválido.");
        }
    }

    private boolean verificarContatoDuplicado(Cliente cliente, String valor) {
        return contatoRepository.existsByClienteAndValor(cliente, valor);
    }

    private Contato validarContato(ContatoRequest request) {
        if (request.tipo().equals("e-mail")) {
            validarEmail(request.valor());
        } else if (request.tipo().equals("telefone")) {
            validarTelefone(request.valor());
        } else {
            throw new ContatoInvalidoException("Tipo de contato inválido.");
        }

        if (verificarContatoDuplicado(request.cliente(), request.valor())) {
            throw new ContatoDuplicadoException("Este cliente já possui o contato '" + request.valor() + "' salvo.");
        }

        Optional<TipoContato> optionalTipoContato = tipoContatoService.buscarPorTipo(request.tipo().toLowerCase());
        TipoContato tipoContato = null;

        if (optionalTipoContato.isPresent()) {
            tipoContato = optionalTipoContato.get();
        }

        return new Contato(request.cliente(), tipoContato, request.valor(), request.observacao());
    }

    private void salvar(Contato contato) {
        contatoRepository.save(contato);
    }

    public void cadastrar(ContatoRequest request) {
        salvar(validarContato(request));
    }

    public List<ContatoResponse> buscarPorCliente(Integer id) {
        return contatoRepository.findByClienteId(id);
    }

    public void editar(Integer id, ContatoRequest request) {
        Contato c = validarContato(request);
        c.setId(id);

        salvar(c);
    }

    @Transactional
    public void excluirTodosPorClienteId(Integer id) {
        contatoRepository.deleteAllByClienteId(id);
    }

    @Transactional
    public void excluir(Integer id) {
        contatoRepository.deleteContatoById(id);
    }
}