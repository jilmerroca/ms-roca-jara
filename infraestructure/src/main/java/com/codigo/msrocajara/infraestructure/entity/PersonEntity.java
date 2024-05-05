package com.codigo.msrocajara.infraestructure.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name = "persona")
@Getter
@Setter
public class PersonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre", nullable = false, length = 255)
    private String name;

    @Column(name = "apellido", nullable = false, length = 255)
    private String lastname;

    @Column(name = "tipodocumento", nullable = false, length = 5)
    private String identificationType;

    @Column(name = "numerodocumento", nullable = false, length = 15, unique = true)
    private String identificationNumber;

    @Email
    @Column(name = "email", nullable = false, length = 255, unique = true)
    private String email;

    @Column(name = "telefono", length = 15)
    private String phone;

    @Lob
    @Column(name = "direccion")
    private String address;

    @Min(0) @Max(1)
    @Column(name = "estado", nullable = false)
    private Integer status;

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

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa_id", nullable = false)
    private CompanyEntity company;
}
