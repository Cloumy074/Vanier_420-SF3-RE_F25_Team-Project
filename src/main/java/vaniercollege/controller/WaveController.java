package vaniercollege.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
    private LineChart<Double, Double> chart;

    @FXML
    private Label equation;

    @FXML
    private TextField phaseDiff;

    @FXML
    private ComboBox<WaveType> type;

    @FXML
    void initialize() {
        type.getItems().addAll(SIN,COS);
        type.setValue(SIN);
    }

    @FXML
    void exportImage(ActionEvent event) {

    }

    @FXML
    void loadFile(ActionEvent event) {

    }

    @FXML
    void saveAsFile(ActionEvent event) {

    }

    @FXML
    void saveFile(ActionEvent event) {

    }

    @FXML
    void updateChart() {
        equation.setText(String.format("y(x,t) = %s%s(%sx%s%s)",
                getAmplitude(), getType(), getAngWaveNum(), getAngFreq(), getPhaseDiff()));
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
        if (phaseDiff.getText().contains("\\pi")) {
            result = "+" + phaseDiff.getText().replace("\\pi", "π");
        }
        return result;
    }

    public String getType() {
        return type.getValue().toString();
    }
}
