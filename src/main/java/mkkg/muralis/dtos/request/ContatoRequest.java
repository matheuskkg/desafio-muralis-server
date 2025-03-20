package mkkg.muralis.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import mkkg.muralis.entities.Cliente;

public record ContatoRequest(
        @NotNull
        Cliente cliente,

        @NotBlank(message = "Deve ser selecionado um tipo de contato.")
        @Size(max = 50)
        String tipo,

        @NotBlank(message = "Deve ser inserido o e-mail/telefone.")
        @Size(max = 100)
        String valor,

        @Size(max = 255, message = "A observação do contato excedeu o limite de caracteres.")
        String observacao
) {
}
