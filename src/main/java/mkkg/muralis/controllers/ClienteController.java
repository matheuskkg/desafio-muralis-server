package mkkg.muralis.controllers;

import jakarta.validation.Valid;
import mkkg.muralis.dtos.request.ClienteRequest;
import mkkg.muralis.entities.Cliente;
import mkkg.muralis.exceptions.ClienteNaoEncontradoException;
import mkkg.muralis.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
@CrossOrigin
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity cadastrarCliente(@Valid @RequestBody ClienteRequest request) {
        clienteService.cadastrar(request);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity editarCliente(@PathVariable Integer id, @Valid @RequestBody ClienteRequest request) {
        clienteService.editar(id, request);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/by{nome}{cpf}")
    public ResponseEntity buscarPorNomeCpf(@RequestParam(defaultValue = "") String nome, @RequestParam(defaultValue = "") String cpf) {
        List<Cliente> clientes = clienteService.buscarPorNomeCpf(nome, cpf);

        if (clientes.isEmpty()) {
            throw new ClienteNaoEncontradoException("Não foi encontrado nenhum cliente com este nome e/ou cpf.");
        }

        return ResponseEntity.status(HttpStatus.OK).body(clientes.stream().map(Cliente::toDto).toList());
    }

    @GetMapping
    public ResponseEntity buscarTodosClientes() {
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.buscarTodos().stream().map(Cliente::toDto).toList());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity excluirCliente(@PathVariable Integer id) {
        clienteService.excluir(id);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
