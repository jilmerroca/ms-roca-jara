package com.codigo.msrocajara.domain.aggregates.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class CompanyDto {
    private Long id;
    private String socialName;
    private String identificationType;
    private String identificationNumber;
    private String condition;
    private String address;
    private String district;
    private String province;
    private String departament;
    private boolean withHoldingAgent;
    private Integer status;
    private String createdUser;
    private Timestamp createdDate;
    private String modifiedUser;
    private Timestamp modifiedDate;
    private String deletedUser;
    private Timestamp deletedDate;
}
