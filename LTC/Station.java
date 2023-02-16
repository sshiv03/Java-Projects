public class Station {
    private static int nextID = 0;
    private int id;
    private int stnNo;
    private String name;
    private Station prev;
    private Station next;
    private int x;
    private int y;

    public Station(int stnNo, String stnName, int x, int y) {
        this.stnNo = stnNo;
        this.name = stnName;
        this.x = x;
        this.y = y;

        this.prev = null;
        this.next = null;

        this.id = nextID;
        nextID += 1;
    }

    public int getID() {
        return id;
    }

    public int getStnNo() {
        return stnNo;
    }

    public String getName() {
        return name;
    }

    public Station getPrev() {
        return prev;
    }

    public Station getNext() {
        return next;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setPrev(Station prev) {
        this.prev = prev;
    }

    public void setNext(Station next) {this.next = next;}

    public String toString() {
        return "Stn: " + stnNo + " (" + name + ")";
    }
}

