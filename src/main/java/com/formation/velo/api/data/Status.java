package com.formation.velo.api.data;

import java.io.IOException;

public enum Status {
    CLOSED, OPEN;

    public String toValue() {
        switch (this) {
            case CLOSED: return "CLOSED";
            case OPEN: return "OPEN";
        }
        return null;
    }

    public static Status forValue(String value) throws IOException {
        if (value.equals("CLOSED")) return CLOSED;
        if (value.equals("OPEN")) return OPEN;
        throw new IOException("Cannot deserialize Status");
    }
}
