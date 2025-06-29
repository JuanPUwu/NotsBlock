package com.notsblock.notsblock.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "nota")
public class Nota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String titulo;

    @Column
    private String contenido;

    @Column(nullable = false)
    private Boolean enPapelera;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
