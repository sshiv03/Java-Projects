public class LTC {

    private Station[] allStations;
    private TransitLine[] allLines;
    private Map gui;

    public LTC (Station[] sArr, TransitLine[] tArr) {
        allStations = sArr;
        allLines = tArr;
        gui = new Map(this, true);
    }

    public Station[] getStations () {
        return allStations;
    }

    public TransitLine[] getLines () {
        return allLines;
    }


    public Station getStationByIndex (int index) {
        return allStations[index];
    }

    public Station getStationByID (int id) {
        for (int i = 0; i < allStations.length; i++) {
            if (allStations[i].getStnNo() == id) {
                return allStations[i];
            }
        }
        return null;
    }

    public TransitLine getTransitLineByIndex (int index) {
        return allLines[index];
    }


}

