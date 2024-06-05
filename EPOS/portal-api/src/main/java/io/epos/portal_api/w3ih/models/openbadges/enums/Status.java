package io.epos.portal_api.w3ih.models.openbadges.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.io.IOException;

/**
 * The status of the achievement. Required if `resultType` of the linked ResultDescription
 * is Status.
 */
public enum Status {
    COMPLETED, ENROLLED, FAILED, IN_PROGRESS, ON_HOLD, PROVISIONAL, WITHDREW;

    @JsonValue
    public String toValue() {
        switch (this) {
            case COMPLETED: return "Completed";
            case ENROLLED: return "Enrolled";
            case FAILED: return "Failed";
            case IN_PROGRESS: return "InProgress";
            case ON_HOLD: return "OnHold";
            case PROVISIONAL: return "Provisional";
            case WITHDREW: return "Withdrew";
        }
        return null;
    }

    @JsonCreator
    public static Status forValue(String value) throws IOException {
        if (value.equals("Completed")) return COMPLETED;
        if (value.equals("Enrolled")) return ENROLLED;
        if (value.equals("Failed")) return FAILED;
        if (value.equals("InProgress")) return IN_PROGRESS;
        if (value.equals("OnHold")) return ON_HOLD;
        if (value.equals("Provisional")) return PROVISIONAL;
        if (value.equals("Withdrew")) return WITHDREW;
        throw new IOException("Cannot deserialize Status");
    }
}
