import javax.swing.*;
import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class window {
    public static JFrame f;
    public static Canvas c;
    public static JLabel status;
    public static JLabel selectedTitle, pointX, pointY, pointH, moveSpeed, turnSpeed;

    public static JTextField editPointX, editPointY, editPointH, editMoveSpeed, editTurnSpeed, editTitle;

    public static JButton editButton, drawButton, removeButton;
    public static JButton exportButton, saveButton, loadButton;
    public static JButton playButton, stopButton;

    public static Timer timer;

    static KeyboardListen kl;
    static MouseListen ml;
    static DocListen dl;
    static ButtonListen bl;

    public static void start()
    {
        f = new JFrame();

        kl = new KeyboardListen();
        ml = new MouseListen();
        dl = new DocListen();
        bl = new ButtonListen();

        status = new JLabel("Current Action: Editing");

        selectedTitle = new JLabel("");
        editTitle = new JTextField(100);

        pointX = new JLabel("X: ");
        pointY = new JLabel("Y: ");
        pointH = new JLabel("H: ");
        moveSpeed = new JLabel("Move Speed: ");
        turnSpeed = new JLabel("Turn Speed: ");

        editPointX = new JTextField(20);
        editPointY = new JTextField(20);
        editPointH = new JTextField(20);
        editMoveSpeed = new JTextField(20);
        editTurnSpeed = new JTextField(20);

        c = new Canvas() {
            public void paint(Graphics g) {
                g.drawImage(Application.field, 0, 0, null);
            }
        };

        // set background
        c.setBackground(Color.black);

        Image drawImage, editImage, removeImage;
        try {
            drawImage = ImageIO.read(new File("C:/Users/rperg/IdeaProjects/FtcGui/src/images/draw.png"));
            editImage = ImageIO.read(new File("C:/Users/rperg/IdeaProjects/FtcGui/src/images/edit.png"));
            removeImage = ImageIO.read(new File("C:/Users/rperg/IdeaProjects/FtcGui/src/images/remove.png"));

            editButton = new JButton(new ImageIcon(editImage));
            drawButton = new JButton(new ImageIcon(drawImage));
            removeButton = new JButton(new ImageIcon(removeImage));
        }
        catch (IOException e) {
            System.out.println("COULDNT FIND IMAGE");
        }

        exportButton = new JButton("Export");
        saveButton = new JButton("Save");
        loadButton = new JButton("Load");

        playButton = new JButton("Play");
        stopButton = new JButton("Stop");

        timer = new Timer(50, new ActionListener() { // 50ms delay
            @Override
            public void actionPerformed(ActionEvent e) {
                Application.updateAnimation();
            }
        });

        editButton.addActionListener(bl);
        drawButton.addActionListener(bl);
        removeButton.addActionListener(bl);

        playButton.addActionListener(bl);
        stopButton.addActionListener(bl);

        exportButton.addActionListener(bl);
        saveButton.addActionListener(bl);
        loadButton.addActionListener(bl);

        editButton.setBounds(0, 5, 25, 25);
        drawButton.setBounds(30, 5, 25, 25);
        removeButton.setBounds(60, 5, 25, 25);

        exportButton.setBounds(510, 5, 80, 25);
        loadButton.setBounds(430, 5, 70, 25);
        saveButton.setBounds(350, 5, 70, 25);

        playButton.setBounds(225, 637, 70, 25);
        stopButton.setBounds(305, 637, 70, 25);

        pointX.setBounds(610, 30, 500, 25);
        editPointX.setBounds(640, 30, 100, 25);
        pointY.setBounds(610, 55, 500, 25);
        editPointY.setBounds(640, 55, 100, 25);
        pointH.setBounds(610, 80, 500, 25);
        editPointH.setBounds(640, 80, 100, 25);

        moveSpeed.setBounds(610, 110, 500, 25);
        editMoveSpeed.setBounds(695, 110, 80, 25);
        turnSpeed.setBounds(610, 135, 500, 25);
        editTurnSpeed.setBounds(695, 135, 80, 25);

        pointX.setVisible(false);
        editPointX.setVisible(false);
        pointY.setVisible(false);
        editPointY.setVisible(false);
        pointH.setVisible(false);
        editPointH.setVisible(false);
        moveSpeed.setVisible(false);
        editMoveSpeed.setVisible(false);
        turnSpeed.setVisible(false);
        editTurnSpeed.setVisible(false);

        // add listeners
        f.addKeyListener(kl);
        c.addKeyListener(kl);
        c.addMouseListener(ml);
        c.addMouseMotionListener(ml);
        editPointX.getDocument().addDocumentListener(dl);
        editPointY.getDocument().addDocumentListener(dl);
        editPointH.getDocument().addDocumentListener(dl);
        editMoveSpeed.getDocument().addDocumentListener(dl);
        editTurnSpeed.getDocument().addDocumentListener(dl);
        editTitle.getDocument().addDocumentListener(dl);

        editPointX.addKeyListener(kl);
        editPointY.addKeyListener(kl);
        editPointH.addKeyListener(kl);
        editMoveSpeed.addKeyListener(kl);
        editTurnSpeed.addKeyListener(kl);

        selectedTitle.setFont(new Font("Sans Serif", Font.BOLD, 16));
        selectedTitle.setBounds(600, 5, 500, 25);
        editTitle.setBounds(675, 5, 100, 25);
        editTitle.setVisible(false);

        c.setBounds(0, 35, 600, 600);
        status.setBounds(90, 5, 500, 25);

        f.setBackground(new Color(150, 150, 150));

        f.add(c);
        f.add(selectedTitle);
        f.add(editTitle);
        f.add(pointX);
        f.add(editPointX);
        f.add(pointY);
        f.add(editPointY);
        f.add(pointH);
        f.add(editPointH);
        f.add(editMoveSpeed);
        f.add(moveSpeed);
        f.add(editTurnSpeed);
        f.add(turnSpeed);
        f.add(status);
        f.add(drawButton);
        f.add(editButton);
        f.add(removeButton);
        f.add(exportButton);
        f.add(saveButton);
        f.add(loadButton);
        f.add(playButton);
        f.add(stopButton);

        f.setSize(800, 700);
        f.setLayout(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.show();
    }
}
