import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WeatherDashboard extends JFrame implements ActionListener {
    private JPanel graphPanel;
    private JButton btnPlot;
    private JButton btnClear;

    public WeatherDashboard() {
        setTitle("Interactive Weather Data Dashboard");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create graph panel
        graphPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                plotData(g);
            }
        };
        graphPanel.setBackground(Color.BLUE);
        graphPanel.setPreferredSize(new Dimension(600, 400));
        add(graphPanel, BorderLayout.CENTER);

        // Create control panel
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        controlPanel.setPreferredSize(new Dimension(800, 100));

        btnPlot = new JButton("Implement Data");
        btnPlot.addActionListener(this);
        controlPanel.add(btnPlot);

        btnClear = new JButton("Clear");
        btnClear.addActionListener(this);
        controlPanel.add(btnClear);

        add(controlPanel, BorderLayout.SOUTH);
    }

    private void plotData(Graphics g) {
        // Generate random weather data points
        List<Point> data = generateRandomData();

        // Plot data points on graph panel
        g.setColor(Color.GREEN);
        for (Point point : data) {
            g.fillOval(point.x, point.y, 10, 10);
        }
    }

    private List<Point> generateRandomData() {
        List<Point> data = new ArrayList<>();
        Random random = new Random();
        int numPoints = random.nextInt(11) + 5;  // Generate between 5 to 15 points
        for (int i = 0; i < numPoints; i++) {
            int x = random.nextInt(580) + 10;  // Random x-coordinate between 10 and 590
            int y = random.nextInt(370) + 10;  // Random y-coordinate between 10 and 380
            data.add(new Point(x, y));
        }
        return data;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnPlot) {
            graphPanel.repaint();
        } else if (e.getSource() == btnClear) {
            graphPanel.repaint();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            WeatherDashboard weatherDashboard = new WeatherDashboard();
            weatherDashboard.setVisible(true);
        });
    }
}
