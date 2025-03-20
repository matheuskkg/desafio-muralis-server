package mkkg.muralis.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(
        name = "clientes",
        uniqueConstraints = @UniqueConstraint(columnNames = {"cpf"})
)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100)
    private String nome;

    @Column(length = 14)
    private String cpf;

    private LocalDate dataNascimento;

    private String endereco;

    public Cliente(String nome, String cpf, LocalDate dataNascimento, String endereco) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.endereco = !endereco.isBlank() ? endereco.trim() : null;
    }
}
