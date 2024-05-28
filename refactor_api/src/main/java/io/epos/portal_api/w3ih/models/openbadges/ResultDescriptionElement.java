package io.epos.portal_api.w3ih.models.openbadges;

/**
 * Describes a possible achievement result.
 */
public class ResultDescriptionElement {
    private AlignmentElement[] alignment;
    private String[] allowedValue;
    private String id;
    private String name;
    private String requiredLevel;
    private String requiredValue;
    private String resultType;
    private RubricCriterionLevelElement[] rubricCriterionLevel;
    private String type;
    private String valueMax;
    private String valueMin;

    public AlignmentElement[] getAlignment() { return alignment; }
    public void setAlignment(AlignmentElement[] value) { this.alignment = value; }

    public String[] getAllowedValue() { return allowedValue; }
    public void setAllowedValue(String[] value) { this.allowedValue = value; }

    /**
     * The unique URI for this result description. Required so a result can link to this result
     * description.
     */
    public String getID() { return id; }
    public void setID(String value) { this.id = value; }

    /**
     * The name of the result.
     */
    public String getName() { return name; }
    public void setName(String value) { this.name = value; }

    /**
     * The `id` of the rubric criterion level required to pass as determined by the achievement
     * creator.
     */
    public String getRequiredLevel() { return requiredLevel; }
    public void setRequiredLevel(String value) { this.requiredLevel = value; }

    /**
     * A value from `allowedValue` or within the range of `valueMin` to `valueMax` required to
     * pass as determined by the achievement creator.
     */
    public String getRequiredValue() { return requiredValue; }
    public void setRequiredValue(String value) { this.requiredValue = value; }

    /**
     * The type of result this description represents. This is an extensible enumerated
     * vocabulary.
     */
    public String getResultType() { return resultType; }
    public void setResultType(String value) { this.resultType = value; }

    public RubricCriterionLevelElement[] getRubricCriterionLevel() { return rubricCriterionLevel; }
    public void setRubricCriterionLevel(RubricCriterionLevelElement[] value) { this.rubricCriterionLevel = value; }

    public String getType() { return type; }
    public void setType(String value) { this.type = value; }

    /**
     * The maximum possible `value` that may be asserted in a linked result.
     */
    public String getValueMax() { return valueMax; }
    public void setValueMax(String value) { this.valueMax = value; }

    /**
     * The minimum possible `value` that may be asserted in a linked result.
     */
    public String getValueMin() { return valueMin; }
    public void setValueMin(String value) { this.valueMin = value; }
}
