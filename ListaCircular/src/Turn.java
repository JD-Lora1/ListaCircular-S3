public class Turn {
    //Attributes
    private Integer index;
    private int turnCounter;

    //Links
    private Turn prev;
    private Turn next;

    public Turn(Integer index) {
        this.index = index;
        turnCounter = 0;
    }

    //GET and SET

    public Integer getIndex() {
        return index;
    }
    public int getTurnCounter() {
        return turnCounter;
    }
    public void setIndex(Integer index) {
        this.index = index;
    }
    public void setTurnCounter(int turnCounter) {
        this.turnCounter = turnCounter;
    }

    public Turn getPrev() {
        return prev;
    }

    public void setPrev(Turn prev) {
        this.prev = prev;
    }

    public Turn getNext() {
        return next;
    }

    public void setNext(Turn next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Id: "+ index +" ";
    }
}
