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

    /**
     * Valida o contato com base no tipo de contato (e-mail ou telefone)
     */
    private void validarValorPorTipoContato(String tipo, String valor) {
        if (tipo.equals("e-mail")) {
            validarEmail(valor);
        } else if (tipo.equals("telefone")) {
            validarTelefone(valor);
        } else {
            throw new ContatoInvalidoException("Tipo de contato inválido.");
        }
    }

    /**
     * Um contato é duplicado caso o cliente já tenha um contato com este valor
     *
     * Edge case:
     *      Uma edição de contato é válida caso não exista um contato deste cliente com este valor e ID diferente
     *
     *      Caso contatoId seja diferente de null, está sendo realizada a edição de um contato
     */
    private boolean verificarContatoDuplicado(Cliente cliente, String valor, Integer contatoId) {
        Optional<Contato> optionalContato = contatoRepository.findByClienteIdAndValor(cliente.getId(), valor);

        if (contatoId == null) {
            return optionalContato.isPresent();
        }

        if (optionalContato.isPresent()) {
            return optionalContato.get().getId() != contatoId;
        }

        return false;
    }

    private Contato validarContato(ContatoRequest request, Integer contatoId) {
        validarValorPorTipoContato(request.tipo(), request.valor());

        if (verificarContatoDuplicado(request.cliente(), request.valor(), contatoId)) {
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
        salvar(validarContato(request, null));
    }

    public List<ContatoResponse> buscarPorCliente(Integer id) {
        return contatoRepository.findByClienteId(id);
    }

    public void editar(Integer id, ContatoRequest request) {
        Contato c = validarContato(request, id);
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