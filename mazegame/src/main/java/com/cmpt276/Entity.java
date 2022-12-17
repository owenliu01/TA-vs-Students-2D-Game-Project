package com.cmpt276;

/**
 * Class to represent GameObjects that can affect the Player's sanity
 */
public class Entity extends GameObject {
    /**
     * How much this object affects the Player's sanity upon interaction
     */
    protected int sanityImpact;

    /**
     * Default constructor; sets sanity impact to 0.
     */
    public Entity() {
        super();
        sanityImpact = 0;
    }

    /**
     * Creates an Entity with the sanityImpact specified.
     *
     * @param _sanityImpact how much interaction with this object affects the Player's sanity
     */
    public Entity(int _sanityImpact) {
        super();
        sanityImpact = _sanityImpact;
    }

    /**
     * Creates an Entity at the specified location. Sanity impact is set to 0.
     *
     * @param xPos the x coordinate for this object
     * @param yPos the y coordinate for this object
     */
    public Entity(int xPos, int yPos) {
        super(xPos, yPos);
        sanityImpact = 0;
    }

    /**
     * Creates an Entity at the specified location with specified sanityImpact.
     *
     * @param xPos the x coordinate for this object
     * @param yPos the y coordinate for this object
     * @param _sanityImpact how much interaction with this object affects the Player's sanity
     */
    public Entity(int xPos, int yPos, int _sanityImpact) {
        super(xPos, yPos);
        sanityImpact = _sanityImpact;
    }

    /**
     * Get function for sanityImpact.
     * 
     * @return int equal to current own sanityImpact
     */
    public int getSanityImpact() {
        return sanityImpact;
    }

    /**
     * Set function for sanityImpact.
     * 
     * @param _sanityImpact int to set own sanityImpact to
     */
    public void setSanityImpact(int _sanityImpact) {
        sanityImpact = _sanityImpact;
    }
}
