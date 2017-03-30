package edu.oborovec.cvut.lightDiffraction;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

/**
 * Created by oborovec on 28/5/2013.
 * @author Ondrej Borovec
 */
public class ControlBar extends JPanel {

    public JTextField numberText;
    public JSlider slider;
    public int value;

    public ControlBar(String title, String typValue, int min, int max, int entryValue, int sliderMin, int sliderMax) {
        super(new GridLayout(2, 1));
        this.value=entryValue;
        setBorder(BorderFactory.createTitledBorder(title));
        Container values = new Container();
        GridLayout con = new GridLayout(1,2);
        values.setLayout(con);
        values.add(numberText=new JTextField(Integer.toString(entryValue), 8));
        values.add(new JLabel(typValue));
        add(values);
        slider = new JSlider(JSlider.HORIZONTAL, min, max, entryValue);
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {

                value = ((JSlider)e.getSource()).getValue();
                numberText.setText(Integer.toString(value));
            }
        });
        slider.setMinorTickSpacing(sliderMin);
        slider.setMajorTickSpacing(sliderMax);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        add(slider);
    }

    public int getValue(){
        return this.value;
    }
}