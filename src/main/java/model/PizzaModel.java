package model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "pizza", schema = "lincolnbd")
public class PizzaModel {

//    @JsonIgnore
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PIZZA_SEQ")
    @SequenceGenerator(name = "PIZZA_SEQ", sequenceName = "PIZZA_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "nombre", length = 50, unique = true)
    @NotNull
    @Size(min = 4, max = 50)
    private String nombre;

    @Column(name = "tipo", length = 50, unique = true)
    @Size(min = 4, max = 50)
    private String tipo;

    @Column(name = "precio", length = 50, unique = true)
    @Size(min = 4, max = 50)
    private Double precio;

    @Column(name = "autor", length = 50, unique = true)
    @Size(min = 4, max = 50)
    private String autor;
}
