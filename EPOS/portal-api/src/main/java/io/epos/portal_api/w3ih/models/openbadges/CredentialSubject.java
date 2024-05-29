package io.epos.portal_api.w3ih.models.openbadges;

/**
 * A collection of information about the recipient of an achievement. Maps to Credential
 * Subject in [[VC-DATA-MODEL-2.0]].
 */
public class CredentialSubject {
    private Achievement achievement;
    private String activityEndDate;
    private String activityStartDate;
    private Double creditsEarned;
    private String id;
    private IdentifierElement[] identifier;
    private Image image;
    private String licenseNumber;
    private String narrative;
    private ResultElement[] result;
    private String role;
    private Creator source;
    private String term;
    private String type;

    public Achievement getAchievement() { return achievement; }
    public void setAchievement(Achievement value) { this.achievement = value; }

    /**
     * The datetime the activity ended.
     */
    public String getActivityEndDate() { return activityEndDate; }
    public void setActivityEndDate(String value) { this.activityEndDate = value; }

    /**
     * The datetime the activity started.
     */
    public String getActivityStartDate() { return activityStartDate; }
    public void setActivityStartDate(String value) { this.activityStartDate = value; }

    /**
     * The number of credits earned, generally in semester or quarter credit hours. This field
     * correlates with the Achievement `creditsAvailable` field.
     */
    public Double getCreditsEarned() { return creditsEarned; }
    public void setCreditsEarned(Double value) { this.creditsEarned = value; }

    /**
     * An identifier for the Credential Subject. Either `id` or at least one `identifier` MUST
     * be supplied.
     */
    public String getID() { return id; }
    public void setID(String value) { this.id = value; }

    public IdentifierElement[] getIdentifier() { return identifier; }
    public void setIdentifier(IdentifierElement[] value) { this.identifier = value; }

    public Image getImage() { return image; }
    public void setImage(Image value) { this.image = value; }

    /**
     * The license number that was issued with this credential.
     */
    public String getLicenseNumber() { return licenseNumber; }
    public void setLicenseNumber(String value) { this.licenseNumber = value; }

    /**
     * A narrative that connects multiple pieces of evidence. Likely only present at this
     * location if evidence is a multi-value array.
     */
    public String getNarrative() { return narrative; }
    public void setNarrative(String value) { this.narrative = value; }

    public ResultElement[] getResult() { return result; }
    public void setResult(ResultElement[] value) { this.result = value; }

    /**
     * Role, position, or title of the learner when demonstrating or performing the achievement
     * or evidence of learning being asserted. Examples include 'Student President', 'Intern',
     * 'Captain', etc.
     */
    public String getRole() { return role; }
    public void setRole(String value) { this.role = value; }

    public Creator getSource() { return source; }
    public void setSource(Creator value) { this.source = value; }

    /**
     * The academic term in which this assertion was achieved.
     */
    public String getTerm() { return term; }
    public void setTerm(String value) { this.term = value; }

    public String getType() { return type; }
    public void setType(String value) { this.type = value; }
}
