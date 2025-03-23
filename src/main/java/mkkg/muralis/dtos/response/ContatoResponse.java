package mkkg.muralis.dtos.response;

public record ContatoResponse(
        Integer id,
        String tipo,
        String valor,
        String observacao
) {
}
