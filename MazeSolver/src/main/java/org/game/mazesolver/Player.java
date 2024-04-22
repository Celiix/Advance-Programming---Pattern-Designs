package org.game.mazesolver;

/**
 * The type Player.
 */
public class Player {
    private int posX;
    private int posY;

    /**
     * Instantiates a new Player.
     *
     * @param startX the start x
     * @param startY the start y
     */
    public Player(int startX, int startY) {
        this.posX = startX;
        this.posY = startY;
    }

    /**
     * Move up.
     */
    public void moveUp() {
        posY--;
    }

    /**
     * Move down.
     */
    public void moveDown() {
        posY++;
    }

    /**
     * Move left.
     */
    public void moveLeft() {
        posX--;
    }

    /**
     * Move right.
     */
    public void moveRight() {
        posX++;
    }

    /**
     * Gets pos x.
     *
     * @return the pos x
     */
    public int getPosX() {
        return posX;
    }

    /**
     * Gets pos y.
     *
     * @return the pos y
     */
    public int getPosY() {
        return posY;
    }

    /**
     * Sets pos x.
     *
     * @param posX the pos x
     */
    public void setPosX(int posX) {
        this.posX = posX;
    }

    /**
     * Sets pos y.
     *
     * @param posY the pos y
     */
    public void setPosY(int posY) {
        this.posY = posY;
    }
}
