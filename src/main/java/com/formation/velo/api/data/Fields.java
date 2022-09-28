package com.formation.velo.api.data;

import java.time.OffsetDateTime;
import java.util.Date;

@lombok.Data
public class Fields {
    private long available_bike_stands;
    private long bike_stands;
    private long number;
    private String address;
    private String name;
    private Banking bonus;
    private Banking banking;
    private ContractName contract_name;
    private Status status;
    private long available_bikes;
    private double[] position;
    private Date last_update;
}
