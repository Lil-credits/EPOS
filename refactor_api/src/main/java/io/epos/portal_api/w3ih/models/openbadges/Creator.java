package io.epos.portal_api.w3ih.models.openbadges;

/**
 * A Profile is a collection of information that describes the entity or organization using
 * Open Badges. Issuers must be represented as Profiles, and endorsers, or other entities
 * may also be represented using this vocabulary. Each Profile that represents an Issuer may
 * be referenced in many BadgeClasses that it has defined. Anyone can create and host an
 * Issuer file to start issuing Open Badges. Issuers may also serve as recipients of Open
 * Badges, often identified within an Assertion by specific properties, like their url or
 * contact email address.
 */
public class Creator {
    private String additionalName;
    private Address address;
    private String dateOfBirth;
    private String description;
    private String email;
    private EndorsementElement[] endorsement;
    private String[] endorsementJwt;
    private String familyName;
    private String familyNamePrefix;
    private String givenName;
    private String honorificPrefix;
    private String honorificSuffix;
    private String id;
    private Image image;
    private String name;
    private String official;
    private OtherIdentifierElement[] otherIdentifier;
    private Creator parentOrg;
    private String patronymicName;
    private String phone;
    private String type;
    private String url;

    /**
     * Additional name. Includes what is often referred to as 'middle name' in the western world.
     */
    public String getAdditionalName() { return additionalName; }
    public void setAdditionalName(String value) { this.additionalName = value; }

    public Address getAddress() { return address; }
    public void setAddress(Address value) { this.address = value; }

    /**
     * Birthdate of the person.
     */
    public String getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(String value) { this.dateOfBirth = value; }

    /**
     * A short description of the issuer entity or organization.
     */
    public String getDescription() { return description; }
    public void setDescription(String value) { this.description = value; }

    /**
     * An email address.
     */
    public String getEmail() { return email; }
    public void setEmail(String value) { this.email = value; }

    public EndorsementElement[] getEndorsement() { return endorsement; }
    public void setEndorsement(EndorsementElement[] value) { this.endorsement = value; }

    public String[] getEndorsementJwt() { return endorsementJwt; }
    public void setEndorsementJwt(String[] value) { this.endorsementJwt = value; }

    /**
     * Family name. In the western world, often referred to as the 'last name' of a person.
     */
    public String getFamilyName() { return familyName; }
    public void setFamilyName(String value) { this.familyName = value; }

    /**
     * Family name prefix. As used in some locales, this is the leading part of a family name
     * (e.g. 'de' in the name 'de Boer').
     */
    public String getFamilyNamePrefix() { return familyNamePrefix; }
    public void setFamilyNamePrefix(String value) { this.familyNamePrefix = value; }

    /**
     * Given name. In the western world, often referred to as the 'first name' of a person.
     */
    public String getGivenName() { return givenName; }
    public void setGivenName(String value) { this.givenName = value; }

    /**
     * Honorific prefix(es) preceding a person's name (e.g. 'Dr', 'Mrs' or 'Mr').
     */
    public String getHonorificPrefix() { return honorificPrefix; }
    public void setHonorificPrefix(String value) { this.honorificPrefix = value; }

    /**
     * Honorific suffix(es) following a person's name (e.g. 'M.D, PhD').
     */
    public String getHonorificSuffix() { return honorificSuffix; }
    public void setHonorificSuffix(String value) { this.honorificSuffix = value; }

    /**
     * Unique URI for the Issuer/Profile file.
     */
    public String getID() { return id; }
    public void setID(String value) { this.id = value; }

    public Image getImage() { return image; }
    public void setImage(Image value) { this.image = value; }

    /**
     * The name of the entity or organization.
     */
    public String getName() { return name; }
    public void setName(String value) { this.name = value; }

    /**
     * If the entity is an organization, `official` is the name of an authorized official of the
     * organization.
     */
    public String getOfficial() { return official; }
    public void setOfficial(String value) { this.official = value; }

    public OtherIdentifierElement[] getOtherIdentifier() { return otherIdentifier; }
    public void setOtherIdentifier(OtherIdentifierElement[] value) { this.otherIdentifier = value; }

    public Creator getParentOrg() { return parentOrg; }
    public void setParentOrg(Creator value) { this.parentOrg = value; }

    /**
     * Patronymic name.
     */
    public String getPatronymicName() { return patronymicName; }
    public void setPatronymicName(String value) { this.patronymicName = value; }

    /**
     * A phone number.
     */
    public String getPhone() { return phone; }
    public void setPhone(String value) { this.phone = value; }

    public String getType() { return type; }
    public void setType(String value) { this.type = value; }

    /**
     * The homepage or social media profile of the entity, whether individual or institutional.
     * Should be a URL/URI Accessible via HTTP.
     */
    public String getURL() { return url; }
    public void setURL(String value) { this.url = value; }
}
