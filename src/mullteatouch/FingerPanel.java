package mullteatouch;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * A panel that visualizes a list of Fingers.
 */
public class FingerPanel extends JPanel {

    private boolean showHelp = true;
    Finger[] fingers;

    public void setFingers(Finger[] fingers) {
        this.fingers = fingers;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        Rectangle clip = g.getClipBounds();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.WHITE);
        g2.fillRect(clip.x, clip.y, clip.width, clip.height);
        g2.setColor(Color.BLACK);
        int w = getWidth();
        int h = getHeight();
        if (fingers == null) {
            if (showHelp) {
            g2.setColor(new Color(0, 0, 0, 160));
            g2.fillRoundRect(20, h-80, w-40, 60, 10, 10);
            g2.setFont(new Font(Font.DIALOG, Font.PLAIN, 30));
            g2.setColor(Color.WHITE);
            g2.drawString("Touch the trackpad with multiple fingers.", 40, h-40);
                showHelp = false;
            }
            return;
        };
        Polygon poly = new Polygon();
        for (Finger f : fingers) {
            float x = f.normalized.position.x * w;
            // y coordinate is reversed.
            float y = (1f - f.normalized.position.y) * h;
            poly.addPoint((int) x, (int) y);
            g2.fill(new Ellipse2D.Float(x - 4, y - 4, 8, 8));
        }
        g2.drawPolygon(poly);
    }
}
