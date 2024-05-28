package io.epos.portal_api.w3ih.models.openbadges;

import nl.hu.w3ih.models.openbadges.enums.Status;

/**
 * Describes a result that was achieved.
 */
public class ResultElement {
    private String achievedLevel;
    private AlignmentElement[] alignment;
    private String resultDescription;
    private Status status;
    private String[] type;
    private String value;

    /**
     * If the result represents an achieved rubric criterion level (e.g. Mastered), the value is
     * the `id` of the RubricCriterionLevel in linked ResultDescription.
     */
    public String getAchievedLevel() { return achievedLevel; }
    public void setAchievedLevel(String value) { this.achievedLevel = value; }

    public AlignmentElement[] getAlignment() { return alignment; }
    public void setAlignment(AlignmentElement[] value) { this.alignment = value; }

    /**
     * An achievement can have many result descriptions describing possible results. The value
     * of `resultDescription` is the `id` of the result description linked to this result. The
     * linked result description must be in the achievement that is being asserted.
     */
    public String getResultDescription() { return resultDescription; }
    public void setResultDescription(String value) { this.resultDescription = value; }

    /**
     * The status of the achievement. Required if `resultType` of the linked ResultDescription
     * is Status.
     */
    public Status getStatus() { return status; }
    public void setStatus(Status value) { this.status = value; }

    public String[] getType() { return type; }
    public void setType(String[] value) { this.type = value; }

    /**
     * A string representing the result of the performance, or demonstration, of the
     * achievement. For example, 'A' if the recipient received an A grade in class.
     */
    public String getValue() { return value; }
    public void setValue(String value) { this.value = value; }
}
