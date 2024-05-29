package io.epos.portal_api.w3ih.models.openbadges;

/**
 * A JSON-LD Linked Data proof.
 */
public class ProofElement {
    private String challenge;
    private String created;
    private String cryptosuite;
    private String domain;
    private String nonce;
    private String proofPurpose;
    private String proofValue;
    private String type;
    private String verificationMethod;

    /**
     * A value chosen by the verifier to mitigate authentication proof replay attacks.
     */
    public String getChallenge() { return challenge; }
    public void setChallenge(String value) { this.challenge = value; }

    /**
     * Date the proof was created.
     */
    public String getCreated() { return created; }
    public void setCreated(String value) { this.created = value; }

    /**
     * The suite used to create the proof.
     */
    public String getCryptosuite() { return cryptosuite; }
    public void setCryptosuite(String value) { this.cryptosuite = value; }

    /**
     * The domain of the proof to restrict its use to a particular target.
     */
    public String getDomain() { return domain; }
    public void setDomain(String value) { this.domain = value; }

    /**
     * A value chosen by the creator of proof to randomize proof values for privacy purposes.
     */
    public String getNonce() { return nonce; }
    public void setNonce(String value) { this.nonce = value; }

    /**
     * The purpose of the proof to be used with `verificationMethod`. MUST be 'assertionMethod'.
     */
    public String getProofPurpose() { return proofPurpose; }
    public void setProofPurpose(String value) { this.proofPurpose = value; }

    /**
     * Value of the proof.
     */
    public String getProofValue() { return proofValue; }
    public void setProofValue(String value) { this.proofValue = value; }

    /**
     * Signature suite used to produce proof.
     */
    public String getType() { return type; }
    public void setType(String value) { this.type = value; }

    /**
     * The URL of the public key that can verify the signature.
     */
    public String getVerificationMethod() { return verificationMethod; }
    public void setVerificationMethod(String value) { this.verificationMethod = value; }
}
