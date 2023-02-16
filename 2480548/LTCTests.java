public class LTCTests {

    public static LTC t1 () {
        InterchangeStation s1 = new InterchangeStation(55, "Fanshawe", 50, 220);
        InterchangeStation s2 = new InterchangeStation(72, "Richmond", 215, 220);
        InterchangeStation s3 = new InterchangeStation(19, "Hyde Park", 343, 220);
        Station s4 = new Station(33, "Oxford", 220, 154);
        InterchangeStation s5 = new InterchangeStation(91, "Southdale", 203, 300);

        TransitLine lineA = new TransitLine('A', s1);
        lineA.addStation(s2);
        lineA.addStation(s3);

        TransitLine lineB = new TransitLine('B', s4);
        lineB.addStation(s2);
        lineB.addStation(s5);

        Station s6 = new Station(27, "Kipps Lane", 343, 100);
        InterchangeStation s7 = new InterchangeStation(48, "Highbury", 350, 400);
        Station s8 = new Station(32, "White Oaks", 350, 450);
        Station s9 = new Station(81, "Exeter", 350, 515);
        TransitLine lineC = new TransitLine('C', s6);
        lineC.addStation(s3);
        lineC.addStation(s7);
        lineC.addStation(s8);
        lineC.addStation(s9);


        TransitLine lineD = new TransitLine('D', s1);
        lineD.addStation(s5);
        lineD.addStation(s7);

        Station[] sArr = new Station[] {s1, s2, s3, s4, s5, s6, s7, s8, s9};
        TransitLine[] tArr = new TransitLine[] {lineA, lineB, lineC, lineD};
        LTC ltc = new LTC(sArr, tArr);

        return ltc;
    }

    public static LTC t2 () {

        Station[] sArr = new Station[30];
        sArr[0] = new Station(92, "Hyde Park", 70, 85);
        sArr[1] = new Station(65, "Wonderland", 70, 172);
        sArr[2] = new Station(17, "Aldersbrook", 114, 240);
        sArr[3] = new Station(88, "Wharncliffe", 208, 261);
        sArr[4] = new Station(21, "Windermere", 299, 261);
        sArr[5] = new Station(36, "Arva", 393, 85);
        sArr[6] = new Station(95, "Masonville", 393, 195);
        sArr[7] = new InterchangeStation(49, "Richmond", 393, 261);
        sArr[8] = new Station(62, "Platt's Lane", 400, 337);
        sArr[9] = new InterchangeStation(37, "Oxford", 417, 400);
        sArr[10] = new Station(11, "Bradley", 417, 480);
        sArr[11] = new Station(20, "Southdale", 417, 535);
        sArr[12] = new Station(57, "Exeter", 417, 604);
        sArr[13] = new Station(63, "University", 150, 400);
        sArr[14] = new Station(81, "Western", 297, 400);
        sArr[15] = new Station(25, "Wellington", 550, 400);
        sArr[16] = new InterchangeStation(44, "Highbury", 630, 400);
        sArr[17] = new Station(29, "North Centre", 460, 261);
        sArr[18] = new Station(71, "Adelaide", 523, 250);
        sArr[19] = new Station(98, "Glenora", 611, 235);
        sArr[20] = new Station(34, "Medway", 700, 85);
        sArr[21] = new Station(70, "Sunningdale", 700, 170);
        sArr[22] = new InterchangeStation(58, "Fanshawe", 700, 235);
        sArr[23] = new Station(42, "Clarke", 739, 235);
        sArr[24] = new Station(64, "College", 690, 315);
        sArr[25] = new Station(87, "Crumlin", 708, 400);
        sArr[26] = new Station(10, "Airport", 750, 400);
        sArr[27] = new Station(52, "Base Line", 630, 444);
        sArr[28] = new Station(23, "Commissioners", 630, 530);
        sArr[29] = new Station(78, "White Oaks", 630, 594);

        TransitLine lineA = new TransitLine('A', sArr[0]);
        lineA.addStation(sArr[1]);
        lineA.addStation(sArr[2]);
        lineA.addStation(sArr[3]);
        lineA.addStation(sArr[4]);
        lineA.addStation(sArr[7]);
        lineA.addStation(sArr[17]);
        lineA.addStation(sArr[18]);
        lineA.addStation(sArr[19]);
        lineA.addStation(sArr[22]);
        lineA.addStation(sArr[23]);

        TransitLine lineB = new TransitLine('B', sArr[5]);
        lineB.addStation(sArr[6]);
        lineB.addStation(sArr[7]);
        lineB.addStation(sArr[8]);
        lineB.addStation(sArr[9]);
        lineB.addStation(sArr[10]);
        lineB.addStation(sArr[11]);
        lineB.addStation(sArr[12]);

        TransitLine lineC = new TransitLine('C', sArr[13]);
        lineC.addStation(sArr[14]);
        lineC.addStation(sArr[9]);
        lineC.addStation(sArr[15]);
        lineC.addStation(sArr[16]);
        lineC.addStation(sArr[25]);
        lineC.addStation(sArr[26]);

        TransitLine lineD = new TransitLine('D', sArr[20]);
        lineD.addStation(sArr[21]);
        lineD.addStation(sArr[22]);
        lineD.addStation(sArr[24]);
        lineD.addStation(sArr[16]);
        lineD.addStation(sArr[27]);
        lineD.addStation(sArr[28]);
        lineD.addStation(sArr[29]);

        TransitLine[] tArr = new TransitLine[] {lineA, lineB, lineC, lineD};
        LTC ltc = new LTC(sArr, tArr);

        return ltc;
    }

    public static void main(String[] args) {

        Station s1, s2;
        InterchangeStation i1, i2;
        TransitLine t1, t2;

        LTC ltc1 = t1();

        s1 = ltc1.getStationByID(32);
        s2 = ltc1.getStationByIndex(1);
        i1 = (InterchangeStation)s2;
        i2 = (InterchangeStation)ltc1.getStationByIndex(2);
        t1 = ltc1.getTransitLineByIndex(0);
        t2 = ltc1.getTransitLineByIndex(2);



        // 1 ---------- Station toString() ----------

        if (s1.toString().equals("Stn: 32 (White Oaks)")) {
            System.out.println("Test 1 Passed");
        } else {
            System.out.println("Test 1 Failed");
        }



        // 2 ---------- InterchangeStation toString() ----------

        if (s2.toString().equals("Stn: 72 (Richmond) on Lines: A B ")) {
            System.out.println("Test 2 Passed");
        } else {
            System.out.println("Test 2 Failed");
        }



        // 3 ---------- TransitLine toString() ----------

        if (t1.toString().equals("Line A:  55  72  19  ") && t2.toString().equals("Line C:  27  19  48  32  81  ")) {
            System.out.println("Test 3 Passed");
        } else {
            System.out.println("Test 3 Failed");
        }



        // 4 ---------- InterchangeStation getPrevString() ----------

        if (i1.getPrevString().equals("55  33  ") && i2.getPrevString().equals("72  __  27  ")) {
            System.out.println("Test 4 Passed");
        } else {
            System.out.println("Test 4 Failed");
        }



        // 5 ---------- InterchangeStation getNextString() ----------
        if (i1.getNextString().equals("19  91  ") && i2.getNextString().equals("__  __  48  ")) {
            System.out.println("Test 5 Passed");
        } else {
            System.out.println("Test 5 Failed");
        }

        // --------------------------------------------
        // Reset variables for next set of tests.
        // --------------------------------------------
        s1 = s2 = i1 = i2 = null;
        t1 = t2 = null;


        LTC ltc2 = t2();
        s1 = ltc2.getStationByIndex(17);
        i1 = (InterchangeStation)ltc2.getStationByIndex(9);
        i2 = (InterchangeStation)ltc2.getStationByIndex(16);
        t1 = ltc2.getTransitLineByIndex(0);



        // 6 ---------- Station getPrev() and getNext() ----------

        if (s1.getPrev().getStnNo() == 49 && s1.getNext().getStnNo() == 71) {
            System.out.println("Test 6 Passed");
        } else {
            System.out.println("Test 6 Failed");
        }



        // 7 ---------- InterchangeStation getNext() ----------
        try {
            int x = i1.getNext().getStnNo();
            System.out.println("Test 7 Failed");
        } catch (StationException e) {
            if (e.getMessage().equals("Station Exception: InterchangeStation cannot use getNext()")) {
                System.out.println("Test 7 Passed");
            } else {
                System.out.println("Test 7 Failed");
            }
        } catch (Exception e) {
            System.out.println("Test 7 Failed");
        }



        // 8 ---------- InterchangeStation getPrev(char) and getNext(char) ----------

        if (i1.getPrev('B').getStnNo() == 62 && i1.getNext('B').getStnNo() == 11 &&
                i1.getPrev('C').getStnNo() == 81 && i1.getNext('C').getStnNo() == 25 &&
                i1.getPrev('D') == null && i1.getNext('D') == null) {
            System.out.println("Test 8 Passed");
        } else {
            System.out.println("Test 8 Failed");
        }



        // 9 ---------- InterchangeStation toString() ----------

        if (i2.toString().equals("Stn: 44 (Highbury) on Lines: C D ")) {
            System.out.println("Test 9 Passed");
        } else {
            System.out.println("Test 9 Failed");
        }



        // 10 ---------- TransitLine toString() ----------

        if (t1.toString().equals("Line A:  92  65  17  88  21  49  29  71  98  58  42  ")) {
            System.out.println("Test 10 Passed");
        } else {
            System.out.println("Test 10 Failed");
        }



    }

}

