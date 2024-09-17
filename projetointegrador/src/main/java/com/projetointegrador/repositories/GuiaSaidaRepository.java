package com.projetointegrador.repositories;

import com.projetointegrador.entities.GuiaSaidaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuiaSaidaRepository extends JpaRepository<GuiaSaidaEntity, Long> {
}
