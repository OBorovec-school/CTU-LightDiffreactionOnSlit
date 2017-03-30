package edu.oborovec.cvut.lightDiffraction;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by oborovec on 3/30/2017.
 */
public class Config {

    public static final String CONFIG_FILE_NAME = "initValues.properties";
    public static final String WAVE_LENGTH = "wave.length";
    public static final String WAVE_LENGTH_MIN = "wave.length.min";
    public static final String WAVE_LENGTH_MAX = "wave.length.max";
    public static final String SLIT_DISTANCE = "slit.distance";
    public static final String SLIT_DISTANCE_MAX = "slit.distance.max";
    public static final String SLIT_WIDTH = "slit.width";
    public static final String SLIT_WIDTH_MAX = "slit.width.max";
    public static final String BEANS_WIDTH_MAX = "beans.width.max";
    public static final String DISPLAY_ZOOM = "display.zoom";

    private Properties properties;

    public Config() throws IOException {
        properties = new Properties();
        InputStream input = getClass().getClassLoader().getResourceAsStream(CONFIG_FILE_NAME);;
        properties.load(input);
    }

    public int getWaveLegth(){
        return Integer.parseInt(properties.getProperty(WAVE_LENGTH));
    }
    public int getWaveLegthMin(){
        return Integer.parseInt(properties.getProperty(WAVE_LENGTH_MIN));
    }
    public int getWaveLegthMax(){
        return Integer.parseInt(properties.getProperty(WAVE_LENGTH_MAX));
    }
    public int getSlitDistance(){
        return Integer.parseInt(properties.getProperty(SLIT_DISTANCE));
    }
    public int getSlitDistanceMax(){
        return Integer.parseInt(properties.getProperty(SLIT_DISTANCE_MAX));
    }
    public int getSlitWidth(){
        return Integer.parseInt(properties.getProperty(SLIT_WIDTH));
    }
    public int getSlitWidthMax(){
        return Integer.parseInt(properties.getProperty(SLIT_WIDTH_MAX));
    }
    public int getBeanWidthMax(){
        return Integer.parseInt(properties.getProperty(BEANS_WIDTH_MAX));
    }
    public int getDisplayZoom(){
        return Integer.parseInt(properties.getProperty(DISPLAY_ZOOM));
    }
}
