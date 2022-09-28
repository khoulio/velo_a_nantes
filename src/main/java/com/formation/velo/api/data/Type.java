package com.formation.velo.api.data;

import java.io.IOException;

public enum Type {
    POINT;

    public String toValue() {
        switch (this) {
            case POINT: return "Point";
        }
        return null;
    }

    public static Type forValue(String value) throws IOException {
        if (value.equals("Point")) return POINT;
        throw new IOException("Cannot deserialize Type");
    }
}
