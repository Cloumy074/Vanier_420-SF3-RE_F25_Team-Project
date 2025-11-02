/**
 *  Core logic for wave calculations.
 */
public class WaveSimulator {
    private double amplitude;
    private double angularWaveNum;
    private double angFreq;
    private double phaseDiff;
    private WaveType type;

    /**
     * No-args Contructor with Sample SIN wave data.
     */
    public WaveSimulator() {
        this.amplitude = 1;
        this.angularWaveNum = 1;
        this.angFreq = 1;
        this.phaseDiff = 0;
        this.type = WaveType.SIN;
    }
    
    /**
     * Full-args Contructor 
     * @param amplitude The Amplitude of the Wave
     * @param angularWaveNum The Angular Wave Number of the Wave
     * @param angFreq The Angular Frequency of the Wave
     * @param phaseDiff The Phase Difference of the Wave
     * @param type The Type of the Wave (SIN or COS)
     */
    public WaveSimulator(double amplitude, double angularWaveNum, double angFreq, double phaseDiff, WaveType type) {
        this.amplitude = amplitude;
        this.angularWaveNum = angularWaveNum;
        this.angFreq = angFreq;
        this.phaseDiff = phaseDiff;
        this.type = type;
    }

    public double getAmplitude() {
        return this.amplitude;
    }

    public void setAmplitude(double amplitude) {
        this.amplitude = amplitude;
    }

    public double getAngularWaveNum() {
        return this.angularWaveNum;
    }

    public void setAngularWaveNum(double angularWaveNum) {
        this.angularWaveNum = angularWaveNum;
    }

    public double getAngFreq() {
        return this.angFreq;
    }

    public void setAngFreq(double angFreq) {
        this.angFreq = angFreq;
    }

    public double getPhaseDiff() {
        return this.phaseDiff;
    }

    public void setPhaseDiff(double phaseDiff) {
        this.phaseDiff = phaseDiff;
    }

    public WaveType getType() {
        return this.type;
    }

    public void setType(WaveType type) {
        this.type = type;
    }
}
