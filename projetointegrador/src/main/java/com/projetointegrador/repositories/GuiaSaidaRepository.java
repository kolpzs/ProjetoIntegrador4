package com.projetointegrador.repositories;

import com.projetointegrador.entities.GuiaSaidaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface GuiaSaidaRepository extends JpaRepository<GuiaSaidaEntity, Long> {
    @Query("SELECT g FROM guias_saidas g WHERE g.data BETWEEN :startDate AND :endDate ORDER BY g.data ASC")
    List<GuiaSaidaEntity> findAllByDateRange(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query("SELECT g FROM guias_saidas g WHERE g.produto.id = :produtoId")
    List<GuiaSaidaEntity> findAllByProdutoId(@Param("produtoId") Long produtoId);

    @Query("SELECT g FROM guias_saidas g WHERE g.cliente.id = :clienteId")
    List<GuiaSaidaEntity> findAllByClienteId(@Param("clienteId") Long clienteId);
}
