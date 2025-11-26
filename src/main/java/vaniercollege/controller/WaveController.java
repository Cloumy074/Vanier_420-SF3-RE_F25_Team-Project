package vaniercollege.controller;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.*;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import vaniercollege.model.WaveSimulator;
import vaniercollege.utils.Utils;
import vaniercollege.utils.WaveType;

import static vaniercollege.utils.WaveType.*;

public class WaveController {
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
    void initialize() {
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
    void exportImage() {
        Utils.exportImage();
    }

    @FXML
    void loadFile() {
        Utils.importFile();
    }

    @FXML
    void saveAsFile() {
        Utils.exportFile();
    }

    @FXML
    void saveFile() {
        Utils.saveFile();
    }

    @FXML
    void updateChart() {
        wave.setAmplitude(amplitude.getText().isEmpty() ? 1.0 : wave.setNum(amplitude.getText()));
        wave.setAngWaveNum(angWaveNum.getText().isEmpty() ? 1.0 : wave.setNum(angWaveNum.getText()));
        wave.setAngFreq(angFreq.getText().isEmpty() ? 1.0 : wave.setNum(angFreq.getText()));
        wave.setPhaseDiff(phaseDiff.getText().isEmpty() ? 0.0 : wave.setNum(phaseDiff.getText()));
        wave.setType(type.getValue());
        
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
}
