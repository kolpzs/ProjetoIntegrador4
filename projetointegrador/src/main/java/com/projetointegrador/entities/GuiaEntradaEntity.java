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
@Entity(name = "guias_entradas")
public class GuiaEntradaEntity {

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

    @ManyToOne
    @JoinColumn(name = "produto_id")
    @JsonIgnoreProperties({"produtos", "fornecedores", "enderecos"})
    private ProdutoEntity produto;

    @ManyToOne
    @JoinColumn(name = "fornecedor_id")
    @JsonIgnoreProperties({"produtos", "fornecedores", "enderecos"})
    private FornecedorEntity fornecedor;

    @ManyToOne
    @JoinColumn(name = "funcionario_id")
    @JsonIgnoreProperties({"produtos", "fornecedores", "enderecos"})
    private FuncionarioEntity funcionario;
}
