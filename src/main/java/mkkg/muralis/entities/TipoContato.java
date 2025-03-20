package mkkg.muralis.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(
        name = "tipos_contatos",
        uniqueConstraints = @UniqueConstraint(columnNames = {"tpc_tipo"})
)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TipoContato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tpc_id")
    private Integer id;

    @Column(name = "tpc_tipo", length = 50, nullable = false)
    private String tipo;
}
