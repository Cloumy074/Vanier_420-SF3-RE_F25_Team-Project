package vaniercollege.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import vaniercollege.model.WaveSimulator;
import vaniercollege.utils.*;

import javax.sound.sampled.LineUnavailableException;
import java.io.File;
import java.io.IOException;

import static vaniercollege.utils.WaveType.*;

/**
 * The Controller for the FXML
 *
 * @author Yu Duo Zhang (2480549)
 */
public class WaveController {
    @FXML
    private Button playAnimBtn;
    @FXML
    private Button playSoundBtn;
    @FXML
    private TextField displayFreq;
    @FXML
    private TextField displayWL;
    @FXML
    private TextField displaySpeed;
    @FXML
    private NumberAxis yAxis;
    @FXML
    private TextField amplitude;
    @FXML
    private TextField angFreq;
    @FXML
    private TextField angWaveNum;
    @FXML
    private LineChart<Number, Number> chart;
    @FXML
    private Label equation;
    @FXML
    private TextField phaseDiff;
    @FXML
    private ComboBox<WaveType> type;

    XYChart.Series<Number, Number> series = new XYChart.Series<>();     // Points on the chart
    private WaveSimulator wave = new WaveSimulator();             // Wave to be used
    private String currentFilePath;                                     // Current Opened File Path (For File IO)
    private SoundStatus soundStatus = SoundStatus.PAUSED;               // Current Sound Status (For Sound Playing)
    private WaveStatus waveStatus = WaveStatus.PAUSED;                  // Current Animation Status (For Traveling Wave Animation)
    private SoundController soundController;                            // Sound Controller for sound generation
    private Timeline animTimeLine;                                      // TimeLine for Animation
    private double time = 0;                                            // Time Reference For Animation

    /**
     *  Initialize the program controller
     */
    @FXML
    public void initialize() throws LineUnavailableException, IOException {
        // Add Types in ComboBox
        type.getItems().addAll(SIN, COS);
        type.setValue(SIN);

        // Initialize points on Chart
        initializePoints();
        chart.setCreateSymbols(false);
        chart.setAnimated(false);

        infoDisplay();

        // Generate sound for current wave
        soundController = new SoundController(wave);

        updateTimeLine();
    }

    /**
     * Initialize a Timeline for the Traveling Waves Animation
     */
    private void initializePoints() {
        chart.getData().clear();
        series.getData().clear();
        series.setName("Wave");
        for (int i = 0; i < wave.getPoints().length; i++) {
            if (wave.getPoints()[i][0] != 0 && wave.getPoints()[i][1] != 0) {
                series.getData().add(new XYChart.Data<>(wave.getPoints()[i][0], wave.getPoints()[i][1]));
            }
        }
        chart.getData().add(series);
    }

    /**
     * Update the Animation Timeline for a new Wave
     */
    private void updateTimeLine() {
        animTimeLine = new Timeline(
                new KeyFrame(Duration.millis(16), event -> {
                    time += 0.05;
                    for (int i = 0; i < wave.getPoints().length; i++) {
                        XYChart.Data<Number, Number> data = series.getData().get(i);
                        double x = data.getXValue().doubleValue();
                        double y = wave.getYPos(x, time);
                        data.setYValue(y);
                    }
                })
        );

        animTimeLine.setCycleCount(Timeline.INDEFINITE);
    }

    /**
     * Update the chart for every parameter change.
     */
    @FXML
    public void updateChart() throws LineUnavailableException, IOException {
        // Set the parameters for the current Wave
        wave.setAmplitude(amplitude.getText().isEmpty() ? 0.0 : wave.convertToNum(amplitude.getText()));
        wave.setAngWaveNum(angWaveNum.getText().isEmpty() ? 0.0 : wave.convertToNum(angWaveNum.getText()));
        wave.setAngFreq(angFreq.getText().isEmpty() ? 0.0 : wave.convertToNum(angFreq.getText()));
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
        wave.setPoints(0);
        // Clear the current chart & Add the new points to the chart
        chart.getData().clear();
        initializePoints();

        infoDisplay();

        // Restart Sound Generation
        if (soundStatus == SoundStatus.PLAYING) {
            soundController.stop();
            soundController = new SoundController(wave);
            soundController.start();
        } else {
            soundController = new SoundController(wave);
        }

        updateTimeLine();
    }

    /**
     * Displays / Updates current wave's Frequency, Wave length & Velocity
     */
    private void infoDisplay() {
        // Display Frequency, Wavelength & Velocity
        displayFreq.setText(String.format("Frequency: %.2f", wave.getFrequency()));
        displayWL.setText(String.format("Wave Length: %.2f", wave.getWaveLength()));
        displaySpeed.setText(String.format("Velocity: %.2f", wave.getSpeed()));
    }

    /**
     * Update the Equation to be displayed at the bottom.
     * @return The updated equation as a String
     */
    private String updateEquation() {
        String equation = "y(x,t) = Asin(kx-ωt+δ)";

        equation = equation.replace("kx", getAngWaveNumString());
        equation = equation.replace("-ωt", getAngFreqString());
        equation = equation.replace("+δ", getPhaseDiffString());
        equation = equation.replace("sin", getTypeString());
        if (getAmplitudeString().isEmpty()) {
            return "y(x,t) = none (because amplitude is 0)";
        } else {
            equation = equation.replace("A", getAmplitudeString());
        }

        return equation.replace(" ", "");
    }

    /**
     * Get the string of the amplitude to be used for the equation display
     * @return The amplitude to be displayed as a String.
     */
    public String getAmplitudeString() {
        if (amplitude.getText().isEmpty() || amplitude.getText().equals("0")) {
            return "";
        } else if (amplitude.getText().equals("1")) {
            return " ";
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
        } else if (angFreq.getText().isEmpty() || angFreq.getText().equals("0")) {
            return " ";
        } else if (angFreq.getText().contains("-")) {
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
        if (angWaveNum.getText().isEmpty() || angWaveNum.getText().equals("0")) {
            return " ";
        } else if (angWaveNum.getText().equals("1")) {
            return "k";
        } else {
            return angWaveNum.getText() + "x";
        }
    }

    /**
     * Get the string of the phase difference to be used for the equation display
     * @return The phase difference to be displayed as a String.
     */
    public String getPhaseDiffString() {
        String result;
        result = "+" + phaseDiff.getText();
        if (phaseDiff.getText().isEmpty() || phaseDiff.getText().equals("0")) {
            return " ";
        }
        if (phaseDiff.getText().contains("-")) {
            result = "-" + phaseDiff.getText().replace("-", "");
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
            if (saveFile.getAbsolutePath().endsWith(".png")) {
                Utils.exportImage(saveFile.getAbsolutePath(), chart);
            } else {
                Utils.exportImage(saveFile.getAbsolutePath() + ".png", chart);
            }
        }
    }

    /**
     * Load a saved file.
     */
    @FXML
    public void loadFile() throws LineUnavailableException, IOException {
        String[] datas;
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Load Wave");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Wave files", "*.wave*"));
        File openFile = fileChooser.showOpenDialog(stage);
        if (openFile != null) {
            currentFilePath = openFile.getAbsolutePath();
            datas = Utils.importFile(currentFilePath);
            wave = new WaveSimulator(Double.parseDouble(datas[0]), Double.parseDouble(datas[1]), Double.parseDouble(datas[2]), Double.parseDouble(datas[3]), WaveType.valueOf(datas[4]));
            this.amplitude.setText(datas[0]);
            this.angWaveNum.setText(datas[1]);
            this.angFreq.setText(datas[2]);
            this.phaseDiff.setText(datas[3]);
            this.type.setValue(WaveType.valueOf(datas[4]));
            updateChart();
        }
    }

    /**
     * Save the current wave as a file
     */
    @FXML
    public void saveAsFile() {
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save AS");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Wave files", "*.wave*"));
        File saveFile = fileChooser.showSaveDialog(stage);
        if (saveFile != null) {
            if (saveFile.getAbsolutePath().contains(".wave")) {
                currentFilePath = saveFile.getAbsolutePath();
            } else {
                currentFilePath = saveFile.getAbsolutePath() + ".wave";
            }
            Utils.exportFile(wave, currentFilePath);
        }
    }

    /**
     * Save the current wave to the opened file, if no file is opened, should Jump to save as.
     */
    @FXML
    public void saveFile() {
        if (currentFilePath != null) {
            Utils.exportFile(wave, currentFilePath);
        } else {
            saveAsFile();
        }
    }

    /**
     * Handler for the Play Animation button. Plays or Pauses the chart animation
     */
    public void playAnim() {
        if (waveStatus == WaveStatus.PAUSED) {
            playAnimBtn.setText("Pause");
            animTimeLine.play();
            waveStatus = WaveStatus.PLAYING;
        } else if (waveStatus == WaveStatus.PLAYING) {
            playAnimBtn.setText("Play");
            animTimeLine.pause();
            waveStatus = WaveStatus.PAUSED;
        }
    }

    /**
     * Handler for the Play Sound button. Plays or stops the frequency of the current wave.
     */
    public void playSound() {
        if (soundStatus == SoundStatus.PAUSED) {
            playSoundBtn.setText("Pause");
            soundStatus = SoundStatus.PLAYING;
            soundController.start();
        } else {
            playSoundBtn.setText("Sound");
            soundStatus = SoundStatus.PAUSED;
            soundController.stop();
        }
    }

    /**
     * Reset all parameters of the current wave.
     */
    public void reset() throws LineUnavailableException, IOException {
        soundStatus = SoundStatus.PAUSED;
        soundController.stop();
        playSoundBtn.setText("Sound");
        amplitude.setText("1");
        angWaveNum.setText("1");
        angFreq.setText("1");
        phaseDiff.setText("0");
        type.setValue(SIN);
        updateChart();
        currentFilePath = null;
        animTimeLine.stop();
        time = 0;
        updateTimeLine();
    }
}
