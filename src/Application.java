import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.awt.geom.AffineTransform;
import java.util.Scanner;
import javax.swing.JFileChooser;

public class Application {
    public static ArrayList<Point> points;

    public static int selectedPoint = -1;
    static int numPoints = 0;

    public static boolean drawing;
    public static boolean edit;
    public static boolean remove;

    public static BufferedImage robot, field;

    public static int animationIndex = 0;
    public static double animationLerp = 0.0;

    public static void main(String[] args) {

        drawing = false;
        edit = true;
        remove = false;

        points = new ArrayList<>();

        try {
            robot = ImageIO.read(new File("C:/Users/rperg/IdeaProjects/FtcGui/src/robot.png"));
            field = ImageIO.read(new File("C:/Users/rperg/IdeaProjects/FtcGui/src/field.png"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        window.start();
    }

    public static void displayPointInfo() {
        if (selectedPoint != -1) {
            window.selectedTitle.setText("Selected: ");
            window.editTitle.setText(points.get(selectedPoint).label);
            window.editTitle.setVisible(true);
            window.pointX.setVisible(true);
            window.editPointX.setVisible(true);
            window.pointY.setVisible(true);
            window.editPointY.setVisible(true);
            window.pointH.setVisible(true);
            window.editPointH.setVisible(true);

            window.moveSpeed.setVisible(true);
            window.editMoveSpeed.setVisible(true);
            window.turnSpeed.setVisible(true);
            window.editTurnSpeed.setVisible(true);

            window.editPointX.setText(points.get(selectedPoint).x + "");
            window.editPointY.setText(points.get(selectedPoint).y + "");
            window.editPointH.setText(points.get(selectedPoint).heading + "");
            window.editMoveSpeed.setText(points.get(selectedPoint).moveSpeed + "");
            window.editTurnSpeed.setText(points.get(selectedPoint).turnSpeed + "");

            Application.drawPath();
        }
        else {
            window.selectedTitle.setText(" ");
            window.editTitle.setVisible(false);
            window.pointX.setVisible(false);
            window.editPointX.setVisible(false);
            window.pointY.setVisible(false);
            window.editPointY.setVisible(false);
            window.pointH.setVisible(false);
            window.editPointH.setVisible(false);

            window.moveSpeed.setVisible(false);
            window.editMoveSpeed.setVisible(false);
            window.turnSpeed.setVisible(false);
            window.editTurnSpeed.setVisible(false);
        }
    }

    public static void save() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int returnValue = fileChooser.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFolder = fileChooser.getSelectedFile();
            File selectedFile = new File(selectedFolder.getAbsoluteFile() + "/project.txt");
            try {
                if (selectedFile.createNewFile()) {
                    FileWriter newFile = new FileWriter(selectedFile.getAbsolutePath());
                    for (Point p : points) {
                        newFile.write(p.label + "," + p.x + "," + p.y + "," + p.heading + "," + p.moveSpeed + "," + p.turnSpeed + "\n");
                    }
                    newFile.close();
                }
                else {
                    window.status.setText("File already exists!");
                }
            }
            catch(IOException e) {
                window.status.setText("Error!");
            }

            window.status.setText("Saved to project.txt");
        }
        else if (returnValue == JFileChooser.CANCEL_OPTION) {
            window.status.setText("File selection cancelled.");
        }
    }

    public static void load() {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File input = fileChooser.getSelectedFile();
            try (Scanner sc = new Scanner(input)) {
                while (sc.hasNextLine()) {
                    String[] line = sc.nextLine().split(",");

                    for(int i = 0; i < line.length; i++) {
                        System.out.println(line[i]);
                    }

                    String l = line[0];
                    double x = Double.parseDouble(line[1]);
                    double y = Double.parseDouble(line[2]);
                    double h = Double.parseDouble(line[3]);
                    double m = Double.parseDouble(line[4]);
                    double t = Double.parseDouble(line[5]);

                    points.add(new Point(x, y, h, l, m, t));
                }
                drawPath();
                window.status.setText("Import successful!");
            }
            catch (IOException e) {
                window.status.setText("Could not read/write to file!");
                System.out.print(e.getLocalizedMessage());
            }
        }
        else if (returnValue == JFileChooser.CANCEL_OPTION) {
            window.status.setText("File selection cancelled.");
        }
    }

    public static void export() {
        File shell = new File("C:/Users/rperg/IdeaProjects/FtcGui/src/programShell.txt");

        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            try (Scanner sc = new Scanner(shell)) {
                FileWriter selectedFile = new FileWriter(fileChooser.getSelectedFile());
                System.out.println("Selected file: " + fileChooser.getSelectedFile().getAbsolutePath());

                while (sc.hasNextLine()) {
                    String line = sc.nextLine();

                    if (line.contains("CODE")) {
                        if (points.size() > 0) {
                            selectedFile.write("        Trajectory movement = new Trajectory(new Pose(" + (-points.get(0).y) + ", " + (-points.get(0).x) + ", Math.toRadians(" + points.get(0).heading + "))) \n");

                            for (int i = 1; i < points.size(); i++) {
                                selectedFile.write("        .lineTo(new Pose(" + (-points.get(i).y) + ", " + (-points.get(i).x) + ", Math.toRadians(" + points.get(i).heading + ")))");
                                if (i == points.size()-1)
                                    selectedFile.write(";\n");
                                else
                                    selectedFile.write("\n");
                            }
                        }
                    }
                    else {
                        selectedFile.write(line + "\n");
                    }
                }
                window.status.setText("Export successful!");
                selectedFile.close();
            }
            catch (IOException e) {
                window.status.setText("Could not read/write to file!");
                System.out.print(e.getLocalizedMessage());
            }
        }
        else if (returnValue == JFileChooser.CANCEL_OPTION) {
            window.status.setText("File selection cancelled.");
        }
    }

    public static void updateAnimation() {
        if (animationLerp >= 1.0) {
            animationLerp = 0;
            animationIndex++;
        }
        if (animationIndex == points.size() - 1) {
            animationIndex = 0;
            animationLerp = 0;
        }

        Point start = points.get(animationIndex);
        Point end = points.get(animationIndex + 1);

        if (end.heading > start.heading) {
            while (Math.abs(end.heading - start.heading) > 180) {
                start.heading += 360;
            }
        }
        else if (end.heading < start.heading) {
            while (Math.abs(end.heading - start.heading) > 180) {
                start.heading -= 360;
            }
        }

        double x = start.x + (end.x - start.x) * animationLerp;
        double y = start.y + (end.y - start.y) * animationLerp;
        double h = start.heading + (end.heading - start.heading) * animationLerp;

        drawPath();
        drawRobot(new Point(x, y, h));

        animationLerp += 3 / (start.distance(end));
    }

    public static void drawPath() {
        Graphics2D g = (Graphics2D) window.c.getGraphics();
        g.setColor(Color.YELLOW);

        BasicStroke stroke = new BasicStroke(3.0f);

        window.c.paint(g);

        g.setStroke(stroke);

        if (selectedPoint != -1)
            drawRobot(points.get(selectedPoint));

        for (int i = 0; i < points.size(); i++) {
            Point p = points.get(i).toPixels();
            if (i < points.size()-1) {
                g.setColor(Color.YELLOW);
                g.drawLine((int) p.x, (int) p.y, Point.toPixels(points.get(i + 1).x), Point.toPixels(points.get(i + 1).y));
            }

            p = p.toField();

            if (selectedPoint == i) {
                g.setColor(Color.RED);
                g.fillOval(Point.toPixels(points.get(selectedPoint).x) - 5, Point.toPixels(points.get(selectedPoint).y) - 5, 10, 10);
            }
            else {
                g.setColor(Color.YELLOW);
                g.fillOval(Point.toPixels(p.x) - 5, Point.toPixels(p.y) - 5, 10, 10);
            }
        }
    }

    public static void drawRobot(Point robotPose) {
        Graphics2D g = (Graphics2D) window.c.getGraphics();
        g.setColor(Color.YELLOW);

        AlphaComposite alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.75f);
        g.setComposite(alphaComposite);

        BasicStroke stroke = new BasicStroke(3.0f);

        g.setStroke(stroke);

        int centerX = Point.toPixels(robotPose.x);
        int centerY = Point.toPixels(robotPose.y);

        // Calculate the top-left corner of the square relative to its center
        int squareX = centerX - 76 / 2;
        int squareY = centerY - 76 / 2;

        AffineTransform old = g.getTransform();

        g.translate(centerX, centerY);
        g.rotate(Math.toRadians(robotPose.heading));
        g.translate(-centerX, -centerY);

        g.drawImage(robot, centerX-38, centerY-38, null);

        g.setTransform(old);
    }
}
