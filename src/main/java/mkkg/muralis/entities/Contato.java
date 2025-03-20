package mkkg.muralis.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "contatos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "con_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "con_cli_id", referencedColumnName = "cli_id", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "con_tpc_id", referencedColumnName = "tpc_id", nullable = false)
    private TipoContato tipo;

    @Column(name = "con_valor", length = 100, nullable = false)
    private String valor;

    @Column(name = "con_observacao")
    private String observacao;

    public Contato(Cliente cliente, TipoContato tipo, String valor, String observacao) {
        this.cliente = cliente;
        this.tipo = tipo;
        this.valor = valor;
        this.observacao = observacao;
    }
}
