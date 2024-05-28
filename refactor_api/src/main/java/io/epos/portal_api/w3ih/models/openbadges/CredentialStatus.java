package io.epos.portal_api.w3ih.models.openbadges;

import java.util.Map;

/**
 * The information in CredentialStatus is used to discover information about the current
 * status of a verifiable credential, such as whether it is suspended or revoked.
 */
public class CredentialStatus {
    private String id;
    private String type;

    /**
     * The value MUST be the URL of the issuer's credential status method.
     */
    public String getID() { return id; }
    public void setID(String value) { this.id = value; }

    /**
     * The name of the credential status method.
     */
    public String getType() { return type; }
    public void setType(String value) { this.type = value; }
}
