package io.epos.portal_api.w3ih.models.openbadges;

import io.epos.portal_api.w3ih.models.openbadges.enums.GeoType;

/**
 * The geographic coordinates of a location.
 */
public class Geo {
    private double latitude;
    private double longitude;
    private GeoType type;

    /**
     * The latitude of the location [[WGS84]].
     */
    public double getLatitude() { return latitude; }
    public void setLatitude(double value) { this.latitude = value; }

    /**
     * The longitude of the location [[WGS84]].
     */
    public double getLongitude() { return longitude; }
    public void setLongitude(double value) { this.longitude = value; }

    /**
     * The value of the type property MUST be an unordered set. One of the items MUST be the IRI
     * 'GeoCoordinates'.
     */
    public GeoType getType() { return type; }
    public void setType(GeoType value) { this.type = value; }
}
