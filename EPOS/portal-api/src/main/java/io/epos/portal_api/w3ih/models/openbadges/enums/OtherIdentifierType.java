package io.epos.portal_api.w3ih.models.openbadges.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.io.IOException;

/**
 * The value of the type property MUST be an unordered set. One of the items MUST be the IRI
 * 'IdentifierEntry'.
 */
public enum OtherIdentifierType {
    IDENTIFIER_ENTRY;

    @JsonValue
    public String toValue() {
        switch (this) {
            case IDENTIFIER_ENTRY: return "IdentifierEntry";
        }
        return null;
    }

    @JsonCreator
    public static OtherIdentifierType forValue(String value) throws IOException {
        if (value.equals("IdentifierEntry")) return IDENTIFIER_ENTRY;
        throw new IOException("Cannot deserialize OtherIdentifierType");
    }
}
