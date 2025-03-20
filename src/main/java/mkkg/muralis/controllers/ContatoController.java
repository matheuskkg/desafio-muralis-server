package mkkg.muralis.controllers;

import jakarta.validation.Valid;
import mkkg.muralis.dtos.request.ContatoRequest;
import mkkg.muralis.services.ContatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cliente/contato")
public class ContatoController {

    @Autowired
    private ContatoService contatoService;

    @PostMapping
    public ResponseEntity cadastrarContato(@Valid @RequestBody ContatoRequest request) {
        contatoService.cadastrar(request);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/{contatoId}")
    public ResponseEntity editarContato(@PathVariable Integer contatoId, @Valid @RequestBody ContatoRequest request) {
        contatoService.editar(contatoId, request);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/{clienteId}")
    public ResponseEntity buscarPorCliente(@PathVariable Integer clienteId) {
        return ResponseEntity.status(HttpStatus.OK).body(contatoService.buscarPorCliente(clienteId));
    }

    @DeleteMapping("/{contatoId}")
    public ResponseEntity excluirContato(@PathVariable Integer contatoId) {
        contatoService.excluir(contatoId);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
