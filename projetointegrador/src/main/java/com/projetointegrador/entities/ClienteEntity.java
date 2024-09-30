package com.projetointegrador.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table
@Entity(name = "clientes")
public class ClienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}")
    // 000.000.000-00
    private String cpf;

    @Column(nullable = false)
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")
    // email@email.com
    private String email;

    @Column(nullable = false)
    @Pattern(regexp = "\\(\\d{2}\\)\\d{4,5}-\\d{4}")
    // (45)99999-9399
    private String telefone;

    @OneToMany(mappedBy = "cliente")
    @JsonIgnoreProperties({"produtos", "fornecedores", "enderecos", "funcionarios", "adms", "guias_entradas", "guias_saidas", "clientes"})
    private List<GuiaSaidaEntity> guias_saidas;
}
