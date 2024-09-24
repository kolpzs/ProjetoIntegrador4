package com.projetointegrador.repositories;

import com.projetointegrador.entities.GuiaEntradaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface GuiaEntradaRepository extends JpaRepository<GuiaEntradaEntity, Long> {

    @Query("SELECT g FROM guias_entradas g WHERE g.data BETWEEN :startDate AND :endDate ORDER BY g.data ASC")
    List<GuiaEntradaEntity> findAllByDateRange(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query("SELECT g FROM guias_entradas g WHERE g.produto.id = :produtoId")
    List<GuiaEntradaEntity> findAllByProdutoId(@Param("produtoId") Long produtoId);

    @Query("SELECT g FROM guias_entradas g WHERE g.fornecedor.id = :fornecedorId")
    List<GuiaEntradaEntity> findAllByFornecedorId(@Param("fornecedorId") Long fornecedorId);
}
