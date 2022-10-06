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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;
	private String status;
	private String recordid;
	private double lattitude;
	private double longitude;
	private int bike_stands;
	private int available_bikes;
	private int available_bike_stands;




}
