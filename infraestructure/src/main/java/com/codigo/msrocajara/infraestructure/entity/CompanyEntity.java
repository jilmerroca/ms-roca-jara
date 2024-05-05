package com.codigo.msrocajara.infraestructure.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.sql.Timestamp;

@Entity
@Table(name = "empresa_info")
@Getter
@Setter
public class CompanyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "razonsocial", nullable = false, length = 255)
    private String socialName;

    @Column(name = "tipodocumento", nullable = false, length = 5)
    private String identificationType;

    @Column(name = "numerodocumento", nullable = false, length = 20, unique = true)
    private String identificationNumber;

    @Min(0) @Max(1)
    @Column(name = "estado", nullable = false)
    private Integer status;

    @Column(name = "condicion", nullable = false, length = 50)
    private String condition;

    @Lob
    @Column(name = "direccion")
    private String address;

    @Column(name = "distrito", nullable = false, length = 100)
    private String district;

    @Column(name = "provincia", nullable = false, length = 100)
    private String province;

    @Column(name = "departamento", nullable = false, length = 100)
    private String departament;

    @ColumnDefault(value = "false")
    @Column(name = "esagenteretencion", nullable = false)
    private boolean withHoldingAgent;

    @Column(name = "usuacrea", nullable = false, length = 255)
    private String createdUser;

    @Column(name = "datecreate", nullable = false)
    private Timestamp createdDate;

    @Column(name = "usuamodif", length = 255)
    private String modifiedUser;

    @Column(name = "datemodif")
    private Timestamp modifiedDate;

    @Column(name = "usuadelet", length = 255)
    private String deletedUser;

    @Column(name = "datedelet")
    private Timestamp deletedDate;
}
