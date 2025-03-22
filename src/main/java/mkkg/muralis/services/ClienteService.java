package mkkg.muralis.services;

import mkkg.muralis.dtos.request.ClienteRequest;
import mkkg.muralis.entities.Cliente;
import mkkg.muralis.exceptions.CpfEmUsoException;
import mkkg.muralis.exceptions.DataNascimentoInvalidaException;
import mkkg.muralis.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ContatoService contatoService;

    private Cliente validarCliente(ClienteRequest request) {
        try {
            LocalDate dataNascimento = null;

            if (request.dataNascimento() != null && !request.dataNascimento().isEmpty()) {
                dataNascimento = LocalDate.parse(request.dataNascimento());

                if (dataNascimento.isAfter(LocalDate.now())) {
                    throw new DataNascimentoInvalidaException("A data de nascimento não pode ser no futuro.");
                }
            }

            return new Cliente(request.nome(), request.cpf(), dataNascimento, request.endereco());
        } catch (DateTimeParseException e) {
            throw new DataNascimentoInvalidaException("A data de nascimento enviada é inválida");
        }
    }

    private void salvar(Cliente cliente) {
        try {
            clienteRepository.save(cliente);
        } catch (DataIntegrityViolationException e) {
            throw new CpfEmUsoException("O CPF " + cliente.getCpf() + " já está cadastrado");
        }
    }

    public void cadastrar(ClienteRequest request) {
        salvar(validarCliente(request));
    }

    public void editar(Integer id, ClienteRequest request) {
        Cliente c = validarCliente(request);
        c.setId(id);

        salvar(c);
    }

    public List<Cliente> buscarPorNomeCpf(String nome, String cpf) {
        if (!nome.isBlank() && !cpf.isBlank()) {
            return clienteRepository.findByNomeIgnoreCaseAndCpf(nome, cpf).stream().toList();
        } else {
            return clienteRepository.findByNomeIgnoreCaseOrCpf(nome, cpf);
        }
    }

    public List<Cliente> buscarTodos() {
        return clienteRepository.findAllByOrderByNome();
    }

    public void excluir(Integer id) {
        contatoService.excluirTodosPorClienteId(id);
        clienteRepository.deleteById(id);
    }
}
