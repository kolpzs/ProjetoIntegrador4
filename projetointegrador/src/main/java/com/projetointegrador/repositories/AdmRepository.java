package com.projetointegrador.repositories;

import com.projetointegrador.entities.AdmEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdmRepository extends JpaRepository<AdmEntity, Long> {
}
