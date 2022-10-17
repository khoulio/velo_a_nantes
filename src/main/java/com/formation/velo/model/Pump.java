package com.formation.velo.model;



import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity

@Table(name = "pumps")
public class Pump implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String ville;
    private String pop;
    private String regName;
    private String comArmCode;
    private String depName;
    private String prixNom;
    private String comCode;
    private String epciName;
    private String depCode;
    private String prixId;
    private String servicesService;
    private String horairesAutomate24_24;
    private String comArmName;
    private Date prixMaj;
    private String pumpId;
    private String regCode;
    private String adresse;
    private double latitude;
    private double longitude;
    private String epciCode;
    private String cp;
    private double prixValeur;
    private String comName;
    private String recordId;
}
