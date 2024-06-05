package io.epos.portal_api.w3ih.models.openbadges;

/**
 * Descriptive metadata about the achievements necessary to be recognized with an assertion
 * of a particular achievement. This data is added to the Achievement class so that it may
 * be rendered when the achievement assertion is displayed, instead of simply a link to
 * human-readable criteria external to the achievement. Embedding criteria allows either
 * enhancement of an external criteria page or increased portability and ease of use by
 * allowing issuers to skip hosting the formerly-required external criteria page altogether.
 * Criteria is used to allow would-be recipients to learn what is required of them to be
 * recognized with an assertion of a particular achievement. It is also used after the
 * assertion is awarded to a recipient to let those inspecting earned achievements know the
 * general requirements that the recipients met in order to earn it.
 */
public class Criteria {
    private String id;
    private String narrative;

    /**
     * The URI of a webpage that describes in a human-readable format the criteria for the
     * achievement.
     */
    public String getID() { return id; }
    public void setID(String value) { this.id = value; }

    /**
     * A narrative of what is needed to earn the achievement. Markdown is allowed.
     */
    public String getNarrative() { return narrative; }
    public void setNarrative(String value) { this.narrative = value; }
}

