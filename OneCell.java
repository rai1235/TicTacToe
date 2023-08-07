public class OneCell {
    String character;
    boolean occupied;

    public OneCell() {
        this.character = "";
        this.occupied = false;
    }
    public OneCell(String character, boolean occupied) {
        this.character = character;
        this.occupied = occupied;
    }

    public void setCharacter(String newChar) {
        this.character = newChar;
    }
    public void setCharacter(char newChar) {
        String str = "" + newChar;
        setCharacter(str);
    }
    public void setOccupancy(boolean occupied) {
        this.occupied = occupied;
    }

    // Method to check if the cell is occupied
    public boolean isOccupied() {
        return occupied;
    }
}
