package io.epos.portal_api.w3ih.models.openbadges;

import java.util.Map;

/**
 * The information in RefreshService is used to refresh the verifiable credential.
 */
public class RefreshService {
    private String id;
    private String type;

    /**
     * The value MUST be the URL of the issuer's refresh service.
     */
    public String getID() { return id; }
    public void setID(String value) { this.id = value; }

    /**
     * The name of the refresh service method.
     */
    public String getType() { return type; }
    public void setType(String value) { this.type = value; }
}
