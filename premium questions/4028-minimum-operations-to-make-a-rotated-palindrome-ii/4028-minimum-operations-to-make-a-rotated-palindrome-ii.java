import java.util.Arrays;

class Solution {
    private static final double PI = Math.PI;

    public int minOperations(String s) {
        int n = s.length();

        // Round up to smallest power of 2 >= 2 * n for linear convolution
        int size = 1;
        while (size < 2 * n) {
            size <<= 1;
        }

        // Convert string characters to numeric values 0..25
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = s.charAt(i) - 'a';
        }

        // =====================================================================
        // Step 1: Precompute the 26-point Discrete Fourier Transform of the
        //         cyclic distance function D(z) = min(z, 26 - z).
        //
        // Because D(z) is symmetric (even), its Fourier transform cost[t] is
        // strictly real-valued: cost[t] = sum_{z=0..25} D(z) * cos(2*pi*t*z / 26).
        // =====================================================================
        double[] cost = new double[26];
        for (int t = 0; t < 26; t++) {
            for (int z = 0; z < 26; z++) {
                int d = Math.min(z, 26 - z);
                cost[t] += d * Math.cos(-2.0 * PI * t * z / 26.0);
            }
        }

        // dp[c] accumulates the total palindrome mismatch cost for reflection center c
        double[] dp = new double[n];

        // Reusable working buffers for FFT passes (avoids GC allocations inside loops)
        double[] re = new double[size];
        double[] im = new double[size];
        double[] bre = new double[size];
        double[] bim = new double[size];

        // =====================================================================
        // Step 2: Sum over independent Fourier frequencies t = 0..13.
        //
        // By Hermitian symmetry:
        // - t = 0 and t = 13 are unique self-conjugate frequencies (weight 1.0).
        // - t = 1..12 appear twice via t and (26 - t) (weight 2.0).
        // =====================================================================
        for (int t = 0; t <= 13; t++) {
            double theta = 2.0 * PI * t / 26.0;

            // Map each character nums[i] to its complex phasor e^(i * theta * nums[i])
            for (int i = 0; i < n; i++) {
                double angle = theta * nums[i];
                re[i] = Math.cos(angle);
                im[i] = Math.sin(angle);
            }
            // Zero-pad up to power-of-two size
            Arrays.fill(re, n, size, 0.0);
            Arrays.fill(im, n, size, 0.0);

            // Forward FFT on sequence of complex phasors
            fft(re, im, false);

            // Pointwise multiplication in frequency domain:
            // Convolve sequence A with sequence B where B[i] = A[i] (self-convolution)
            for (int i = 0; i < size; i++) {
                double ar = re[i];
                double ai = im[i];

                // Time-domain symmetry trick in frequency domain
                int j = (size - i) & (size - 1);
                double br = re[j];
                double bi = -im[j];

                bre[i] = ar * br - ai * bi;
                bim[i] = ar * bi + ai * br;

                // Conjugate to prepare for inverse transform via forward FFT
                bim[i] = -bim[i];
            }

            // Inverse FFT (evaluated using forward pass on conjugated coordinates)
            fft(bre, bim, false);

            // Factor explanation:
            // - mult: 1.0 for t in {0, 13}, 2.0 for t in 1..12 (accounting for 26 - t)
            // - cost[t]: Fourier amplitude of cyclic distance
            // - / size: Normalization factor from unscaled forward FFT
            double mult = (t == 0 || t == 13) ? 1.0 : 2.0;
            double factor = mult * cost[t] / size;

            // Circular wrap-around: reflection sum c receives contributions from c and c + n
            for (int c = 0; c < n; c++) {
                dp[c] += factor * (bre[c] + bre[c + n]);
            }
        }

        // =====================================================================
        // Step 3: Find rotation r in [0, n - 1] minimizing:
        //         r (rotation cost) + palindromeCost (distance cost)
        // =====================================================================
        long minTotalOps = Long.MAX_VALUE;

        for (int r = 0; r < n; r++) {
            // Under left rotation by r, mirrored index condition i + j = 2r - 1 (mod n)
            int c = (2 * r - 1 + n) % n;

            // Division by 52.0:
            // - 26.0 comes from the inverse DFT scale factor across Z_26
            // - 2.0 accounts for unordered pairs {i, j} being counted twice (i, j) and (j, i)
            long palindromeCost = Math.round(dp[c] / 52.0);

            minTotalOps = Math.min(minTotalOps, r + palindromeCost);
        }

        return (int) minTotalOps;
    }

    /**
     * Standard radix-2 in-place Cooley-Tukey FFT.
     * Computes unscaled forward transform (isInverted = false)
     * or scaled inverse transform (isInverted = true).
     */
    private void fft(double[] real, double[] imag, boolean isInverted) {
        int size = real.length;

        // Bit-reversal permutation
        for (int i = 1, j = 0; i < size; i++) {
            int bit = size >> 1;
            while ((j & bit) > 0) {
                j ^= bit;
                bit >>= 1;
            }
            j ^= bit;

            if (i < j) {
                double tempR = real[i];
                real[i] = real[j];
                real[j] = tempR;

                double tempI = imag[i];
                imag[i] = imag[j];
                imag[j] = tempI;
            }
        }

        // Butterfly calculations
        for (int len = 2; len <= size; len <<= 1) {
            double angle = (isInverted ? 2.0 : -2.0) * PI / len;
            double wLenReal = Math.cos(angle);
            double wLenImag = Math.sin(angle);

            int halfLen = len >> 1;
            for (int i = 0; i < size; i += len) {
                double wReal = 1.0;
                double wImag = 0.0;

                for (int j = 0; j < halfLen; j++) {
                    int l = i + j;
                    int r = l + halfLen;

                    double uReal = real[l];
                    double uImag = imag[l];

                    double vReal = real[r] * wReal - imag[r] * wImag;
                    double vImag = real[r] * wImag + imag[r] * wReal;

                    real[l] = uReal + vReal;
                    imag[l] = uImag + vImag;
                    real[r] = uReal - vReal;
                    imag[r] = uImag - vImag;

                    // Rotate unity root: w = w * wLen
                    double nextReal = wReal * wLenReal - wImag * wLenImag;
                    double nextImag = wReal * wLenImag + wImag * wLenReal;
                    wReal = nextReal;
                    wImag = nextImag;
                }
            }
        }

        if (isInverted) {
            for (int i = 0; i < size; i++) {
                real[i] /= size;
                imag[i] /= size;
            }
        }
    }
}