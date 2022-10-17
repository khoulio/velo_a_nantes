package com.formation.velo.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity

@Table(name = "stations")
public class Station implements Serializable {

	private static final long serialVersionUID = -767070904974486420L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String name;
	private String status;
	private String recordId;
	//index 0 latitude  , index 1 long
	private double latitude;
	private double longitude;
	private int bikeStands;
	private String address;
	private int availableBikes;
	private int availableBikeStands;




}
