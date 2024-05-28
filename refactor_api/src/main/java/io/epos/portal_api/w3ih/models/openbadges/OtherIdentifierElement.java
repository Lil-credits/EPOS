package io.epos.portal_api.w3ih.models.openbadges;

import nl.hu.w3ih.models.openbadges.enums.OtherIdentifierType;

/**
 * No description supplied.
 */
public class OtherIdentifierElement {
    private String identifier;
    private String identifierType;
    private OtherIdentifierType type;

    /**
     * An identifier.
     */
    public String getIdentifier() { return identifier; }
    public void setIdentifier(String value) { this.identifier = value; }

    /**
     * The identifier type.
     */
    public String getIdentifierType() { return identifierType; }
    public void setIdentifierType(String value) { this.identifierType = value; }

    /**
     * The value of the type property MUST be an unordered set. One of the items MUST be the IRI
     * 'IdentifierEntry'.
     */
    public OtherIdentifierType getType() { return type; }
    public void setType(OtherIdentifierType value) { this.type = value; }
}

