package com.cmpt276;

import java.awt.*;
import java.awt.image.ImageObserver;

/**
 * Class to represent all visible elements within the game scene
 */
public class GameObject {
    Image sprite;
    /**
     * The object's x coordinate
     */
    protected int x = 0;
    /**
     * The object's y coordinate
     */
    protected int y = 0;
    /**
     * The type of this GameObject
     */
    protected GameObjectType goType;

    /**
     * Default constructor
     */
    GameObject() {

    }

    /**
     * Creates a GameObject at the specified location.
     *
     * @param x the x coordinate for this object
     * @param y the y coordinate for this object
     */
    public GameObject(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Scales this object's sprite to meet the specified dimensions.
     * 
     * @param width  target width for this object
     * @param height target height for this object
     */
    public void setDimensions(int width, int height) {
        if (sprite != null) {
            sprite = sprite.getScaledInstance(width, height, Image.SCALE_DEFAULT);
        }
    }

    /**
     * Returns the dimensions of this object's sprite
     * 
     * @return integer array equal to this object's dimensions; element 0 is the
     *         width and element 1 is the height
     */
    public int[] getDimensions() {
        return new int[] { sprite.getWidth(null), sprite.getHeight(null) };
    }

    /**
     * Returns the dimensions of this object's sprite. Takes in an ImageObserver to be notified later
     * if the image is not yet ready to view.
     *
     * @param observer the ImageObserver to be notified, should the image not be ready to view at call time
     * @return integer array equal to this object's dimensions; element 0 is the
     *         width and element 1 is the height
     */
    public int[] getDimensions(ImageObserver observer) {
        return new int[] { sprite.getWidth(observer), sprite.getHeight(observer) };
    }

    /**
     * Get function for this object's location in the scene.
     * 
     * @return 2-element integer array; element 0 is the object's x coordinate;
     *         element 1 is the object's y coordinate
     */
    public int[] getLocation() {
        return new int[] { x, y };
    }

    /**
     * Sets the x coordinate of this object in the scene.
     * 
     * @param x the target x coordinate of this object.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Get function for this object's x coordinate in the scene.
     * 
     * @return int equal to this object's current x coordinate in the scene
     */
    public int getX() {
        return x;
    }

    /**
     * Sets the y coordinate of this object in the scene.
     * 
     * @param y the target y coordinate of this object.
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Get function for this object's y coordinate in the scene.
     * 
     * @return int equal to this object's current y coordinate in the scene
     */
    public int getY() {
        return y;
    }

    /**
     * Returns an element of an enum corresponding to what type of object this is.
     * 
     * @return an element of the GameObjectType enum of the same name as this
     *         object's type
     */
    public GameObjectType getGoType() {
        return goType;
    }

    /**
     * Sets this object's GameObjectType.
     * 
     * @param goType an element of the GameObjectType enum
     */
    public void setGoType(GameObjectType goType) {
        this.goType = goType;
    }

    /**
     * Returns a Rectangle corresponding to this object's current location and
     * bounds.
     * 
     * @return a Rectangle corresponding to this object's current location and
     *         bounds
     */
    public Rectangle getCurrBoundaries() {
        return new Rectangle(x, y, sprite.getWidth(null), sprite.getHeight(null));
    }

    /**
     * Returns a Rectangle corresponding to this object's current location and
     * bounds. Takes in an ImageObserver to be notified later if the image is not yet ready to view.
     *
     * @param observer the ImageObserver to be notified, should the image not be ready to view at call time
     * @return a Rectangle corresponding to this object's current location and
     *         bounds
     */
    public Rectangle getCurrBoundaries(ImageObserver observer) {
        return new Rectangle(x, y, sprite.getWidth(null), sprite.getHeight(observer));
    }

    public Image getSprite() {
        return this.sprite;
    }

}
