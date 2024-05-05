package com.codigo.msrocajara.infraestructure.mapper;

import com.codigo.msrocajara.domain.aggregates.dto.CompanyDto;
import com.codigo.msrocajara.infraestructure.entity.CompanyEntity;
import org.springframework.stereotype.Service;

@Service
public class CompanyMapper {
    public static CompanyDto fromEntity(CompanyEntity entity) {
        CompanyDto dto = new CompanyDto();
        dto.setId(entity.getId());
        dto.setSocialName(entity.getSocialName());
        dto.setIdentificationType(entity.getIdentificationType());
        dto.setIdentificationNumber(entity.getIdentificationNumber());
        dto.setCondition(entity.getCondition());
        dto.setAddress(entity.getAddress());
        dto.setDistrict(entity.getDistrict());
        dto.setProvince(entity.getProvince());
        dto.setDepartament(entity.getDepartament());
        dto.setWithHoldingAgent(entity.isWithHoldingAgent());
        dto.setStatus(entity.getStatus());
        dto.setCreatedUser(entity.getCreatedUser());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setModifiedUser(entity.getModifiedUser());
        dto.setModifiedDate(entity.getModifiedDate());
        dto.setDeletedUser(entity.getDeletedUser());
        dto.setDeletedDate(entity.getDeletedDate());
        return dto;
    }
}
