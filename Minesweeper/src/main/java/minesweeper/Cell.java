package minesweeper;

public class Cell {
    private boolean isOpen;
    private boolean isMine;
    private int minesNear;

    public boolean getIsOpen() {
        return this.isOpen;
    }

    public boolean getIsMine() {
        return this.isMine;
    }

    public void setIsOpen() {
        this.isOpen = true;
    }

    public void setIsMine() {
        this.isMine = true;
    }

    public int getMinesNear() {
        return this.minesNear;
    }

    @Override
    public String toString() { 
        if (isOpen) return String.valueOf(minesNear);
        if (isMine) return "*";
        return "#";
    } 

}
