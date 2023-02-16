import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Map extends JPanel {

    private Station[] stns;
    private TransitLine[] lines;
    private Color[] lineColours = new Color[] {Color.red, Color.blue, Color.green.darker(), new Color(175, 0, 200), Color.yellow.darker()};

    public Map (LTC ltc, boolean showMap) {
        stns = ltc.getStations();
        lines = ltc.getLines();

        JFrame f = new JFrame("Linked Transit Commission");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane().add(this);
        f.setSize(1000, 700);
        f.setLocation(550, 25);
        if (showMap) {
            f.setVisible(true);
        }

    }

    public void paint(Graphics g) {

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw transit lines
        g2.setStroke(new BasicStroke(4));
        for (int i = 0; i < lines.length; i++) {
            g2.setColor(lineColours[i]);
            Station prev = null;
            Station curr = lines[i].getFirstStn();
            char ln = lines[i].getLineLetter();
            while (lines[i].hasNext(curr)) {
                prev = curr;
                if (curr instanceof InterchangeStation) curr = ((InterchangeStation)curr).getNext(ln);
                else curr = curr.getNext();
                g2.drawLine(prev.getX(), prev.getY(), curr.getX(), curr.getY());
            }

        }

        // Legend
        for (int i = 0; i < lines.length; i++) {
            g2.setColor(lineColours[i]);
            char ln = lines[i].getLineLetter();
            g2.drawLine(700, 500+(i*30), 750, 500+(i*30));
            g2.drawString("Line " + ln, 760, 505+(i*30));
        }

        // Draw stations as circles.
        for (int i = 0; i < stns.length; i++) {
            int x = stns[i].getX();
            int y = stns[i].getY();
            int rad = (stns[i] instanceof InterchangeStation) ? 40 : 30;
            g2.setColor(Color.black);
            g2.fillOval(x-rad/2, y-rad/2, rad, rad);
            g2.setColor(Color.white);
            g2.drawString(String.valueOf(stns[i].getStnNo()), x - 7, y + 5);
        }

    }

}

