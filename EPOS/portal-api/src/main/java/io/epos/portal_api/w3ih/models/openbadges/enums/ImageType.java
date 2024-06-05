package io.epos.portal_api.w3ih.models.openbadges.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.io.IOException;

/**
 * MUST be the IRI 'Image'.
 */
public enum ImageType {
    IMAGE;

    @JsonValue
    public String toValue() {
        switch (this) {
            case IMAGE: return "Image";
        }
        return null;
    }

    @JsonCreator
    public static ImageType forValue(String value) throws IOException {
        if (value.equals("Image")) return IMAGE;
        throw new IOException("Cannot deserialize ImageType");
    }
}
