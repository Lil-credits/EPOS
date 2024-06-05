package io.epos.portal_api.w3ih.models.openbadges;

/**
 * Terms of use can be utilized by an issuer or a holder to communicate the terms under
 * which a verifiable credential or verifiable presentation was issued
 */
public class TermsOfUseElement {
    private String id;
    private String type;

    /**
     * The value MUST be a URI identifying the term of use.
     */
    public String getID() { return id; }
    public void setID(String value) { this.id = value; }

    /**
     * The value MUST identify the type of the terms of use.
     */
    public String getType() { return type; }
    public void setType(String value) { this.type = value; }
}
