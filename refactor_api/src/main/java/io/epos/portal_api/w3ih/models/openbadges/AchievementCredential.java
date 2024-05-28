package io.epos.portal_api.w3ih.models.openbadges;

import com.fasterxml.jackson.annotation.JsonAlias;

/**
 * AchievementCredentials are representations of an awarded achievement, used to share
 * information about a achievement belonging to one earner. Maps to a Verifiable Credential
 * as defined in the [[VC-DATA-MODEL-2.0]]. As described in [[[#data-integrity]]], at least
 * one proof mechanism, and the details necessary to evaluate that proof, MUST be expressed
 * for a credential to be a verifiable credential. In the case of an embedded proof, the
 * credential MUST append the proof in the `proof` property.
 */
public class AchievementCredential {
    @JsonAlias("@context")
    private String[] context;
    private String awardedDate;

    private CredentialSchemaElement[] credentialSchema;
    private CredentialStatus credentialStatus;
    private CredentialSubject credentialSubject;
    private String description;
    private EndorsementElement[] endorsement;
    private String[] endorsementJwt;
    private EvidenceElement[] evidence;
    private String expirationDate;
    private String id;
    private String issuanceDate;
    private Image image;
    private Creator issuer;
    private String name;
    private ProofElement[] proof;
    private RefreshService refreshService;
    private TermsOfUseElement[] termsOfUse;
    private String[] type;
    private String validFrom;
    private String validUntil;

    public String[] getContext() { return context; }
    public void setContext(String[] value) { this.context = value; }

    /**
     * Timestamp of when the credential was awarded. `validFrom` is used to determine the most
     * recent version of a Credential in conjunction with `issuer` and `id`. Consequently, the
     * only way to update a Credental is to update the `validFrom`, losing the date when the
     * Credential was originally awarded. `awardedDate` is meant to keep this original date.
     */
    public String getAwardedDate() { return awardedDate; }
    public void setAwardedDate(String value) { this.awardedDate = value; }

    public CredentialSchemaElement[] getCredentialSchema() { return credentialSchema; }
    public void setCredentialSchema(CredentialSchemaElement[] value) { this.credentialSchema = value; }

    public CredentialStatus getCredentialStatus() { return credentialStatus; }
    public void setCredentialStatus(CredentialStatus value) { this.credentialStatus = value; }

    public CredentialSubject getCredentialSubject() { return credentialSubject; }
    public void setCredentialSubject(CredentialSubject value) { this.credentialSubject = value; }

    /**
     * The short description of the credential for display purposes in wallets.
     */
    public String getDescription() { return description; }
    public void setDescription(String value) { this.description = value; }

    public EndorsementElement[] getEndorsement() { return endorsement; }
    public void setEndorsement(EndorsementElement[] value) { this.endorsement = value; }

    public String[] getEndorsementJwt() { return endorsementJwt; }
    public void setEndorsementJwt(String[] value) { this.endorsementJwt = value; }

    public EvidenceElement[] getEvidence() { return evidence; }
    public void setEvidence(EvidenceElement[] value) { this.evidence = value; }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    /**
     * Unambiguous reference to the credential.
     */
    public String getID() { return id; }
    public void setID(String value) { this.id = value; }

    public String getIssuanceDate() {
        return issuanceDate;
    }

    public void setIssuanceDate(String issuanceDate) {
        this.issuanceDate = issuanceDate;
    }

    public Image getImage() { return image; }
    public void setImage(Image value) { this.image = value; }

    public Creator getIssuer() { return issuer; }
    public void setIssuer(Creator value) { this.issuer = value; }

    /**
     * The name of the credential for display purposes in wallets. For example, in a list of
     * credentials and in detail views.
     */
    public String getName() { return name; }
    public void setName(String value) { this.name = value; }

    public ProofElement[] getProof() { return proof; }
    public void setProof(ProofElement[] value) { this.proof = value; }

    public RefreshService getRefreshService() { return refreshService; }
    public void setRefreshService(RefreshService value) { this.refreshService = value; }

    public TermsOfUseElement[] getTermsOfUse() { return termsOfUse; }
    public void setTermsOfUse(TermsOfUseElement[] value) { this.termsOfUse = value; }

    public String[] getType() { return type; }
    public void setType(String[] value) { this.type = value; }

    /**
     * Timestamp of when the credential becomes valid.
     */
    public String getValidFrom() { return validFrom; }
    public void setValidFrom(String value) { this.validFrom = value; }

    /**
     * If the credential has some notion of validity period, this indicates a timestamp when a
     * credential should no longer be considered valid. After this time, the credential should
     * be considered invalid.
     */
    public String getValidUntil() { return validUntil; }
    public void setValidUntil(String value) { this.validUntil = value; }
}
