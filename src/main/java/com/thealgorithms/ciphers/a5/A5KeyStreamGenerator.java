package com.thealgorithms.ciphers.a5;

import java.util.BitSet;

public class A5KeyStreamGenerator extends CompositeLFSR {

    private static final int SESSION_KEY_LENGTH = 64; // Assuming 64-bit session key
    private static final int FRAME_COUNTER_LENGTH = 22; // Assuming 22-bit frame counter
    private static final int INITIAL_CLOCKING_CYCLES = 100;
    private static final int KEY_STREAM_LENGTH = 228; // 28.5 bytes so we need to pad bytes or something

    private BitSet initialFrameCounter;
    private BitSet frameCounter;
    private BitSet sessionKey;

    @Override
    public void initialize(BitSet sessionKey, BitSet frameCounter) {
        if (sessionKey == null || frameCounter == null) {
            throw new IllegalArgumentException("Session key and frame counter cannot be null.");
        }
        if (sessionKey.length() > SESSION_KEY_LENGTH) {
            throw new IllegalArgumentException("Session key length exceeds the maximum allowed length of " + SESSION_KEY_LENGTH + " bits.");
        }
        if (frameCounter.length() > FRAME_COUNTER_LENGTH) {
            throw new IllegalArgumentException("Frame counter length exceeds the maximum allowed length of " + FRAME_COUNTER_LENGTH + " bits.");
        }

        this.sessionKey = (BitSet) sessionKey.clone();
        this.frameCounter = (BitSet) frameCounter.clone();
        this.initialFrameCounter = (BitSet) frameCounter.clone();
        registers.clear();

        LFSR lfsr1 = new LFSR(19, 8, new int[] {13, 16, 17, 18});
        LFSR lfsr2 = new LFSR(22, 10, new int[] {20, 21});
        LFSR lfsr3 = new LFSR(23, 10, new int[] {7, 20, 21, 22});
        registers.add(lfsr1);
        registers.add(lfsr2);
        registers.add(lfsr3);

        registers.forEach(lfsr -> lfsr.initialize(sessionKey, frameCounter));
    }

    public void reInitialize() {
        if (sessionKey == null || initialFrameCounter == null) {
            throw new IllegalStateException("Cannot reinitialize before the initial initialization.");
        }
        this.initialize(sessionKey, initialFrameCounter);
    }

    public BitSet getNextKeyStream() {
        if (sessionKey == null || frameCounter == null) {
            throw new IllegalStateException("Generator not properly initialized with session key and frame counter.");
        }

        for (int cycle = 1; cycle <= INITIAL_CLOCKING_CYCLES; ++cycle) {
            this.clock();
        }

        BitSet result = new BitSet(KEY_STREAM_LENGTH);
        for (int cycle = 1; cycle <= KEY_STREAM_LENGTH; ++cycle) {
            boolean outputBit = this.clock();
            result.set(cycle - 1, outputBit);
        }

        reInitializeRegisters();
        return result;
    }

    private void reInitializeRegisters() {
        incrementFrameCounter();
        registers.forEach(lfsr -> lfsr.initialize(sessionKey, frameCounter));
    }

    private void incrementFrameCounter() {
        Utils.increment(frameCounter, FRAME_COUNTER_LENGTH);
    }
}
