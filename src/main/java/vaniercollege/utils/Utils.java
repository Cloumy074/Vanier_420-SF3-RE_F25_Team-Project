package vaniercollege.utils;

import javafx.scene.SnapshotParameters;
import javafx.scene.chart.LineChart;
import javafx.scene.image.WritableImage;
import javafx.embed.swing.SwingFXUtils;
import javax.imageio.ImageIO;
import java.io.File;

/**
 *  Utility functions / tools for calculations.
 */
public class Utils {
    public static void exportFile() {
        // Todo
    }

    public static void importFile() {
        // Todo
    }

    public static void exportImage(String path, LineChart<Number, Number> chart) {
        WritableImage wi = chart.snapshot(new SnapshotParameters(), new WritableImage((int)chart.getPrefWidth(),(int)chart.getPrefHeight() ));
        File file = new File(path);
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(wi, null), "png", file);
        } catch (Exception s) {
        }
    }

    public static void saveFile() {
        // Todo
    }
}
