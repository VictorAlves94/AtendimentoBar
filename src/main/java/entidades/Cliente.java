package entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(of = "id")
public class Cliente extends Pessoa{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int numeroPulseira;

    private int mesa;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<ContaDetalhada> contaDetalhada = new ArrayList<>();

    private float contaTemp;
    private float fecharConta;



    public void adicionarItemNaConta(String item, float valor) {
        ContaDetalhada detalheConta = new ContaDetalhada();
        detalheConta.setItem(item);
        detalheConta.setValor(valor);
        detalheConta.setCliente(this);

        this.contaDetalhada.add(detalheConta);
    }


}
