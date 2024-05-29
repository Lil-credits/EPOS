package io.epos.portal_api.w3ih.models.openbadges;

/**
 * Describes a rubric criterion level.
 */
public class RubricCriterionLevelElement {
    private AlignmentElement[] alignment;
    private String description;
    private String id;
    private String level;
    private String name;
    private String points;
    private String type;

    public AlignmentElement[] getAlignment() { return alignment; }
    public void setAlignment(AlignmentElement[] value) { this.alignment = value; }

    /**
     * Description of the rubric criterion level.
     */
    public String getDescription() { return description; }
    public void setDescription(String value) { this.description = value; }

    /**
     * The unique URI for this rubric criterion level. Required so a result can link to this
     * rubric criterion level.
     */
    public String getID() { return id; }
    public void setID(String value) { this.id = value; }

    /**
     * The rubric performance level in terms of success.
     */
    public String getLevel() { return level; }
    public void setLevel(String value) { this.level = value; }

    /**
     * The name of the rubric criterion level.
     */
    public String getName() { return name; }
    public void setName(String value) { this.name = value; }

    /**
     * The points associated with this rubric criterion level.
     */
    public String getPoints() { return points; }
    public void setPoints(String value) { this.points = value; }

    public String getType() { return type; }
    public void setType(String value) { this.type = value; }
}

