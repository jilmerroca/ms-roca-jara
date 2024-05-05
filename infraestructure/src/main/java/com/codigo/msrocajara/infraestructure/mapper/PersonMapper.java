package com.codigo.msrocajara.infraestructure.mapper;

import com.codigo.msrocajara.domain.aggregates.dto.PersonDto;
import com.codigo.msrocajara.infraestructure.entity.PersonEntity;
import org.springframework.stereotype.Service;

@Service
public class PersonMapper {
    public static PersonDto fromEntity(PersonEntity entity) {
        PersonDto dto = new PersonDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setLastname(entity.getLastname());
        dto.setIdentificationType(entity.getIdentificationType());
        dto.setIdentificationNumber(entity.getIdentificationNumber());
        dto.setEmail(entity.getEmail());
        dto.setPhone(entity.getPhone());
        dto.setAddress(entity.getAddress());
        dto.setStatus(entity.getStatus());
        dto.setCreatedUser(entity.getCreatedUser());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setModifiedUser(entity.getModifiedUser());
        dto.setModifiedDate(entity.getModifiedDate());
        dto.setDeletedUser(entity.getDeletedUser());
        dto.setDeletedDate(entity.getDeletedDate());
        dto.setCompany(entity.getCompany().getIdentificationNumber());
        return dto;
    }
}
