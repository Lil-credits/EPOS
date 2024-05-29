package io.epos.portal_api.w3ih.models.openbadges.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.io.IOException;

public enum GeoType {
    GEO_COORDINATES;

    @JsonValue
    public String toValue() {
        switch (this) {
            case GEO_COORDINATES: return "GeoCoordinates";
        }
        return null;
    }

    @JsonCreator
    public static GeoType forValue(String value) throws IOException {
        if (value.equals("GeoCoordinates")) return GEO_COORDINATES;
        throw new IOException("Cannot deserialize GeoType");
    }
}
