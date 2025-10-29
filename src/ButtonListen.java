import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ButtonListen implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == window.editButton) {
            Application.drawing = false;
            Application.edit = true;
            Application.remove = false;
            window.status.setText("Current Action: Editing");
        }
        else if (e.getSource() == window.drawButton) {
            Application.drawing = true;
            Application.edit = false;
            Application.remove = false;
            window.status.setText("Current Action: Drawing");

            Application.selectedPoint = -1;
            Application.drawPath();
            Application.displayPointInfo();
        }
        else if (e.getSource() == window.removeButton) {
            Application.drawing = false;
            Application.edit = false;
            Application.remove = true;
            window.status.setText("Current Action: Remove");

            Application.selectedPoint = -1;
            Application.drawPath();
            Application.displayPointInfo();
        }
        else if (e.getSource() == window.exportButton) {
            Application.export();
        }
        else if (e.getSource() == window.saveButton) {
            Application.save();
        }
        else if (e.getSource() == window.loadButton) {
            Application.load();
        }
        else if (e.getSource() == window.playButton) {
            if (Application.points.size() > 1) {
                Application.selectedPoint = -1;
                Application.animationIndex = 0;
                Application.animationLerp = 0;
                window.timer.start();
            }
        }
        else if (e.getSource() == window.stopButton) {
            window.timer.stop();
        }
    }
}
