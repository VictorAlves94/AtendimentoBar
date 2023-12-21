package entidades;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

public class Garcon extends Pessoa{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(mappedBy = "garcom", cascade = CascadeType.ALL)
    private List<Cliente> clientesAtendidos = new ArrayList<>();


}
