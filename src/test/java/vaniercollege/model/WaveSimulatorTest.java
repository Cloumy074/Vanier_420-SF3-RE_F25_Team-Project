package vaniercollege.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

import vaniercollege.utils.WaveType;

/**
 * Tests for WaveSimulator calculation methods.
 *
 * @author Yu Duo Zhang (2480549)
 */
public class WaveSimulatorTest {
    private WaveSimulator waveSimulator;

    @Test
    @DisplayName("Test default constructor values")
    void testDefaultConstructor() {
        waveSimulator = new WaveSimulator();
        assertEquals(1.0, waveSimulator.getAmplitude(), 0);
        assertEquals(1.0, waveSimulator.getAngWaveNum(), 0);
        assertEquals(1.0, waveSimulator.getAngFreq(), 0);
        assertEquals(0.0, waveSimulator.getPhaseDiff(), 0);
        assertEquals(WaveType.SIN, waveSimulator.getType());
        assertNotNull(waveSimulator.getPoints());
        assertEquals(150, waveSimulator.getPoints().length);
    }

    @Test
    @DisplayName("Test parameterized constructor")
    void testParameterizedConstructor() {
        WaveSimulator wave = new WaveSimulator(2.5, 0.5, 3.14, 1.57, WaveType.COS);

        assertEquals(2.5, wave.getAmplitude(), 0);
        assertEquals(0.5, wave.getAngWaveNum(), 0);
        assertEquals(3.14, wave.getAngFreq(), 0);
        assertEquals(1.57, wave.getPhaseDiff(), 0);
        assertEquals(WaveType.COS, wave.getType());
    }

    @Test
    @DisplayName("Test frequency calculation")
    void testGetFrequency() {
        waveSimulator = new WaveSimulator();

        // Default angFreq (= 1)
        double expectedFreq = 1.0 / (2 * Math.PI);
        assertEquals(expectedFreq, waveSimulator.getFrequency(), 0);

        // Custom angular frequency (= 2PI)
        waveSimulator.setAngFreq(2 * Math.PI);
        assertEquals(1.0, waveSimulator.getFrequency(), 0);
    }

    @Test
    @DisplayName("Test speed calculation")
    void testGetSpeed() {
        waveSimulator = new WaveSimulator();

        // Test with default values (angFreq = 1, angWaveNum = 1)
        assertEquals(1.0, waveSimulator.getSpeed(), 0);

        // Test with custom values
        waveSimulator.setAngFreq(10.0);
        waveSimulator.setAngWaveNum(2.0);
        assertEquals(5.0, waveSimulator.getSpeed(), 0);
    }

    @Test
    @DisplayName("Test wavelength calculation")
    void testGetWaveLength() {
        waveSimulator = new WaveSimulator();

        // Test with default values
        double expectedWaveLength = waveSimulator.getSpeed() / waveSimulator.getFrequency();
        assertEquals(expectedWaveLength, waveSimulator.getWaveLength(), 0);

        // Test with known values
        waveSimulator.setAngFreq(2 * Math.PI); // frequency = 1 Hz
        waveSimulator.setAngWaveNum(1.0); // speed = 2π
        double expectedWL = (2 * Math.PI);
        assertEquals(expectedWL, waveSimulator.getWaveLength(), 0);
    }

    @Test
    @DisplayName("Test Y position calculation for SIN wave")
    void testGetYPosSinWave() {
        waveSimulator = new WaveSimulator();

        waveSimulator.setType(WaveType.SIN);
        waveSimulator.setAmplitude(2.0);
        waveSimulator.setAngWaveNum(1.0);
        waveSimulator.setAngFreq(1.0);
        waveSimulator.setPhaseDiff(0.0);

        // Test at x=0, t=0
        double expected = 2.0 * Math.sin(0);
        assertEquals(expected, waveSimulator.getYPos(0, 0), 0);

        // Test at x=π/2, t=0
        double expectedPiOver2 = 2.0 * Math.sin(Math.PI / 2);
        assertEquals(expectedPiOver2, waveSimulator.getYPos(Math.PI / 2, 0), 0);

        // Test with phase difference
        waveSimulator.setPhaseDiff(Math.PI / 2);
        double expectedWithPhase = 2.0 * Math.sin(0 + Math.PI / 2);
        assertEquals(expectedWithPhase, waveSimulator.getYPos(0, 0), 0);
    }

    @Test
    @DisplayName("Test Y position calculation for COS wave")
    void testGetYPosCosWave() {
        waveSimulator = new WaveSimulator();

        waveSimulator.setType(WaveType.COS);
        waveSimulator.setAmplitude(1.5);
        waveSimulator.setAngWaveNum(2.0);
        waveSimulator.setAngFreq(1.0);
        waveSimulator.setPhaseDiff(0.0);

        // Test at x=0, t=0
        double expected = 1.5 * Math.cos(0);
        assertEquals(expected, waveSimulator.getYPos(0, 0), 0);

        // Test at x=π/4, t=0 (2*π/4 = π/2)
        double expectedPiOver4 = 1.5 * Math.cos(Math.PI / 2);
        assertEquals(expectedPiOver4, waveSimulator.getYPos(Math.PI / 4, 0), 0);
    }

    @Test
    @DisplayName("Test Y position with time variation")
    void testGetYPosWithTime() {
        waveSimulator = new WaveSimulator();

        waveSimulator.setType(WaveType.SIN);
        waveSimulator.setAmplitude(1.0);
        waveSimulator.setAngWaveNum(1.0);
        waveSimulator.setAngFreq(2.0);
        waveSimulator.setPhaseDiff(0.0);

        double x = Math.PI / 4;
        double t = Math.PI / 8;

        // y = sin(x - 2t) = sin(π/4 - 2*π/8) = sin(π/4 - π/4) = sin(0) = 0
        double expected = Math.sin(0);
        assertEquals(expected, waveSimulator.getYPos(x, t), 0);
    }
}
