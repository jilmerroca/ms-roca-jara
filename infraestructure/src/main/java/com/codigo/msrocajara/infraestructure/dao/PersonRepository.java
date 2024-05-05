package com.codigo.msrocajara.infraestructure.dao;

import com.codigo.msrocajara.infraestructure.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<PersonEntity, Long> {
}
