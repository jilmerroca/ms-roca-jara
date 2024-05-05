package com.codigo.msrocajara.domain.aggregates.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonRequest {
    private Integer identificationType;
    private String identificationNumber;
    private String company;

    // Not provided by Reniec
    private String email;
    private String phone;
    private String address;
}
