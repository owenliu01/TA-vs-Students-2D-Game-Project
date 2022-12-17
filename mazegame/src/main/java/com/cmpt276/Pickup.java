package com.cmpt276;

import java.awt.*;

/**
 * Class to represent the items that need to be picked up to win 
 */
public class Pickup extends Entity {
    private String coffee_smaller_path = "/images/coffee_smaller.png";

    /**
     * Default constructor.
     */
    public Pickup() {
        super();
        this.sprite = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource(coffee_smaller_path));
        goType = GameObjectType.Pickup;
    }

    /**
     * Creates a Pickup with the specified sanityImpact.
     *
     * @param _sanityImpact how much interaction with this object affects the Player's sanity
     */
    public Pickup(int _sanityImpact) {
        super(_sanityImpact); // _sanityImpact should be greater than 0 bc we want to increase sanity
        this.sprite = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource(coffee_smaller_path));
        goType = GameObjectType.Pickup;
    }

    /**
     * Creates a Pickup at the specified location with the specified sanityImpact.
     *
     * @param xPos the x coordinate for this object
     * @param yPos the y coordinate for this object
     * @param _sanityImpact how much interaction with this object affects the Player's sanity
     */
    public Pickup(int xPos, int yPos, int _sanityImpact) {
        super(xPos, yPos, _sanityImpact);
        this.sprite = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource(coffee_smaller_path));
        goType = GameObjectType.Pickup;
    }

}
