package io.epos.portal_api.w3ih.models.openbadges;

/**
 * Describes an alignment between an achievement and a node in an educational framework.
 */
public class AlignmentElement {
    private String targetCode;
    private String targetDescription;
    private String targetFramework;
    private String targetName;
    private String targetType;
    private String targetUrl;
    private String type;

    /**
     * If applicable, a locally unique string identifier that identifies the alignment target
     * within its framework and/or targetUrl.
     */
    public String getTargetCode() { return targetCode; }
    public void setTargetCode(String value) { this.targetCode = value; }

    /**
     * Short description of the alignment target.
     */
    public String getTargetDescription() { return targetDescription; }
    public void setTargetDescription(String value) { this.targetDescription = value; }

    /**
     * Name of the framework the alignment target.
     */
    public String getTargetFramework() { return targetFramework; }
    public void setTargetFramework(String value) { this.targetFramework = value; }

    /**
     * Name of the alignment.
     */
    public String getTargetName() { return targetName; }
    public void setTargetName(String value) { this.targetName = value; }

    /**
     * The type of the alignment target node.
     */
    public String getTargetType() { return targetType; }
    public void setTargetType(String value) { this.targetType = value; }

    /**
     * URL linking to the official description of the alignment target, for example an
     * individual standard within an educational framework.
     */
    public String getTargetUrl() { return targetUrl; }
    public void setTargetUrl(String value) { this.targetUrl = value; }

    public String getType() { return type; }
    public void setType(String value) { this.type = value; }
}
