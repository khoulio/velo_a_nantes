package com.formation.velo.api.data;

import java.io.IOException;

public enum ContractName {
    NANTES;

    public String toValue() {
        switch (this) {
            case NANTES: return "nantes";
        }
        return null;
    }

    public static ContractName forValue(String value) throws IOException {
        if (value.equals("nantes")) return NANTES;
        throw new IOException("Cannot deserialize ContractName");
    }
}
