package com.codigo.rocajara.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name = "empresa")
@Getter
@Setter
public class EmpresaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Razón social es requerido.")
    @Column(name = "razon_social", length = 255)
    private String razonSocial;

    @NotBlank(message = "Tipo de documento es requerido.")
    @Column(name = "tipo_documento", length = 1)
    private String tipoDocumento;

    @NotBlank(message = "Número de documento es requerido.")
    @Column(name = "numero_documento", length = 11)
    private String numeroDocumento;

    @NotBlank(message = "Condición es requerido.")
    @Column(name = "condicion", length = 10)
    private String condicion;

    @NotBlank(message = "Direccion es requerido.")
    @Column(name = "direccion", length = 255)
    private String direccion;

    @NotBlank(message = "Distrito es requerido.")
    @Column(name = "distrito", length = 255)
    private String distrito;

    @NotBlank(message = "Provincia es requerido.")
    @Column(name = "provincia", length = 255)
    private String provincia;

    @NotBlank(message = "Departamento es requerido.")
    @Column(name = "departamento", length = 255)
    private String departamento;

    @Column(name = "es_agente_retencion")
    private boolean esAgenteRetencion;

    @Max(value = 1, message = "El valor máximo es 1.")
    @Column(name = "estado", nullable = false)
    private int estado;

    @Column(name = "usua_crea", length = 50)
    private String usuaCreate;

    @Column(name = "date_create")
    private Timestamp dateCreate;

    @Column(name = "usua_modif", length = 50)
    private String usuaModif;

    @Column(name = "date_modif")
    private Timestamp dateModif;

    @Column(name = "usua_delet", length = 50)
    private String usuaDelet;

    @Column(name = "date_delet")
    private Timestamp dateDelet;
}
