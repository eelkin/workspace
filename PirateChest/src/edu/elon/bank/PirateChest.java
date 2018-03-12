/*
 * PirateChest.java 1.0 Feb. 26, 2012
 *
 * Copyright (c) Oregon State University provided source code
 */
package edu.elon.bank;


/**
 * Class example using a simple PirateChest that holds pieces of gold.
 * 
 * @author Oregon State University
 * @version 1.0
 */
public class PirateChest {
    private int numGold;

    /**
     * Constructs a empty <code>PirateChest</code> with no gold.
     */
    public PirateChest() {
        numGold = 0;
    }

    /**
     * Constructs a <code>PirateChest</code> and puts <code>gold</code> pieces
     * into an empty chest.
     * 
     * @param gold
     *        int representing positive number of gold pieces to place into the
     *        chest.
     */
    public PirateChest(int gold) {
        if (gold < 0) {
            gold = 0;
        }
        numGold = gold;
    }

    /**
     * Adds an additional <code>gold</code> pieces to what is already in the
     * chest.
     * 
     * @param gold
     *        int greater than 0 representing number of pieces of gold to add to
     *        the chest.
     */
    public void addGold(int gold) {
        if (gold < 0) {
            // we do not allow negative gold so set to zero
            gold = 0;
        }
        numGold = numGold + gold;
    }

    /**
     * Gets the number of pieces of gold currently in the chest. The number of
     * pieces should be greater than or equal to 0
     * 
     * @return int representing the number of pieces of gold in the chest
     */
    public int checkGold() {
        return numGold;
    }

    /**
     * Tests this against <code>aObj</code> to see if both chests have the same
     * number of gold pieces.
     * 
     * @return <code>true</code> if both chests have same number of pieces.
     *         Otherwise return <code>false</code>
     */
    @Override
    public boolean equals(Object aObj) {
        boolean result = false;
        if (this.getClass() == aObj.getClass()) {
            PirateChest chest = (PirateChest) aObj;
            if (numGold == chest.checkGold()) {
                result = true;
            }
        }
        return result;

    }

    /**
     * Remove a specified number of pieces from the chest. The number to remove
     * must be greater than or equal to 0 and cannot be larger than what is in
     * the chest. If it is larger than the chest is just emptied.
     * 
     * @param gold
     *        positive int representing the number of pieces to remove from the
     *        trunk
     */
    public void removeGold(int gold) {
        if (gold > numGold) {
            // can only remove a max of what is in there
            gold = numGold;
        } else if (gold < 0) {
            gold = 0;
        }
        numGold = numGold - gold;
    }

    /**
     * Returns a descriptive string of how many pieces are in the trunk
     * 
     * @return string representing the number of gold pieces in the trunk.
     */
    @Override
    public String toString() {
        String chest = "Chest has " + numGold + " pieces of gold";
        return chest;
    }

}