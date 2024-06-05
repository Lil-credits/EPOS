package io.epos.portal_api.w3ih.models.openbadges.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.io.IOException;

/**
 * MUST be the IRI 'IdentityObject'.
 */
public enum IdentifierType {
    IDENTITY_OBJECT;

    @JsonValue
    public String toValue() {
        switch (this) {
            case IDENTITY_OBJECT: return "IdentityObject";
        }
        return null;
    }

    @JsonCreator
    public static IdentifierType forValue(String value) throws IOException {
        if (value.equals("IdentityObject")) return IDENTITY_OBJECT;
        throw new IOException("Cannot deserialize IdentifierType");
    }
}
