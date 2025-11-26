package vaniercollege.controller;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.*;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import vaniercollege.model.WaveSimulator;
import vaniercollege.utils.Utils;
import vaniercollege.utils.WaveType;

import java.io.File;

import static vaniercollege.utils.WaveType.*;

public class WaveController {
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

    @FXML
    public void loadFile() {
        Utils.importFile();
    }

    @FXML
    public void saveAsFile() {
        Utils.exportFile();
    }

    @FXML
    public void saveFile() {
        Utils.saveFile();
    }

    @FXML
    public void updateChart() {
        wave.setAmplitude(amplitude.getText().isEmpty() ? 1.0 : wave.setNum(amplitude.getText()));
        wave.setAngWaveNum(angWaveNum.getText().isEmpty() ? 1.0 : wave.setNum(angWaveNum.getText()));
        wave.setAngFreq(angFreq.getText().isEmpty() ? 1.0 : wave.setNum(angFreq.getText()));
        wave.setPhaseDiff(phaseDiff.getText().isEmpty() ? 0.0 : wave.setNum(phaseDiff.getText()));
        wave.setType(type.getValue());

        if (wave.getAmplitude() >= 5) {
            yAxis.setAutoRanging(true);
        } else {
            yAxis.setAutoRanging(false);
            yAxis.setLowerBound(-5);
            yAxis.setUpperBound(5);
        }
        
        equation.setText(String.format("y(x,t) = %s%s(%sx%s%s)",
                getAmplitude(), getType(), getAngWaveNum(), getAngFreq(), getPhaseDiff()));
        
        wave.setPoints();
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

    public String getAmplitude() {
        if (amplitude.getText().isEmpty() || amplitude.getText().equals("1")) {
            return "";
        } else {
            return amplitude.getText();
        }
    }

    public String getAngFreq() {
        if (angFreq.getText().equals("1")) {
            return "t";
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

    public String getAngWaveNum() {
        if (angWaveNum.getText().isEmpty() || angWaveNum.getText().equals("1")) {
            return "";
        } else {
            return angWaveNum.getText();
        }
    }

    public String getPhaseDiff() {
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

    public String getType() {
        return type.getValue().toString();
    }

    public void playAnim() {
    }

    public void playSound() {
    }

    public void reset() {
        amplitude.setText("1");
        angWaveNum.setText("1");
        angFreq.setText("1");
        phaseDiff.setText("0");
        type.setValue(SIN);
        updateChart();
    }
}
