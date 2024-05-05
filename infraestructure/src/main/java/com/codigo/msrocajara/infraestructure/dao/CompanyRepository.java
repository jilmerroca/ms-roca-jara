package com.codigo.msrocajara.infraestructure.dao;

import com.codigo.msrocajara.infraestructure.entity.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository  extends JpaRepository<CompanyEntity, Long> {
    CompanyEntity findByIdentificationNumber(String identificationNumber);
}