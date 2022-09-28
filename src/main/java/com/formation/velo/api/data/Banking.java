package com.formation.velo.api.data;

import java.io.IOException;

public enum Banking {
    FALSE, TRUE;

    public String toValue() {
        switch (this) {
            case FALSE: return "False";
            case TRUE: return "True";
        }
        return null;
    }

    public static Banking forValue(String value) throws IOException {
        if (value.equals("False")) return FALSE;
        if (value.equals("True")) return TRUE;
        throw new IOException("Cannot deserialize Banking");
    }
}
