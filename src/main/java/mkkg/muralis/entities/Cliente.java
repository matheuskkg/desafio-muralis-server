package mkkg.muralis.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(
        name = "clientes",
        uniqueConstraints = @UniqueConstraint(columnNames = {"cli_cpf"})
)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cli_id")
    private Integer id;

    @Column(name = "cli_nome", length = 100)
    private String nome;

    @Column(name = "cli_cpf", length = 14)
    private String cpf;

    @Column(name = "cli_data_nascimento")
    private LocalDate dataNascimento;

    @Column(name = "cli_endereco")
    private String endereco;

    public Cliente(String nome, String cpf, LocalDate dataNascimento, String endereco) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.endereco = !endereco.isBlank() ? endereco.trim() : null;
    }
}
