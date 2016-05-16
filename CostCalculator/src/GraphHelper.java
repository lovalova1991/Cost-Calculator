import java.awt.Color;
import java.awt.Font;
import java.util.LinkedList;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.IntervalMarker;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.Layer;
import org.jfree.ui.RectangleAnchor;
import org.jfree.ui.RefineryUtilities;
import org.jfree.ui.TextAnchor;


public class GraphHelper extends ApplicationFrame
{
	
	public GraphHelper(final String title, LinkedList<DFGs> result) 
	{
        super(title);
        IntervalXYDataset dataset = createDataset(result);
        JFreeChart chart = createChart(dataset);
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);
    }
	
	private IntervalXYDataset createDataset(LinkedList<DFGs> result) 
	{
		int maxCost = 0;
		for(DFGs d : result)
		{
			if(d.getCost() > maxCost)
				maxCost = d.getCost();
		}
        final XYSeries series = new XYSeries("Solutions for cost");
        //qui posso cambiare il range dei risultati che vedo
        for(int i = 0; i < maxCost + 2; i++)
        {
        	int count = 0;
        	for(DFGs d : result)
        	{
        		if(d.getCost() == i)
        		{
        			count ++;
        		}
        	}
        	series.add(i,count);
        }
        final XYSeriesCollection dataset = new XYSeriesCollection(series);
        return dataset;
    }
	
	private JFreeChart createChart(IntervalXYDataset dataset) 
	{
        final JFreeChart chart = ChartFactory.createXYBarChart(
            "Distribution of Solutions per cost",
            "Cost", 
            false,
            "Number of Solutions", 
            dataset,
            PlotOrientation.VERTICAL,
            true,
            true,
            false
        );
        XYPlot plot = (XYPlot) chart.getPlot();
        final IntervalMarker target = new IntervalMarker(400.0, 700.0);
        target.setLabel("Target Range");
        target.setLabelFont(new Font("SansSerif", Font.ITALIC, 11));
        target.setLabelAnchor(RectangleAnchor.LEFT);
        target.setLabelTextAnchor(TextAnchor.CENTER_LEFT);
        target.setPaint(new Color(222, 222, 255, 128));
        plot.addRangeMarker(target, Layer.BACKGROUND);
        return chart;    
    }

	public void showGraph(LinkedList<DFGs> result)
	{
		 final GraphHelper graph = new GraphHelper("Solutions per cost", result);
	        graph.pack();
	        RefineryUtilities.centerFrameOnScreen(graph);
	        graph.setVisible(true);
	}
}
