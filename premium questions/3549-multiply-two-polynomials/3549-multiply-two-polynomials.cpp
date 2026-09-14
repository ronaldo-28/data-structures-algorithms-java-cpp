class Solution {
    // Time: O((n + m) * log(n + m))
    // Space: O(n + m)

    const double PI = acos(-1);
    vector<int> rev;
    int bit, tot;

    struct Complex {
        double x, y;

        Complex(double x = 0, double y = 0) : x(x), y(y) {}

        Complex plus(const Complex& t) const {
            return Complex(x + t.x, y + t.y);
        }

        Complex minus(const Complex& t) const {
            return Complex(x - t.x, y - t.y);
        }

        Complex multiply(const Complex& t) const {
            return Complex(
                x * t.x - y * t.y,
                x * t.y + y * t.x
            );
        }
    };

public:
    vector<long long> multiply(vector<int>& poly1, vector<int>& poly2) {
        int n = poly1.size();
        int m = poly2.size();

        // Find bit such that 2^bit >= n + m
        bit = 0;
        while ((1 << bit) < n + m) bit++;

        tot = 1 << bit;

        // Allocate arrays
        vector<Complex> a(tot);
        vector<Complex> b(tot);

        rev.resize(tot);

        // Copy input coefficients
        for (int i = 0; i < n; i++) {
            a[i].x = poly1[i];
        }

        for (int i = 0; i < m; i++) {
            b[i].x = poly2[i];
        }

        // Build bit-reversal permutation
        for (int i = 0; i < tot; i++) {
            rev[i] = (rev[i >> 1] >> 1) |
                     ((i & 1) << (bit - 1));
        }

        // Forward FFT
        fft(a, 1);
        fft(b, 1);

        // Point-wise multiplication
        for (int i = 0; i < tot; i++) {
            a[i] = a[i].multiply(b[i]);
        }

        // Inverse FFT
        fft(a, -1);

        // Collect result and round
        vector<long long> res(n + m - 1);

        for (int i = 0; i < (int)res.size(); i++) {
            res[i] = llround(a[i].x / tot);
        }

        return res;
    }

private:
    void fft(vector<Complex>& a, int inv) {

        // Bit-reversal reorder
        for (int i = 0; i < tot; i++) {
            if (i < rev[i]) {
                swap(a[i], a[rev[i]]);
            }
        }

        // Cooley-Tukey FFT
        for (int len = 1; len < tot; len <<= 1) {

            double ang = PI / len * inv;

            Complex wlen(
                cos(ang),
                sin(ang)
            );

            for (int i = 0; i < tot; i += len << 1) {

                Complex w(1, 0);

                for (int j = 0; j < len; j++) {

                    Complex u = a[i + j];

                    Complex v = a[i + j + len].multiply(w);

                    a[i + j] = u.plus(v);

                    a[i + j + len] = u.minus(v);

                    w = w.multiply(wlen);
                }
            }
        }
    }
};