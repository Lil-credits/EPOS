package io.epos.portal_api.w3ih.models.openbadges;

/**
 * Identify the type and location of a data schema.
 */
public class CredentialSchemaElement {
    private String id;
    private String type;

    /**
     * The value MUST be a URI identifying the schema file. One instance of `CredentialSchema`
     * MUST have an `id` that is the URL of the JSON Schema for this credential defined by this
     * specification.
     */
    public String getID() { return id; }
    public void setID(String value) { this.id = value; }

    /**
     * The value MUST identify the type of data schema validation. One instance of
     * `CredentialSchema` MUST have a `type` of 'JsonSchemaValidator2019'.
     */
    public String getType() { return type; }
    public void setType(String value) { this.type = value; }
}
