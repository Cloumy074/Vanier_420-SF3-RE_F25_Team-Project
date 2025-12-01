package vaniercollege.utils;

/**
 * An enum listing for available types of a wave, which are SIN and COS.
 *
 * @author Yu Duo Zhang (2480549)
 */
public enum WaveType {
    SIN,
    COS;

    @Override
    public String toString() {
        if (this == SIN) {
            return "sin";
        } else {
            return "cos";
        }
    }
}
