package mkkg.muralis.dtos.response;

import java.time.LocalDate;

public record ClienteResponse(
        Integer id,
        String nome,
        String cpf,
        LocalDate dataNascimento,
        String endereco
) {
}
