import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class DocListen implements DocumentListener {
    public DocListen() {}

    public void insertUpdate(DocumentEvent e) { update(e); }
    public void removeUpdate(DocumentEvent e) { update(e); }
    public void changedUpdate(DocumentEvent e) { update(e); }

    public void update(DocumentEvent e) {
        if (e.getDocument() == window.editPointX.getDocument()) {
            try {
                if (!window.editPointX.getText().equals(""))
                    Application.points.get(Application.selectedPoint).x = Double.parseDouble(window.editPointX.getText());
            } catch (Exception exception) {
                System.out.println("ERROR!!");
                Application.points.get(Application.selectedPoint).x = 0;
            }
        }
        else if (e.getDocument() == window.editPointY.getDocument()) {
            try {
                if (!window.editPointY.getText().equals(""))
                    Application.points.get(Application.selectedPoint).y = Double.parseDouble(window.editPointY.getText());
            } catch (Exception exception) {
                System.out.println("ERROR!!");
                Application.points.get(Application.selectedPoint).y = 0;
            }
        }
        else if (e.getDocument() == window.editPointH.getDocument()) {
            try {
                if (!window.editPointH.getText().equals(""))
                    Application.points.get(Application.selectedPoint).heading = Double.parseDouble(window.editPointH.getText());
            } catch (Exception exception) {
                System.out.println("ERROR!!");
                Application.points.get(Application.selectedPoint).heading = 0;
            }
        }
        else if (e.getDocument() == window.editMoveSpeed.getDocument()) {
            try {
                if (!window.editMoveSpeed.getText().equals(""))
                    Application.points.get(Application.selectedPoint).moveSpeed = Double.parseDouble(window.editMoveSpeed.getText());
            } catch (Exception exception) {
                System.out.println("ERROR!!");
                Application.points.get(Application.selectedPoint).moveSpeed = 0.75;
            }
        }
        else if (e.getDocument() == window.editTurnSpeed.getDocument()) {
            try {
                if (!window.editTurnSpeed.getText().equals(""))
                    Application.points.get(Application.selectedPoint).turnSpeed = Double.parseDouble(window.editTurnSpeed.getText());
            } catch (Exception exception) {
                System.out.println("ERROR!!");
                Application.points.get(Application.selectedPoint).turnSpeed = 0.75;
            }
        }
        else if (e.getDocument() == window.editTitle.getDocument()) {
            Application.points.get(Application.selectedPoint).label = window.editTitle.getText();
        }
    }
}
