package minesweeper.engine;

/**
 * Class controls the state of a single cell on the board.
 * Class has only setters and getters.
 */

public class Cell {
    private boolean isOpen;
    private boolean isMine;
    private boolean isFlagged;
    private int minesNear;

    public boolean getIsOpen() {
        return this.isOpen;
    }

    public boolean getIsMine() {
        return this.isMine;
    }

    public boolean getIsFlagged() {
        return this.isFlagged;
    }

    /**
     * Sets cell open.
     */

    public void setIsOpen() {
        this.isOpen = true;
    }

    /**
     * Sets cell as mine.
     */

    public void setIsMine() {
        this.isMine = true;
    }

    public int getMinesNear() {
        return this.minesNear;
    }

    /**
     * Adds one to minesNear value.
     */

    public void addMinesNear() {
        this.minesNear++;
    }

    /**
     * Sets cell flagged.
     */

    public void setIsFlagged() {
        this.isFlagged = true;
    }

    /**
     * Removes flag from cell.
     */

    public void setIsNotFlagged() {
        this.isFlagged = false;
    }

    /*@Override
    public String toString() {
        if (!isOpen) {
            return " ";
        }
        if (isMine) {
            return "*";
        }
        return String.valueOf(minesNear);
    }*/

    /*@Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Cell)) {
            return false;
        }
        Cell cell = (Cell) o;
        return isOpen == cell.isOpen && isMine == cell.isMine && isFlagged == cell.isFlagged && minesNear == cell.minesNear;
    }*/

}
