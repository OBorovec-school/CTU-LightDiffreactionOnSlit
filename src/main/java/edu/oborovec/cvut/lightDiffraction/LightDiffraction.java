package edu.oborovec.cvut.lightDiffraction;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.IOException;
import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Created by oborovec on 28/5/2013.
 * @author Ondrej Borovec
 */

public class LightDiffraction extends JApplet {
    private View mainView;
    private Config config;
    private ControlBar waveLengthControl;
    private ControlBar slitDistanceControl;
    private ControlBar slitWidthControl;

    @Override
    public void init() {
        try {
            config = new Config();
            createWindow(config);
            while (true){
                this.mainView.setValues(this.waveLengthControl.getValue(),
                        this.slitDistanceControl.getValue(),
                this.slitWidthControl.getValue());
                this.mainView.repaint();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
     }

    private void createWindow(Config c) {
        this.waveLengthControl =new ControlBar("Wave length","nm",
                c.getWaveLegthMin(), c.getWaveLegthMax(), c.getWaveLegth(),
                50,100);
        this.slitWidthControl =new ControlBar("Slit width","µm",
                0, c.getSlitWidthMax(), c.getSlitWidth(),
                1,5);
        this.slitDistanceControl =new ControlBar("Slit distance","cm",
                30, c.getSlitDistanceMax(), c.getSlitDistance(),
                10,50);
        JFrame frame = new JFrame("Light diffraction on slit");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 400);
        frame.setVisible(true);
        BorderLayout frameLO = new BorderLayout();
        frame.setLayout(frameLO);
        JPanel panel= new JPanel(new GridLayout(3,1));
        panel.add(this.waveLengthControl);
        panel.add(this.slitWidthControl);
        panel.add(this.slitDistanceControl);
        frame.add(panel,frameLO.WEST);
        mainView =new View(this.waveLengthControl.getValue(),
                this.slitDistanceControl.getValue(),
                this.slitWidthControl.getValue(),
                c.getBeanWidthMax(), c.getSlitDistanceMax(), c.getDisplayZoom());
        frame.add(mainView,frameLO.CENTER);
        frame.validate();
    }
}
