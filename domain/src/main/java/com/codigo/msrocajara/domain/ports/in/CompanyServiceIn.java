package com.codigo.msrocajara.domain.ports.in;

import com.codigo.msrocajara.domain.aggregates.dto.CompanyDto;
import com.codigo.msrocajara.domain.aggregates.request.CompanyRequest;

import java.util.List;
import java.util.Optional;

public interface CompanyServiceIn {
    CompanyDto createCompanyIn(CompanyRequest companyRequest);
    Optional<CompanyDto> findByIdIn(Long id);
    List<CompanyDto> getAllIn();
    CompanyDto updateIn(Long id, CompanyRequest companyRequest);
    CompanyDto deleteIn(Long id);
}
