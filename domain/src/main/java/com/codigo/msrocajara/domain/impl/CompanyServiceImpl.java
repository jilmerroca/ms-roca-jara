package com.codigo.msrocajara.domain.impl;

import com.codigo.msrocajara.domain.aggregates.request.CompanyRequest;
import com.codigo.msrocajara.domain.aggregates.dto.CompanyDto;
import com.codigo.msrocajara.domain.ports.in.CompanyServiceIn;
import com.codigo.msrocajara.domain.ports.out.CompanyServiceOut;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CompanyServiceImpl implements CompanyServiceIn {

    private final CompanyServiceOut companyServiceOut;

    @Override
    public CompanyDto createCompanyIn(CompanyRequest companyRequest) {
        return companyServiceOut.createCompanyOut(companyRequest);
    }

    @Override
    public Optional<CompanyDto> findByIdIn(Long id) {
        return companyServiceOut.findByIdOut(id);
    }

    @Override
    public List<CompanyDto> getAllIn() {
        return companyServiceOut.obtenerTodosOut();
    }

    @Override
    public CompanyDto updateIn(Long id, CompanyRequest companyRequest) {
        return companyServiceOut.updateOut(id, companyRequest);
    }

    @Override
    public CompanyDto deleteIn(Long id) {
        return companyServiceOut.deleteOut(id);
    }
}
