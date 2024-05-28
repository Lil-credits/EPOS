package io.epos.portal_api.w3ih.models.openbadges;

import nl.hu.w3ih.models.openbadges.enums.ImageType;

/**
 * Metadata about images that represent assertions, achieve or profiles. These properties
 * can typically be represented as just the id string of the image, but using a fleshed-out
 * document allows for including captions and other applicable metadata.
 */
public class Image {
    private String caption;
    private String id;
    private ImageType type;

    /**
     * The caption for the image.
     */
    public String getCaption() { return caption; }
    public void setCaption(String value) { this.caption = value; }

    /**
     * The URI or Data URI of the image.
     */
    public String getID() { return id; }
    public void setID(String value) { this.id = value; }

    /**
     * MUST be the IRI 'Image'.
     */
    public ImageType getType() { return type; }
    public void setType(ImageType value) { this.type = value; }
}

