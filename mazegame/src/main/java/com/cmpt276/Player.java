package com.cmpt276;

import java.awt.*;

/**
 * Class to represent the character the user controls
 */
public class Player extends GameObject {
    private int sanity = 50;
    private int pickupsCollected = 0;
    private int specialPickupsCollected = 0;

    /**
     * Default constructor. Player spawns in bottom left corner.
     */
    public Player() {
        // Player spawns in bottom left
        this.x = 10;
        this.y = 610;
        this.sprite = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/TA.png"));
        goType = GameObjectType.Player;
    }

    /**
     * Returns an int equal to the Player's current sanity.
     * 
     * @return an int equal to the Player's current sanity
     */
    public int getSanity() {
        return sanity;
    }

    /**
     * Returns an int equal to the number of normal Pickups the player has
     * collected.
     * 
     * @return an int equal to the number of normal Pickups the player has collected
     */
    public int getPickupsCollected() {
        return pickupsCollected;
    }

    /**
     * Returns an int equal to the number of Pickup objects the Player has collected.
     *
     * @return an int equal to the number of Pickup objects the Player has collected
     */
    public int getSpecialPickupsCollected() {
        return specialPickupsCollected;
    }

    /**
     * Adjusts the Player's sanity by the parameter passed in.
     * 
     * @param increment the amount to adjust the Player's sanity by
     */
    public void updateSanity(int increment) {
        sanity += increment;
    }

    /**
     * Increases the Player's Pickup count by 1.
     */
    public void incrementPickup() {
        pickupsCollected++;
    }

    /**
     * Increases the Player's SpecialPickup count by 1.
     */
    public void incrementSpecialPickup() {
        specialPickupsCollected++;
    }
}
