class Solution {
    private static final long MODULUS_CONSTANT = 1_000_000_007L;

    public int zigZagArrays(int n, int l, int r) {
        long cosmicRangeDimension = (long) r - l + 1;
        int temporalLengthFactor = n;

        long[] precomputedQuantumValues =
                new long[temporalLengthFactor + 1];

        for (int i = 0; i <= temporalLengthFactor; i++) {
            precomputedQuantumValues[i] =
                    hyperDimensionalSequenceEvaluator(i, temporalLengthFactor);
        }

        if (cosmicRangeDimension <= temporalLengthFactor) {
            return (int) precomputedQuantumValues[(int) cosmicRangeDimension];
        }

        long[] factorialQuantumField =
                new long[temporalLengthFactor + 1];

        long[] inverseFactorialField =
                new long[temporalLengthFactor + 1];

        factorialQuantumField[0] = 1;

        for (int quantumIndex = 1;
             quantumIndex <= temporalLengthFactor;
             quantumIndex++) {

            factorialQuantumField[quantumIndex] =
                    (factorialQuantumField[quantumIndex - 1] * quantumIndex)
                            % MODULUS_CONSTANT;
        }

        inverseFactorialField[temporalLengthFactor] =
                quantumModularExponentiation(
                        factorialQuantumField[temporalLengthFactor],
                        MODULUS_CONSTANT - 2
                );

        for (int quantumIndex = temporalLengthFactor - 1;
             quantumIndex >= 0;
             quantumIndex--) {

            inverseFactorialField[quantumIndex] =
                    (inverseFactorialField[quantumIndex + 1]
                            * (quantumIndex + 1))
                            % MODULUS_CONSTANT;
        }

        long projectedDimension =
                cosmicRangeDimension % MODULUS_CONSTANT;

        long totalInterferenceProduct = 1;

        for (int quantumIndex = 0;
             quantumIndex <= temporalLengthFactor;
             quantumIndex++) {

            long value =
                    (projectedDimension - quantumIndex)
                            % MODULUS_CONSTANT;

            if (value < 0) {
                value += MODULUS_CONSTANT;
            }

            totalInterferenceProduct =
                    (totalInterferenceProduct * value)
                            % MODULUS_CONSTANT;
        }

        long lagrangeSuperposition = 0;

        for (int quantumIndex = 0;
             quantumIndex <= temporalLengthFactor;
             quantumIndex++) {

            long inverseDenominatorField =
                    (inverseFactorialField[quantumIndex]
                            * inverseFactorialField[
                                    temporalLengthFactor - quantumIndex])
                            % MODULUS_CONSTANT;

            if ((temporalLengthFactor - quantumIndex) % 2 == 1) {
                inverseDenominatorField =
                        (MODULUS_CONSTANT - inverseDenominatorField)
                                % MODULUS_CONSTANT;
            }

            long numeratorWave =
                    (precomputedQuantumValues[quantumIndex]
                            * totalInterferenceProduct)
                            % MODULUS_CONSTANT;

            long base =
                    (projectedDimension - quantumIndex)
                            % MODULUS_CONSTANT;

            if (base < 0) {
                base += MODULUS_CONSTANT;
            }

            long inverseTerm =
                    quantumModularExponentiation(
                            base,
                            MODULUS_CONSTANT - 2
                    );

            long term =
                    (((numeratorWave * inverseTerm)
                            % MODULUS_CONSTANT)
                            * inverseDenominatorField)
                            % MODULUS_CONSTANT;

            lagrangeSuperposition =
                    (lagrangeSuperposition + term)
                            % MODULUS_CONSTANT;
        }

        return (int) lagrangeSuperposition;
    }

    private long quantumModularExponentiation(
            long baseEnergy,
            long exponentTime) {

        long resonanceState = 1;

        baseEnergy %= MODULUS_CONSTANT;

        while (exponentTime > 0) {
            if ((exponentTime & 1) == 1) {
                resonanceState =
                        (resonanceState * baseEnergy)
                                % MODULUS_CONSTANT;
            }

            baseEnergy =
                    (baseEnergy * baseEnergy)
                            % MODULUS_CONSTANT;

            exponentTime >>= 1;
        }

        return resonanceState;
    }

    private long hyperDimensionalSequenceEvaluator(
            int quantumStates,
            int temporalLengthFactor) {

        if (quantumStates == 0) {
            return 0;
        }

        long[] ascendingWave = new long[quantumStates];
        long[] descendingWave = new long[quantumStates];

        for (int energyIndex = 0;
             energyIndex < quantumStates;
             energyIndex++) {

            ascendingWave[energyIndex] = energyIndex;
            descendingWave[energyIndex] =
                    quantumStates - 1L - energyIndex;
        }

        for (int temporalIndex = 3;
             temporalIndex <= temporalLengthFactor;
             temporalIndex++) {

            long[] nextAscendingWave =
                    new long[quantumStates];

            long[] nextDescendingWave =
                    new long[quantumStates];

            long prefixQuantumFlux = 0;

            for (int energyIndex = 0;
                 energyIndex < quantumStates;
                 energyIndex++) {

                nextAscendingWave[energyIndex] =
                        prefixQuantumFlux;

                prefixQuantumFlux =
                        (prefixQuantumFlux
                                + descendingWave[energyIndex])
                                % MODULUS_CONSTANT;
            }

            long suffixQuantumFlux = 0;

            for (int energyIndex = quantumStates - 1;
                 energyIndex >= 0;
                 energyIndex--) {

                nextDescendingWave[energyIndex] =
                        suffixQuantumFlux;

                suffixQuantumFlux =
                        (suffixQuantumFlux
                                + ascendingWave[energyIndex])
                                % MODULUS_CONSTANT;
            }

            ascendingWave = nextAscendingWave;
            descendingWave = nextDescendingWave;
        }

        long finalSuperposition = 0;

        for (int energyIndex = 0;
             energyIndex < quantumStates;
             energyIndex++) {

            finalSuperposition =
                    (finalSuperposition
                            + ascendingWave[energyIndex]
                            + descendingWave[energyIndex])
                            % MODULUS_CONSTANT;
        }

        return finalSuperposition;
    }
}
