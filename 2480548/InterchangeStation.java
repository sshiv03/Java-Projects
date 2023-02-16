public class InterchangeStation extends Station{
    private Station[] prevs;
    private Station[] nexts;

    public InterchangeStation(int stnNo, String stnName, int x, int y) {
        super(stnNo, stnName, x, y);

        this.prevs = new Station[2];
        this.nexts = new Station[2];
    }

    public Station getPrev() {
        throw new StationException("InterchangeStation cannot use getPrev()");
    }

    public Station getNext() {
        throw new StationException("InterchangeStation cannot use getNext()");
    }

    public void setPrev(Station stn) {
        throw new StationException("InterchangeStation cannot use setPrev()");
    }

    public void setNext(Station stn) {
        throw new StationException("InterchangeStation cannot use setNext()");
    }

    public Station getPrev(char lineLetter) {
        int lineNumber = lineLetter - 65;

        if(prevs.length > lineNumber)	//Checks if the station is in the array
            return prevs[lineNumber];

        return null;
    }

    public Station getNext(char lineLetter) {
        int lineNumber = lineLetter - 65;

        if(nexts.length > lineNumber)	//Checks if the station is in the array
            return nexts[lineNumber];

        return null;
    }

    public void setPrev(Station stn, char lineLetter) {
        int lineNumber = lineLetter - 65;

        //Expands the prevs and nexts array if needed
        if (prevs.length <= lineNumber || nexts.length <= lineNumber) {	//Check if the array can't hold the index we want to store
            Station[] tempPrevs = new Station[lineNumber + 1];	//Expand the array till it can contain the letter index
            System.arraycopy(prevs, 0, tempPrevs, 0, prevs.length);	//Copies the old data to the temp array
            prevs = tempPrevs;	//Replaces the array with the larger capacity

            Station[] tempNexts = new Station[lineNumber + 1];
            System.arraycopy(nexts, 0, tempNexts, 0, nexts.length);
            nexts = tempNexts;
        }

        this.prevs[lineNumber] = stn;
    }

    public void setNext(Station stn, char lineLetter) {
        int lineNumber = lineLetter - 65;

        //Expands the prevs and nexts array if needed
        if (prevs.length <= lineNumber || nexts.length <= lineNumber) {	//Check if the array can't hold the index we want to store
            Station[] tempPrevs = new Station[lineNumber + 1];	//Expand the array till it can contain the letter index
            System.arraycopy(prevs, 0, tempPrevs, 0, prevs.length);	//Copies the old data to the temp array
            prevs = tempPrevs;	//Replaces the array with the larger capacity

            Station[] tempNexts = new Station[lineNumber + 1];
            System.arraycopy(nexts, 0, tempNexts, 0, nexts.length);
            nexts = tempNexts;
        }

        this.nexts[lineNumber] = stn;
    }

    public Station[] getPrevArray(char lineLetter) {
        return prevs;
    }

    public Station[] getNextArray(char lineLetter) {
        return nexts;
    }

    public String getPrevString() {
        String output = "";

        for(int i = 0; i < prevs.length; i++) {
            if(prevs[i] != null)
                output += prevs[i].getStnNo();
            else
                output += "__";

            output += "  ";
        }

        return output;
    }

    public String getNextString() {
        String output = "";

        for(int i = 0; i < nexts.length; i++) {
            if(nexts[i] != null)
                output += nexts[i].getStnNo();
            else
                output += "__";
            output += "  ";
        }

        return output;
    }

    public String toString() {
        String output = super.toString();
        output += " on Lines: ";

        if(nexts.length != prevs.length)
            System.out.println("Nexts and prevs don't have the same length");

        for(int i = 0; i < nexts.length; i++) {
            if(prevs[i] != null || nexts[i] != null)
                output += ((char)(i + 65)) + " ";
        }

        return output;
    }
}


