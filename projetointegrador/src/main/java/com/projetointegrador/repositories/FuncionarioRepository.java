package com.projetointegrador.repositories;

import com.projetointegrador.entities.FuncionarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository extends JpaRepository<FuncionarioEntity, Long> {

    @Query("SELECT f FROM funcionarios f WHERE f.cpf = :cpf")
    FuncionarioEntity findByCpf(@Param("cpf") String cpf);
}
