package com.cmpt276;

import java.awt.*;

/**
 * Class to represent barriers; blocks movement of players and enemies
 */
public class Barrier extends GameObject {
    /**
     * What type of Barrier this is; affects rendered sprite
     */
    protected BarrierType barrierType;

    /**
     * Creates a Barrier of the specified type at the specified location.
     *
     * @param xPos    the x coordinate for this object
     * @param yPos    the y coordinate for this object
     * @param barrier the type of Barrier for this object
     */
    public Barrier(int xPos, int yPos, BarrierType barrier) {
        super(xPos, yPos);
        goType = GameObjectType.Barrier;
        this.barrierType = barrier;
        setSprite();
    }

    /**
     * Assigns the correct sprite to this barrier object.
     */
    private void setSprite() {
        if (barrierType == BarrierType.Wall) {
            this.sprite = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/block.png"));
        } else if (barrierType == BarrierType.Bookshelf) {
            this.sprite = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/Bookshelf.png"));
        } else if (barrierType == BarrierType.FloorSign) {
            this.sprite = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/FloorSign.png"));
        }
    }

    public BarrierType getBarrierType() {
        return this.barrierType;
    }
}
