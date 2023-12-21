package entidades;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@EqualsAndHashCode(of = "id")
public class ContaDetalhada {
    @Id

    private Long id;
    private String item;
    private float valor;

    @OneToMany
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
}