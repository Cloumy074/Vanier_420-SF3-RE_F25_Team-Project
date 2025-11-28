package vaniercollege.utils;

import javafx.scene.SnapshotParameters;
import javafx.scene.chart.LineChart;
import javafx.scene.image.WritableImage;
import javafx.embed.swing.SwingFXUtils;
import vaniercollege.model.WaveSimulator;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *  Utility functions / tools for calculations.
 *
 * @author Yu Duo Zhang (2480549)
 */
public class Utils {
    /**
     * Export a CSV file to save the wave.
     */
    public static void exportFile(WaveSimulator wave, String path) {
        File file = new File(path);
        try (FileWriter fw = new FileWriter(file, false)) {
            fw.write(wave.toString());
        } catch (IOException e) {
            System.out.println("Fail to write to the file");
        }
    }

    /**
     * Import a CSV file to load the wave.
     */
    public static String[] importFile(String path) {
        File file = new File(path);
        String[] datas = new String[5];
        try (Scanner input = new Scanner(file)) {
            String data = input.nextLine();
            datas = data.split(",");
        } catch (IOException e) {
            System.out.printf("File %s does not exist%n", path);
        }

        return datas;
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
}
