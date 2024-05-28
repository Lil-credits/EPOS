package io.epos.portal_api.w3ih.models.openbadges;

/**
 * Identifies a related achievement.
 */
public class RelatedElement {
    private String id;
    private String inLanguage;
    private String[] type;
    private String version;

    /**
     * The related achievement.
     */
    public String getID() { return id; }
    public void setID(String value) { this.id = value; }

    /**
     * The language of the related achievement.
     */
    public String getInLanguage() { return inLanguage; }
    public void setInLanguage(String value) { this.inLanguage = value; }

    public String[] getType() { return type; }
    public void setType(String[] value) { this.type = value; }

    /**
     * The version of the related achievement.
     */
    public String getVersion() { return version; }
    public void setVersion(String value) { this.version = value; }
}
