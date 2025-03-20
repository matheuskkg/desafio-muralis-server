package mkkg.muralis.controllers;

import jakarta.validation.Valid;
import mkkg.muralis.dtos.request.ClienteRequest;
import mkkg.muralis.entities.Cliente;
import mkkg.muralis.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/cliente")
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

    @GetMapping("/by{nomeCpf}")
    public ResponseEntity buscarPorNomeCpf(@RequestParam String nomeCpf) {
        Optional<Cliente> optionalCliente = clienteService.buscarPorNomeCpf(nomeCpf);

        if (optionalCliente.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(optionalCliente.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping
    public ResponseEntity buscarTodosClientes() {
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.buscarTodos());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity excluirCliente(@PathVariable Integer id) {
        clienteService.excluir(id);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
