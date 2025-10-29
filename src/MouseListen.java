import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseListen implements MouseListener, MouseMotionListener {
    public void mouseClicked(MouseEvent e) {
        Point pos = new Point(e.getX(), e.getY());
        if (Application.drawing) {
            pos = pos.toField();
            pos.truncate();
            pos.label = "Point " + Application.numPoints;
            Application.points.add(pos);

            Application.numPoints++;

            Application.drawPath();
        }
        else if (Application.edit) {
            for (int i = Application.points.size()-1; i >= 0; i--) {
                int x = Point.toPixels(Application.points.get(i).x);
                int y = Point.toPixels(Application.points.get(i).y);
                if (e.getX() > x - 6 && e.getX() < x + 6 && e.getY() > y - 6 && e.getY() < y + 6) {
                    Application.points.get(i).truncate();
                    Application.selectedPoint = i;

                    System.out.println("Selected Point: " + Application.points.get(Application.selectedPoint).toString());

                    Application.drawRobot(Application.points.get(Application.selectedPoint));
                    Application.displayPointInfo();

                    return;
                }
            }
            Application.selectedPoint = -1;
            Application.drawPath();
            Application.displayPointInfo();
        }
        else if (Application.remove) {
            for (int i = 0; i < Application.points.size(); i++) {
                Point p = Application.points.get(i).toPixels();
                if (e.getX() > p.x - 6 && e.getX() < p.x + 6 && e.getY() > p.y - 6 && e.getY() < p.y + 6) {
                    Application.points.remove(i);
                    Application.drawPath();

                    return;
                }
            }
        }
    }

    public void mouseExited(MouseEvent e) {}
    public void mouseEntered(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}
    public void mousePressed(MouseEvent e){}
    public void mouseMoved(MouseEvent e){}

    public void mouseDragged(MouseEvent e) {
        Point pos = new Point(e.getX(), e.getY()).toField();
        pos.truncate();
        if (Application.selectedPoint != -1) {
            Application.points.get(Application.selectedPoint).x = pos.x;
            Application.points.get(Application.selectedPoint).y = pos.y;

            Application.displayPointInfo();
        }
    }
}
