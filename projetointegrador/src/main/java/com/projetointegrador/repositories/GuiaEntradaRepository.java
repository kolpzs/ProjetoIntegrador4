package com.projetointegrador.repositories;

import com.projetointegrador.entities.GuiaEntradaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuiaEntradaRepository extends JpaRepository<GuiaEntradaEntity, Long> {
}
