package com.projetointegrador.repositories;

import com.projetointegrador.entities.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Long> {

    @Query("SELECT p FROM produtos p JOIN p.fornecedores f WHERE f.id = :fornecedorId")
    List<ProdutoEntity> findAllByFornecedorId(@Param("fornecedorId") Long fornecedorId);

    List<ProdutoEntity> findByNome(String nome);

    List<ProdutoEntity> findByMarca(String marca);

    @Query("SELECT p FROM produtos p WHERE p.quantidade_atual > 0")
    List<ProdutoEntity> findAllWithStock();

    @Query("SELECT p FROM produtos p WHERE p.quantidade_atual > 0 ORDER BY p.nome ASC")
    List<ProdutoEntity> findAllWithStockOrdered();
}
