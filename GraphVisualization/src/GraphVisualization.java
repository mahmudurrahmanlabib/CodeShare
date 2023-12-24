import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class GraphVisualization extends JFrame {
    public GraphVisualization() {
        setTitle("Graph Visualization");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new GridLayout(2, 2));

        add(createPieChart());
        add(createColumnChart());
        add(createLineChart());
        add(createBarChart());
    }

    private JPanel createPieChart() {
        JPanel piePanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("Pie Chart - Mobile Users");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        piePanel.add(titleLabel, BorderLayout.NORTH);

        Map<String, Double> userPercentages = new HashMap<>();
        userPercentages.put("iPhone User", 30.0);
        userPercentages.put("Samsung User", 35.0);
        userPercentages.put("Nokia User", 2.0);
        userPercentages.put("Xiaomi User", 18.0);
        userPercentages.put("Huawei User", 15.0);

        PieChart pieChart = new PieChart(userPercentages);
        piePanel.add(pieChart, BorderLayout.CENTER);

        return piePanel;
    }

    private JPanel createColumnChart() {
        JPanel columnPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("Column Chart - Mobile Users Over Years");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        columnPanel.add(titleLabel, BorderLayout.NORTH);

        Map<String, Integer> mobileData = new HashMap<>();
        mobileData.put("iPhone", 2000);
        mobileData.put("Samsung", 2500);
        mobileData.put("Nokia", 1800);
        mobileData.put("Xiaomi", 3000);
        mobileData.put("Huawei", 2200);

        ColumnChart columnChart = new ColumnChart(mobileData);
        columnPanel.add(columnChart, BorderLayout.CENTER);

        return columnPanel;
    }

    private JPanel createLineChart() {
        JPanel linePanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("Line Chart - Mobile Users Over Years");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        linePanel.add(titleLabel, BorderLayout.NORTH);

        int[] years = {2000, 2005, 2010, 2015, 2020, 2023};
        int[] users = {1000, 1500, 2200, 2800, 3500, 4000};

        LineChart lineChart = new LineChart(years, users);
        linePanel.add(lineChart, BorderLayout.CENTER);

        return linePanel;
    }

    private JPanel createBarChart() {
        JPanel barPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("Bar Chart - Comparison of Mobile Brands");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        barPanel.add(titleLabel, BorderLayout.NORTH);

        Map<String, Integer> comparisonData = new HashMap<>();
        comparisonData.put("iPhone", 300);
        comparisonData.put("Samsung", 400);
        comparisonData.put("Xiaomi", 200);
        comparisonData.put("Huawei", 350);

        BarChart barChart = new BarChart(comparisonData);
        barPanel.add(barChart, BorderLayout.CENTER);

        return barPanel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GraphVisualization graphVisualization = new GraphVisualization();
            graphVisualization.setVisible(true);
        });
    }
}

class PieChart extends JPanel {
    private Map<String, Double> data;

    public PieChart(Map<String, Double> data) {
        this.data = data;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        double total = data.values().stream().mapToDouble(Double::doubleValue).sum();
        double startAngle = 0.0;

        int width = getWidth();
        int height = getHeight();
        int x = width / 4;
        int y = height / 4;
        int diameter = Math.min(width, height) / 2;

        int i = 0;
        for (Map.Entry<String, Double> entry : data.entrySet()) {
            double percentage = entry.getValue();
            double sweepAngle = (percentage / total) * 360.0;

            g2d.setColor(getRandomColor(i));
            g2d.fillArc(x, y, diameter, diameter, (int) startAngle, (int) sweepAngle);
            g2d.fillRect(width - 50, y + (i * 20), 10, 10);
            g2d.drawString(entry.getKey(), width - 35, y + 10 + (i * 20));

            startAngle += sweepAngle;
            i++;
        }
    }

    private Color getRandomColor(int i) {
        float hue = (i * 60) % 360 / 360.0f;
        return Color.getHSBColor(hue, 0.6f, 0.9f);
    }
}

class ColumnChart extends JPanel {
    private Map<String, Integer> data;

    public ColumnChart(Map<String, Integer> data) {
        this.data = data;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();
        int x = 50;
        int y = 50;
        int barWidth = 50;

        g2d.setColor(Color.BLUE);
        int i = 0;
        for (Map.Entry<String, Integer> entry : data.entrySet()) {
            int barHeight = (int) ((double) entry.getValue() / 50.0 * (height - 100));
            g2d.fillRect(x + (i * 100), height - barHeight, barWidth, barHeight);
            g2d.drawString(entry.getKey(), x + (i * 100), height - 10);
            i++;
        }
    }
}

class LineChart extends JPanel {
    private int[] years;
    private int[] users;

    public LineChart(int[] years, int[] users) {
        this.years = years;
        this.users = users;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();
        int xMargin = 50;
        int yMargin = 50;

        g2d.setColor(Color.BLUE);
        for (int i = 0; i < years.length - 1; i++) {
            int x1 = xMargin + ((width - 2 * xMargin) * i / (years.length - 1));
            int y1 = height - yMargin - ((height - 2 * yMargin) * users[i] / 4000);

            int x2 = xMargin + ((width - 2 * xMargin) * (i + 1) / (years.length - 1));
            int y2 = height - yMargin - ((height - 2 * yMargin) * users[i + 1] / 4000);

            g2d.drawLine(x1, y1, x2, y2);
        }

        g2d.setColor(Color.BLACK);
        for (int i = 0; i < years.length; i++) {
            int x = xMargin + ((width - 2 * xMargin) * i / (years.length - 1));
            int y = height - yMargin;
            g2d.drawString(String.valueOf(years[i]), x - 10, y + 20);
        }

        for (int i = 0; i <= 4; i++) {
            int x = xMargin;
            int y = height - yMargin - ((height - 2 * yMargin) * i / 4);
            g2d.drawString(String.valueOf(i * 1000), x - 40, y);
        }
    }
}

class BarChart extends JPanel {
    private Map<String, Integer> data;

    public BarChart(Map<String, Integer> data) {
        this.data = data;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();
        int x = 50;
        int y = 50;
        int barWidth = 100;

        int i = 0;
        for (Map.Entry<String, Integer> entry : data.entrySet()) {
            int barHeight = (int) ((double) entry.getValue() / 50.0 * (height - 100));
            g2d.fillRect(x + (i * 150), height - barHeight, barWidth, barHeight);
            g2d.drawString(entry.getKey(), x + (i * 150), height - 10);
            i++;
        }
    }
}
