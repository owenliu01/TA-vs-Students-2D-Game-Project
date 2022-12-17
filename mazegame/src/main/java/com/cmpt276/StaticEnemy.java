package com.cmpt276;

import java.awt.*;

/**
 * Class to represent the immobile enemies that reduce the player's sanity
 */
public class StaticEnemy extends Entity {

    /**
     * Creates a StaticEnemy at the specified location with the specified sanityImpact.
     *
     * @param x the x coordinate for this object
     * @param y the y coordinate for this object
     * @param _sanityImpact how much interaction with this object affects the Player's sanity
     */
    public StaticEnemy(int x, int y, int _sanityImpact) {
        super(x, y, _sanityImpact); // sanityImpact should be < 0 bc we want to decrease sanity
        this.sprite = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/Paper.png"));
        goType = GameObjectType.StaticEnemy;
    }
}
