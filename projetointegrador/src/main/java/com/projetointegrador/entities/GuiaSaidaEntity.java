package com.projetointegrador.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table
@Entity(name = "guias_saidas")
public class GuiaSaidaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Date data;

    @Min(0)
    @Column(nullable = false)
    private float valor;

    @Min(0)
    @Column(nullable = false)
    private int quantidade;

    @ManyToMany
    @JoinTable(
            name = "guia_saida_produto",
            joinColumns = @JoinColumn(name = "guia_saida_id"),
            inverseJoinColumns = @JoinColumn(name = "produto_id")
    )
    @JsonIgnoreProperties({"produtos", "fornecedores", "enderecos"})
    private List<ProdutoEntity> produtos;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    @JsonIgnoreProperties({"produtos", "fornecedores", "enderecos"})
    private ClienteEntity cliente;

    @ManyToOne
    @JoinColumn(name = "funcionario_id")
    @JsonIgnoreProperties({"produtos", "fornecedores", "enderecos"})
    private FuncionarioEntity funcionario;
}
