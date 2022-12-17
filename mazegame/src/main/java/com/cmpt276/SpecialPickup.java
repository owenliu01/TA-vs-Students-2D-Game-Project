package com.cmpt276;

import java.awt.*;

/**
 * Class to represent the timed pickups that the Player may pick up for extra sanity
 */
public class SpecialPickup extends Pickup {
    private String special_pickup_path = "/images/special_pickup.png";

    /**
     * creates Special pickup with certain x and y pos and sanity impact
     * 
     * @param xPos          x positions of special pickup
     * @param yPos          y positions of special pickup
     * @param _sanityImpact amount the object will affect the sanity of the player
     */
    public SpecialPickup(int xPos, int yPos, int _sanityImpact) {
        super(xPos, yPos, _sanityImpact);
        this.sprite = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource(special_pickup_path));
        goType = GameObjectType.SpecialPickup;
    }
}
