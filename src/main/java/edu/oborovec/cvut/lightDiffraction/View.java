package edu.oborovec.cvut.lightDiffraction;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by oborovec on 28/5/2013.
 * @author Ondrej Borovec
 */
public class View extends JPanel {

    private int waveLength;
    private int slitDistance;
    private int slitWidth;

    private final int BEAN_WIDTH_MAX;
    private final int MAX_DISTANCE;
    private final int DISPLAY_ZOOM;

    public View(int waveLength, int slitDistance, int slitWidth, int maxBeanWidth, int maxDistance, int displayZoom){
        this.setValues(waveLength, slitDistance, slitWidth);
        this.setBackground(Color.GRAY);
        BEAN_WIDTH_MAX = maxBeanWidth;
        MAX_DISTANCE = maxDistance;
        DISPLAY_ZOOM = displayZoom;
    }

    public void setValues(int waveLength, int slitDistance, int slitWidth){
        this.waveLength =waveLength;
        this.slitDistance =slitDistance;
        this.slitWidth =slitWidth;
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        int width = getSize().width;
        int height = getSize().height;

        g.setColor(Color.BLACK);
        g.fillRect(0, height-40,width,20);

        if (slitWidth !=0){
            g.setColor(this.setColor());
            g.fillRect((width/2)-(slitWidth /2), height-41, slitWidth,40);

            int lightBeanWidth = Math.min(slitWidth, BEAN_WIDTH_MAX);
            Graphics2D g2d = (Graphics2D)g;
            g2d.setStroke(new BasicStroke(lightBeanWidth--));

            Point origin = new Point(width/2,height-40);
            ArrayList<Point> finalPoints = vypoctiBody(width/2, counrDistance());

            g.drawLine(origin.x, origin.y, finalPoints.get(0).x, finalPoints.get(0).y);

            for (int i=2;i<finalPoints.size();i+=2){
                if (lightBeanWidth>0){
                    g2d.setStroke(new BasicStroke(lightBeanWidth--));
                    g.drawLine(origin.x, origin.y, finalPoints.get(i).x, finalPoints.get(i).y);
                    g.drawLine(origin.x, origin.y, finalPoints.get(i+1).x, finalPoints.get(i+1).y);
                }

            }
        }
        align(g, width);

    }

    private Color setColor(){
        if (waveLength <=430)return (new Color( 128, 0, 128 ));//purple
        if (waveLength >430 && waveLength <=460)return (Color.BLUE);
        if (waveLength >460 && waveLength <=490)return(Color.BLUE.brighter());
        if (waveLength >490 && waveLength <=550)return(Color.GREEN);
        if (waveLength >550 && waveLength <=590)return(Color.YELLOW);
        if (waveLength >590 && waveLength <=650)return(Color.ORANGE);
        if (waveLength >650 && waveLength <=720)return(Color.RED);
        if (waveLength >720)return(Color.RED.darker());
        return Color.BLACK;
    }

    private int counrDistance(){
        int height = getSize().height - 45;
        return (int)(height*(1- slitDistance /(double)MAX_DISTANCE)) + 5;
    }

    private ArrayList<Point> vypoctiBody(int maximumOdchylky, int vzdalenost){
        ArrayList<Point> finalPoints = new ArrayList<>();
        double odchylka=0;
        int i=0;
        while(odchylka<maximumOdchylky){
            i++;
            finalPoints.add(new Point(maximumOdchylky-(int)odchylka,vzdalenost));
            finalPoints.add(new Point((maximumOdchylky+(int)odchylka),vzdalenost));
            odchylka = DISPLAY_ZOOM * Math.tan(countAngle(i)) * slitDistance;
        }
        return finalPoints;
    }

    private double countAngle(int degrees){
        return Math.asin(degrees* waveLength *Math.pow(10, -9) /(slitWidth *Math.pow(10, -6)));
    }

    private void align(Graphics g, int width){
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, width, counrDistance());
    }

}