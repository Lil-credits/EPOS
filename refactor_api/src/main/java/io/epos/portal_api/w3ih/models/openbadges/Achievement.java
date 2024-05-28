package io.epos.portal_api.w3ih.models.openbadges;

/**
 * A collection of information about the accomplishment recognized by the Assertion. Many
 * assertions may be created corresponding to one Achievement.
 */
public class Achievement {
    private String achievementType;
    private AlignmentElement[] alignment;
    private Creator creator;
    private Double creditsAvailable;
    private Criteria criteria;
    private String description;
    private EndorsementElement[] endorsement;
    private String[] endorsementJwt;
    private String fieldOfStudy;
    private String humanCode;
    private String id;
    private Image image;
    private String inLanguage;
    private String name;
    private OtherIdentifierElement[] otherIdentifier;
    private RelatedElement[] related;
    private ResultDescriptionElement[] resultDescription;
    private String specialization;
    private String[] tag;
    private String type;
    private String version;

    /**
     * The type of achievement. This is an extensible vocabulary.
     */
    public String getAchievementType() { return achievementType; }
    public void setAchievementType(String value) { this.achievementType = value; }

    public AlignmentElement[] getAlignment() { return alignment; }
    public void setAlignment(AlignmentElement[] value) { this.alignment = value; }

    public Creator getCreator() { return creator; }
    public void setCreator(Creator value) { this.creator = value; }

    /**
     * Credit hours associated with this entity, or credit hours possible. For example 3.0.
     */
    public Double getCreditsAvailable() { return creditsAvailable; }
    public void setCreditsAvailable(Double value) { this.creditsAvailable = value; }

    public Criteria getCriteria() { return criteria; }
    public void setCriteria(Criteria value) { this.criteria = value; }

    /**
     * A short description of the achievement.
     */
    public String getDescription() { return description; }
    public void setDescription(String value) { this.description = value; }

    public EndorsementElement[] getEndorsement() { return endorsement; }
    public void setEndorsement(EndorsementElement[] value) { this.endorsement = value; }

    public String[] getEndorsementJwt() { return endorsementJwt; }
    public void setEndorsementJwt(String[] value) { this.endorsementJwt = value; }

    /**
     * Category, subject, area of study, discipline, or general branch of knowledge. Examples
     * include Business, Education, Psychology, and Technology.
     */
    public String getFieldOfStudy() { return fieldOfStudy; }
    public void setFieldOfStudy(String value) { this.fieldOfStudy = value; }

    /**
     * The code, generally human readable, associated with an achievement.
     */
    public String getHumanCode() { return humanCode; }
    public void setHumanCode(String value) { this.humanCode = value; }

    /**
     * Unique URI for the Achievement.
     */
    public String getID() { return id; }
    public void setID(String value) { this.id = value; }

    public Image getImage() { return image; }
    public void setImage(Image value) { this.image = value; }

    /**
     * The language of the achievement.
     */
    public String getInLanguage() { return inLanguage; }
    public void setInLanguage(String value) { this.inLanguage = value; }

    /**
     * The name of the achievement.
     */
    public String getName() { return name; }
    public void setName(String value) { this.name = value; }

    public OtherIdentifierElement[] getOtherIdentifier() { return otherIdentifier; }
    public void setOtherIdentifier(OtherIdentifierElement[] value) { this.otherIdentifier = value; }

    public RelatedElement[] getRelated() { return related; }
    public void setRelated(RelatedElement[] value) { this.related = value; }

    public ResultDescriptionElement[] getResultDescription() { return resultDescription; }
    public void setResultDescription(ResultDescriptionElement[] value) { this.resultDescription = value; }

    /**
     * Name given to the focus, concentration, or specific area of study defined in the
     * achievement. Examples include 'Entrepreneurship', 'Technical Communication', and
     * 'Finance'.
     */
    public String getSpecialization() { return specialization; }
    public void setSpecialization(String value) { this.specialization = value; }

    public String[] getTag() { return tag; }
    public void setTag(String[] value) { this.tag = value; }

    public String getType() { return type; }
    public void setType(String value) { this.type = value; }

    /**
     * The version property allows issuers to set a version string for an Achievement. This is
     * particularly useful when replacing a previous version with an update.
     */
    public String getVersion() { return version; }
    public void setVersion(String value) { this.version = value; }
}
