import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardListen implements KeyListener {
    public void keyPressed(KeyEvent e) {
        System.out.println(KeyEvent.getKeyText(e.getKeyCode()));
        if (KeyEvent.getKeyText(e.getKeyCode()).equals("Enter")) {
            Application.drawPath();
        }
        else if (KeyEvent.getKeyText(e.getKeyCode()).equals("D")) {
            Application.drawing = true;
            Application.edit = false;
            Application.remove = false;
            window.status.setText("Current Action: Drawing");

            Application.selectedPoint = -1;
            Application.drawPath();
            Application.displayPointInfo();
        }
        else if (KeyEvent.getKeyText(e.getKeyCode()).equals("E")) {
            Application.drawing = false;
            Application.edit = true;
            Application.remove = false;
            window.status.setText("Current Action: Editing");
        }
        else if (KeyEvent.getKeyText(e.getKeyCode()).equals("R")) {
            Application.drawing = false;
            Application.edit = false;
            Application.remove = true;
            window.status.setText("Current Action: Remove");

            Application.selectedPoint = -1;
            Application.drawPath();
            Application.displayPointInfo();
        }
    }

    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}
}
