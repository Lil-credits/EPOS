package io.epos.portal_api.w3ih.models.openbadges;

/**
 * An address for the described entity.
 */
public class Address {
    private String addressCountry;
    private String addressCountryCode;
    private String addressLocality;
    private String addressRegion;
    private Geo geo;
    private String postalCode;
    private String postOfficeBoxNumber;
    private String streetAddress;
    private String[] type;

    /**
     * A country.
     */
    public String getAddressCountry() { return addressCountry; }
    public void setAddressCountry(String value) { this.addressCountry = value; }

    /**
     * A country code. The value must be a ISO 3166-1 alpha-2 country code [[ISO3166-1]].
     */
    public String getAddressCountryCode() { return addressCountryCode; }
    public void setAddressCountryCode(String value) { this.addressCountryCode = value; }

    /**
     * A locality within the region.
     */
    public String getAddressLocality() { return addressLocality; }
    public void setAddressLocality(String value) { this.addressLocality = value; }

    /**
     * A region within the country.
     */
    public String getAddressRegion() { return addressRegion; }
    public void setAddressRegion(String value) { this.addressRegion = value; }

    public Geo getGeo() { return geo; }
    public void setGeo(Geo value) { this.geo = value; }

    /**
     * A postal code.
     */
    public String getPostalCode() { return postalCode; }
    public void setPostalCode(String value) { this.postalCode = value; }

    /**
     * A post office box number for PO box addresses.
     */
    public String getPostOfficeBoxNumber() { return postOfficeBoxNumber; }
    public void setPostOfficeBoxNumber(String value) { this.postOfficeBoxNumber = value; }

    /**
     * A street address within the locality.
     */
    public String getStreetAddress() { return streetAddress; }
    public void setStreetAddress(String value) { this.streetAddress = value; }

    public String[] getType() { return type; }
    public void setType(String[] value) { this.type = value; }
}
