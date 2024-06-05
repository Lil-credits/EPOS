package io.epos.portal_api.w3ih.models.openbadges;


import io.epos.portal_api.w3ih.models.openbadges.enums.IdentifierType;

/**
 * A collection of information about the recipient of an achievement.
 */
public class IdentifierElement {
    private boolean hashed;
    private String identityHash;
    private String identityType;
    private String salt;
    private IdentifierType type;

    /**
     * Whether or not the `identityHash` value is hashed.
     */
    public boolean getHashed() { return hashed; }
    public void setHashed(boolean value) { this.hashed = value; }

    /**
     * Either the IdentityHash of the identity or the plaintext value. If it's possible that the
     * plaintext transmission and storage of the identity value would leak personally
     * identifiable information where there is an expectation of privacy, it is strongly
     * recommended that an IdentityHash be used.
     */
    public String getIdentityHash() { return identityHash; }
    public void setIdentityHash(String value) { this.identityHash = value; }

    /**
     * The identity type.
     */
    public String getIdentityType() { return identityType; }
    public void setIdentityType(String value) { this.identityType = value; }

    /**
     * If the `identityHash` is hashed, this should contain the string used to salt the hash. If
     * this value is not provided, it should be assumed that the hash was not salted.
     */
    public String getSalt() { return salt; }
    public void setSalt(String value) { this.salt = value; }

    /**
     * MUST be the IRI 'IdentityObject'.
     */
    public IdentifierType getType() { return type; }
    public void setType(IdentifierType value) { this.type = value; }
}
