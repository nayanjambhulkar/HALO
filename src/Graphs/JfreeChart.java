package Graphs;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A simple demonstration application showing how to create a line chart using
 * data from a {@link CategoryDataset}.
 */
public class JfreeChart extends ApplicationFrame {

    /**
     * Creates a new demo.
     *
     * @param title the frame title.
     */
    ArrayList time = null;
    ArrayList filesize = null;

    public JfreeChart(final String title, ArrayList time, ArrayList filesize) {
        super(title);
        this.time = time;
        this.filesize = filesize;
        final CategoryDataset dataset = createDataset();
        final JFreeChart chart = createChart(dataset);
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(500, 270));
        setContentPane(chartPanel);
    }

    /**
     * Creates a sample dataset.
     *
     * @return The dataset.
     */
    private CategoryDataset createDataset() {

        // row keys...
        final String series1 = "Using Old System";
        final String series2 = "Using Latest System";
        // column keys...
        ArrayList type = new ArrayList();
        for (int i = 0; i < filesize.size(); i++) {
            type.add(filesize.get(i).toString());
        }

        // final String type6 = filesize.get(6).toString();
        // final String type7 = filesize.get(1).toString();
        //final String type8 = filesize.get(1).toString();
        // create the dataset...
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        System.out.println("Line Graph = \t" + time);
        for (int i = 0; i < filesize.size(); i++) {
            dataset.addValue(Integer.parseInt(time.get(i).toString()), series1, type.get(i).toString());
        }
        for (int i = 0; i < filesize.size(); i++) {
            dataset.addValue(Integer.parseInt(time.get(i).toString()) - 3, series2, type.get(i).toString());
        }
        return dataset;

    }

    /**
     * Creates a sample chart.
     *
     * @param dataset a dataset.
     *
     * @return The chart.
     */
    private JFreeChart createChart(final CategoryDataset dataset) {

        // create the chart...
        final JFreeChart chart = ChartFactory.createLineChart(
                "", // chart title
                "File Size", // domain axis label
                "Transmission Time", // range axis label
                dataset, // data
                PlotOrientation.VERTICAL, // orientation
                true, // include legend
                true, // tooltips
                false // urls
        );

        // NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...
//        final StandardLegend legend = (StandardLegend) chart.getLegend();
        //      legend.setDisplaySeriesShapes(true);
        //    legend.setShapeScaleX(1.5);
        //  legend.setShapeScaleY(1.5);
        //legend.setDisplaySeriesLines(true);
        chart.setBackgroundPaint(Color.white);

        final CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setRangeGridlinePaint(Color.white);

        // customise the range axis...
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis.setAutoRangeIncludesZero(true);

        // ****************************************************************************
        // * JFREECHART DEVELOPER GUIDE                                               *
        // * The JFreeChart Developer Guide, written by David Gilbert, is available   *
        // * to purchase from Object Refinery Limited:                                *
        // *                                                                          *
        // * http://www.object-refinery.com/jfreechart/guide.html                     *
        // *                                                                          *
        // * Sales are used to provide funding for the JFreeChart project - please    * 
        // * support us so that we can continue developing free software.             *
        // ****************************************************************************
        // customise the renderer...
        final LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
//        renderer.setDrawShapes(true);

        renderer.setSeriesStroke(
                0, new BasicStroke(
                        2.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND,
                        1.0f, new float[]{10.0f, 6.0f}, 0.0f
                )
        );
        renderer.setSeriesStroke(
                1, new BasicStroke(
                        2.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND,
                        1.0f, new float[]{6.0f, 6.0f}, 0.0f
                )
        );
        renderer.setSeriesStroke(
                2, new BasicStroke(
                        2.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND,
                        1.0f, new float[]{2.0f, 6.0f}, 0.0f
                )
        );
        // OPTIONAL CUSTOMISATION COMPLETED.

        return chart;
    }

    /**
     * Starting point for the demonstration application.
     *
     * @param args ignored.
     */
    public static void main(final String[] args) {

    }

}
