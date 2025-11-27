package vaniercollege.controller;

import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import vaniercollege.model.WaveSimulator;
import vaniercollege.utils.*;

import java.io.File;

import static vaniercollege.utils.WaveType.*;

/**
 * The Controller for the FXML
 *
 * @author Yu Duo Zhang (2480549)
 */
public class WaveController {
    @FXML public Button playAnimBtn;
    @FXML public Button playSoundBtn;
    @FXML private NumberAxis yAxis;
    @FXML private TextField amplitude;
    @FXML private TextField angFreq;
    @FXML private TextField angWaveNum;
    @FXML private LineChart<Number, Number> chart;
    @FXML private Label equation;
    @FXML private TextField phaseDiff;
    @FXML private ComboBox<WaveType> type;

    private final WaveSimulator wave =  new WaveSimulator();

    @FXML
    public void initialize() {
        type.getItems().addAll(SIN,COS);
        type.setValue(SIN);
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName("Wave");
        for (int i = 0; i < wave.getPoints().length; i++) {
            if (wave.getPoints()[i][0] != 0 && wave.getPoints()[i][1] != 0) {
                series.getData().add(new XYChart.Data<>(wave.getPoints()[i][0], wave.getPoints()[i][1]));
            }
        }
        chart.getData().add(series);
    }

    /**
     * Export the current chart as an image to the selected place.
     */
    @FXML
    public void exportImage() {
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Export To");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG files", "*.png*"));
        File saveFile = fileChooser.showSaveDialog(stage);
        if (saveFile != null) {
            Utils.exportImage(saveFile.getAbsolutePath(),chart);
        }
    }

    /**
     * Load a saved file.
     */
    @FXML
    public void loadFile() {
        Utils.importFile();
    }

    /**
     * Save the current wave as a file
     */
    @FXML
    public void saveAsFile() {
        Utils.exportFile();
    }

    /**
     * Save the current wave to the opened file, if no file is opened, should Jump to save as.
     */
    @FXML
    public void saveFile() {
        Utils.saveFile();
    }

    /**
     * Update the chart for every parameter change.
     */
    @FXML
    public void updateChart() {
        // Set the parameters for the current Wave
        wave.setAmplitude(amplitude.getText().isEmpty() ? 1.0 : wave.convertToNum(amplitude.getText()));
        wave.setAngWaveNum(angWaveNum.getText().isEmpty() ? 1.0 : wave.convertToNum(angWaveNum.getText()));
        wave.setAngFreq(angFreq.getText().isEmpty() ? 1.0 : wave.convertToNum(angFreq.getText()));
        wave.setPhaseDiff(phaseDiff.getText().isEmpty() ? 0.0 : wave.convertToNum(phaseDiff.getText()));
        wave.setType(type.getValue());

        // Auto range the chart if it's about to pass the fixed bound. Else change the bound back to 5 and -5.
        if (wave.getAmplitude() >= 5) {
            yAxis.setAutoRanging(true);
        } else {
            yAxis.setAutoRanging(false);
            yAxis.setLowerBound(-5);
            yAxis.setUpperBound(5);
        }

        // Update the equation showing at the bottom.
        equation.setText(updateEquation());

        // Set the points based on the previous parameters.
        wave.setPoints();
        // Clear the current chart & Add the new points to the chart
        chart.getData().clear();
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName("Wave");
        for (int i = 0; i < wave.getPoints().length; i++) {
            if (wave.getPoints()[i][0] != 0 && wave.getPoints()[i][1] != 0) {
                series.getData().add(new XYChart.Data<>(wave.getPoints()[i][0], wave.getPoints()[i][1]));
            }
        }
        chart.getData().add(series);
    }

    /**
     * Update the Equation to be displayed at the bottom.
     * @return The updated equation as a String
     */
    private String updateEquation() {
        String equation = "y(x,t) = Asin(kx-ωt+δ)";

        if (!getAmplitudeString().isEmpty()) {
            equation = equation.replace("A", getAmplitudeString());
        }
        if (!getAngWaveNumString().isEmpty()) {
            equation = equation.replace("k", getAngWaveNumString());
        }
        if (!getAngFreqString().isEmpty()) {
            equation = equation.replace("-ωt", getAngFreqString());
        }
        if (!getPhaseDiffString().isEmpty()) {
            equation = equation.replace("+δ", getPhaseDiffString());
        }
        if (!getTypeString().isEmpty()) {
            equation = equation.replace("sin",  getTypeString());
        }

        return equation;
    }

    /**
     * Get the string of the amplitude to be used for the equation display
     * @return The amplitude to be displayed as a String.
     */
    public String getAmplitudeString() {
        if (amplitude.getText().isEmpty() || amplitude.getText().equals("1")) {
            return "";
        } else {
            return amplitude.getText();
        }
    }

    /**
     * Get the string of the angular frequency to be used for the equation display
     * @return The angular frequency to be displayed as a String.
     */
    public String getAngFreqString() {
        if (angFreq.getText().equals("1")) {
            return "-t";
        }
        else if (angFreq.getText().isEmpty() || angFreq.getText().equals("0")) {
            return "";
        }
        else if (angFreq.getText().contains("-")) {
            return angFreq.getText().replace("-", "+") + "t";
        } else {
            return "-" + angFreq.getText() + "t";
        }
    }

    /**
     * Get the string of the angular wave number to be used for the equation display
     * @return The angular wave number to be displayed as a String.
     */
    public String getAngWaveNumString() {
        if (angWaveNum.getText().isEmpty() || angWaveNum.getText().equals("1")) {
            return "";
        } else {
            return angWaveNum.getText();
        }
    }

    /**
     * Get the string of the phase difference to be used for the equation display
     * @return The phase difference to be displayed as a String.
     */
    public String getPhaseDiffString() {
        String result;
        if (phaseDiff.getText().isEmpty() || phaseDiff.getText().equals("0")) {
            result = "";
        } else {
            result =  "+" + phaseDiff.getText();
        }
        if (phaseDiff.getText().contains("-")) {
            result = "-" +  phaseDiff.getText().replace("-", "");
        }
        if (phaseDiff.getText().contains("\\pi")) {
            result = "+" + phaseDiff.getText().replace("\\pi", "π");
        }
        return result;
    }

    /**
     * Get the String of the type to be used for the equation display
     * @return The type to be displayed as a String.
     */
    public String getTypeString() {
        return type.getValue().toString();
    }

    /**
     * Handler for the Play Animation button. Plays or Pauses the chart animation
     */
    public void playAnim() {
    }

    /**
     * Handler for the Play Sound button. Plays or stops the frequency of the current wave.
     */
    public void playSound() {
    }

    /**
     * Reset all parameters of the current wave.
     */
    public void reset() {
        amplitude.setText("1");
        angWaveNum.setText("1");
        angFreq.setText("1");
        phaseDiff.setText("0");
        type.setValue(SIN);
        updateChart();
    }
}
