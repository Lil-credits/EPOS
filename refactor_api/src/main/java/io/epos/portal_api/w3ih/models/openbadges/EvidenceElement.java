package io.epos.portal_api.w3ih.models.openbadges;

/**
 * Descriptive metadata about evidence related to the achievement assertion. Each instance
 * of the evidence class present in an assertion corresponds to one entity, though a single
 * entry can describe a set of items collectively. There may be multiple evidence entries
 * referenced from an assertion. The narrative property is also in scope of the assertion
 * class to provide an overall description of the achievement related to the assertion in
 * rich text. It is used here to provide a narrative of achievement of the specific entity
 * described. If both the description and narrative properties are present, displayers can
 * assume the narrative value goes into more detail and is not simply a recapitulation of
 * description.
 */
public class EvidenceElement {
    private String audience;
    private String description;
    private String genre;
    private String id;
    private String name;
    private String narrative;
    private String type;

    /**
     * A description of the intended audience for a piece of evidence.
     */
    public String getAudience() { return audience; }
    public void setAudience(String value) { this.audience = value; }

    /**
     * A longer description of the evidence.
     */
    public String getDescription() { return description; }
    public void setDescription(String value) { this.description = value; }

    /**
     * A string that describes the type of evidence. For example, Poetry, Prose, Film.
     */
    public String getGenre() { return genre; }
    public void setGenre(String value) { this.genre = value; }

    /**
     * The URL of a webpage presenting evidence of achievement or the evidence encoded as a Data
     * URI. The schema of the webpage is undefined.
     */
    public String getID() { return id; }
    public void setID(String value) { this.id = value; }

    /**
     * A descriptive title of the evidence.
     */
    public String getName() { return name; }
    public void setName(String value) { this.name = value; }

    /**
     * A narrative that describes the evidence and process of achievement that led to an
     * assertion.
     */
    public String getNarrative() { return narrative; }
    public void setNarrative(String value) { this.narrative = value; }

    public String getType() { return type; }
    public void setType(String value) { this.type = value; }
}
