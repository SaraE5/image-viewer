package software.ulpgc.imageviewer.swing;

import software.ulpgc.imageviewer.ImageDisplay;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SwingImageDisplay extends JPanel implements ImageDisplay {
    private Shift shift = Shift.Null;
    private Released released = Released.Null;
    private int initShift;
    private List<Paint> paints = new ArrayList<>();
    private JButton prevButton;
    private JButton nextButton;

    public SwingImageDisplay() {
        this.addMouseListener(mouseListener());
        this.addMouseMotionListener(mouseMotionListener());

        prevButton = new JButton("<");
        nextButton = new JButton(">");
        prevButton.addActionListener(e -> released.offset(-getWidth() / 2));
        nextButton.addActionListener(e -> released.offset(getWidth() / 2));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2));
        buttonPanel.add(prevButton);
        buttonPanel.add(nextButton);

        this.setLayout(new BorderLayout());
        this.add(buttonPanel, BorderLayout.SOUTH);
    }

    private MouseListener mouseListener() {
        return new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {
                initShift = e.getX();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                released.offset(e.getX() - initShift);
            }

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) { }
        };
    }

    private MouseMotionListener mouseMotionListener() {
        return new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                shift.offset(e.getX() - initShift);
            }

            @Override
            public void mouseMoved(MouseEvent e) {}
        };
    }

    @Override
    public void paint(String id, int offset) {
        paints.add(new Paint(id, offset));
        repaint();
    }

    @Override
    public void clear() {
        paints.clear();
    }

    private static final Map<String,Color> colors = Map.of(
            "red", Color.RED,
            "green", Color.GREEN,
            "blue", Color.BLUE
    );
    @Override
    public void paint(Graphics g) {
        // Elimina el código anterior y usa ImageIO para cargar y mostrar las imágenes
        for (Paint paint : paints) {
            try {
                BufferedImage image = ImageIO.read(new File("images/" + paint.id + ".jpg"));
                g.drawImage(image, paint.offset, 0, this);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void on(Shift shift) {
        this.shift = shift != null ? shift : Shift.Null;
    }

    @Override
    public void on(Released released) {
        this.released = released != null ? released : Released.Null;
    }

    private record Paint(String id, int offset) {
    }
}
