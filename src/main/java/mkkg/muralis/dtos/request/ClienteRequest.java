package mkkg.muralis.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ClienteRequest(
        @NotBlank(message = "O nome do cliente não pode estar vazio.")
        @Size(max = 100, message = "O nome do cliente deve conter no máximo 100 caracteres.")
        String nome,

        @NotBlank(message = "O CPF do cliente não pode estar vazio.")
        @Size(max = 14, message = "O CPF do cliente deve conter no máximo 14 caracteres.")
        @Pattern(regexp = "^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$", message = "O CPF deve seguir o seguinte formato: 123.456.789-00")
        String cpf,

        @Pattern(regexp = "^(\\d{4}-\\d{2}-\\d{2})?$", message = "A data de nascimento é inválida.")
        String dataNascimento,

        @Size(max = 255, message = "O endereço do cliente excedeu o limite de caracteres.")
        String endereco
) {
}
