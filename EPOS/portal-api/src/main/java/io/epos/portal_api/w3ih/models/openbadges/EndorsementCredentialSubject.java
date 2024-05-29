package io.epos.portal_api.w3ih.models.openbadges;

/**
 * A collection of information about the subject of the endorsement.
 */
public class EndorsementCredentialSubject {
    private String endorsementComment;
    private String id;
    private String type;

    /**
     * Allows endorsers to make a simple claim in writing about the entity.
     */
    public String getEndorsementComment() { return endorsementComment; }
    public void setEndorsementComment(String value) { this.endorsementComment = value; }

    /**
     * The identifier of the individual, entity, organization, assertion, or achievement that is
     * endorsed.
     */
    public String getID() { return id; }
    public void setID(String value) { this.id = value; }

    public String getType() { return type; }
    public void setType(String value) { this.type = value; }
}
