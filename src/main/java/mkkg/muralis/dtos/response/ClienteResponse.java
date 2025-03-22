package mkkg.muralis.dtos.response;

public record ClienteResponse(
        Integer id,
        String nome,
        String cpf,
        String dataNascimento,
        String endereco
) {
}
