package com.codigo.msrocajara.domain.ports.out;

import com.codigo.msrocajara.domain.aggregates.dto.CompanyDto;
import com.codigo.msrocajara.domain.aggregates.request.CompanyRequest;

import java.util.List;
import java.util.Optional;

public interface CompanyServiceOut {
    CompanyDto createCompanyOut(CompanyRequest companyRequest);
    Optional<CompanyDto> findByIdOut(Long id);
    List<CompanyDto> obtenerTodosOut();
    CompanyDto updateOut(Long id, CompanyRequest companyRequest);
    CompanyDto deleteOut(Long id);
}
