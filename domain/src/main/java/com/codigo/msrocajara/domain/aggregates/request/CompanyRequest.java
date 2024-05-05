package com.codigo.msrocajara.domain.aggregates.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyRequest {
    private String identificationType;
    private String identificationNumber;
}
