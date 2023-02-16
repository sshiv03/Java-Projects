public class TransitLine {
    private char lineLetter;
    private Station firstStn;

    public TransitLine(char letter, Station first) {
        this.lineLetter = letter;
        this.firstStn = first;
    }


    public char getLineLetter() {
        return lineLetter;
    }

    public Station getFirstStn() {
        return firstStn;
    }

    public boolean hasNext(Station stn) {
        if(stn instanceof InterchangeStation) {
            if(((InterchangeStation)stn).getNext(lineLetter) == null)
                return false;
        } else {
            if(stn.getNext() == null)
                return false;
        }

        return true;
    }

    public void addStation(Station stn) {
        Station currentStn = firstStn;

        while(hasNext(currentStn)) {
            if(currentStn instanceof InterchangeStation)
                currentStn = ((InterchangeStation)currentStn).getNext(lineLetter);
            else
                currentStn = currentStn.getNext();
        }

        if(stn instanceof InterchangeStation)
            ((InterchangeStation)stn).setPrev(currentStn, lineLetter);
        else
            stn.setPrev(currentStn);

        if(currentStn instanceof InterchangeStation)
            ((InterchangeStation)currentStn).setNext(stn, lineLetter);
        else
            currentStn.setNext(stn);
    }

    public String toString() {
        Station currentStn = firstStn;
        String output = "Line " + lineLetter + ":  " + firstStn.getStnNo() + "  ";

        while(hasNext(currentStn)) {
            if(currentStn instanceof InterchangeStation)
                currentStn = ((InterchangeStation)currentStn).getNext(lineLetter);
            else
                currentStn = currentStn.getNext();

            output += currentStn.getStnNo() + "  ";
        }

        return output;
    }
}

