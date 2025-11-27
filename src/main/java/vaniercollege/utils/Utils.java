package vaniercollege.utils;

import javafx.scene.SnapshotParameters;
import javafx.scene.chart.LineChart;
import javafx.scene.image.WritableImage;
import javafx.embed.swing.SwingFXUtils;
import javax.imageio.ImageIO;
import java.io.File;

/**
 *  Utility functions / tools for calculations.
 *
 * @author Yu Duo Zhang (2480549)
 */
public class Utils {
    /**
     * Export a CSV file to save the wave.
     */
    public static void exportFile() {
        // Todo
    }

    /**
     * Import a CSV file to load the wave.
     */
    public static void importFile() {
        // Todo
    }

    /**
     * Export an Image of the current wave to a specific path.
     * @param path The path that user gave.
     * @param chart The current chart.
     */
    public static void exportImage(String path, LineChart<Number, Number> chart) {
        WritableImage wi = chart.snapshot(new SnapshotParameters(), new WritableImage((int)chart.getPrefWidth(),(int)chart.getPrefHeight() ));
        File file = new File(path);
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(wi, null), "png", file);
        } catch (Exception _) {
        }
    }

    /**
     * Save the current file. If no file is currently opened, Jump to exportFile().
     */
    public static void saveFile() {
        // Todo
    }
}
