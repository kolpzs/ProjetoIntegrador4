package com.projetointegrador.repositories;

import com.projetointegrador.entities.AdmEntity;
import com.projetointegrador.entities.FuncionarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdmRepository extends JpaRepository<AdmEntity, Long> {

    @Query("SELECT a FROM adms a WHERE a.nome = :nome")
    AdmEntity findByNome(@Param("nome") String nome);
}
