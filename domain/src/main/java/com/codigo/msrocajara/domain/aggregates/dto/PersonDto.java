package com.codigo.msrocajara.domain.aggregates.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class PersonDto {
    private Long id;
    private String name;
    private String lastname;
    private String identificationType;
    private String identificationNumber;
    private String email;
    private String phone;
    private String address;
    private Integer status;
    private String createdUser;
    private Timestamp createdDate;
    private String modifiedUser;
    private Timestamp modifiedDate;
    private String deletedUser;
    private Timestamp deletedDate;

    private String company;
}
