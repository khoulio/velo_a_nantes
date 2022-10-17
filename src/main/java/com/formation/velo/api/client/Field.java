package com.formation.velo.api.client;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Field {

    //STATION
    @SerializedName("available_bike_stands")
    private int availableBikeStands;
    @SerializedName("available_bikes")
    private int availableBikes;
    @SerializedName("bike_stands")
    private int bikeStands;
    private int number;
    private String address;
    private String name;
    private String status;
    //index 0 latitude  , index 1 longitude
    private double[] position;


    //PUMP
    private String ville;
    private String pop;
    @SerializedName("reg_name")
    private String regName;
    @SerializedName("com_arm_code")
    private String comArmCode;
    @SerializedName("dep_name")
    private String depName;
    @SerializedName("prix_nom")
    private String prixNom;
    @SerializedName("com_code")
    private String comCode;
    @SerializedName("epci_name")
    private String epciName;
    @SerializedName("dep_code")
    private String depCode;
    @SerializedName("prix_id")
    private String prixId;
    @SerializedName("services_service")
    private String servicesService;
    @SerializedName("horaires_automate_24_24")
    private String horairesAutomate24_24;
    @SerializedName("com_arm_name")
    private String comArmName;
    @SerializedName("prix_maj")
    private Date prixMaj;
    @SerializedName("id")
    private String pumpId;
    @SerializedName("reg_code")
    private String regCode;
    private String adresse;
    private double[] geom;
    @SerializedName("epci_code")
    private String epciCode;
    private String cp;
    @SerializedName("prix_valeur")
    private double prixValeur;
    @SerializedName("com_name")
    private String comName;
}
