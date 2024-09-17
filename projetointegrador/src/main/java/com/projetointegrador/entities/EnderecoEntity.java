package com.projetointegrador.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table
@Entity(name = "enderecos")
public class EnderecoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String rua;

    @Min(0)
    @Column(nullable = false)
    private int numero;

    @Column(nullable = false)
    private String bairro;

    @Column(nullable = false)
    private String cidade;

    @Column(nullable = false)
    private String estado;

    @Column(nullable = false)
    @Pattern(regexp = "\\d{5}-\\d{3}")
    // 00000-000
    private String cep;

    @ManyToOne
    @JoinColumn(name = "funcionario_id")
    @JsonIgnoreProperties({"funcionarios", "enderecos"})
    private FuncionarioEntity funcionario;

    @ManyToOne
    @JoinColumn(name = "fornecedor_id")
    @JsonIgnoreProperties({"fornecedores", "enderecos"})
    private FornecedorEntity fornecedor;
}
