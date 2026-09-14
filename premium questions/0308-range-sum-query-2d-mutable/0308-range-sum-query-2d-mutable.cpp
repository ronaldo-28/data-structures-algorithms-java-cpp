#define rep(i, a, b) for(int i=(a); i<(b); ++i)
#define sz(x) (int)(x).size()
typedef vector<int> vi;
typedef vector<vector<int>> vii;

class Bit {
public:
    int R;
    int C;
    vii vii_;
    Bit(int m, int n): R(m), C(n), vii_(m, vi(n)) {}
    void update(int row, int col, int dif) {
        int r = row;
        while (r < R) {
            int c = col;
            while (c < C) {
                vii_[r][c] += dif;
                c |= c+1;
            }
            r |= r+1;
        }
    }
    int query(int row, int col) {
        int sm = 0;
        int r = row;
        while (r) {
            int c = col;
            while (c) {
                sm += vii_[r-1][c-1];
                c &= c-1;
            }
            r &= r-1;
        }
        return sm;
    }
};

class NumMatrix {
public:
    Bit b;
    vii& mat;
    NumMatrix(vector<vector<int>>& mat): b(sz(mat), sz(mat[0])), mat(mat) {
        rep(r, 0, sz(mat)) {
            rep(c, 0, sz(mat[0])) {
                b.update(r, c, mat[r][c]);
            }
        }
    }
    
    void update(int row, int col, int val) {
        int dif = val - mat[row][col];
        mat[row][col] = val;
        b.update(row, col, dif);
    }
    
    int sumRegion(int row1, int col1, int row2, int col2) {
        // cout << b.query(0, 0) << endl;
        // cout << b.query(1, 1) << endl;
        // cout << b.query(1, 2) << endl;
        // cout << b.query(2, 1) << endl;
        // cout << b.query(2, 2) << endl;
        // return 0;
        int sm1 = b.query(row1, col1);
        int sm2 = b.query(row1, col2+1);
        int sm3 = b.query(row2+1, col1);
        int sm4 = b.query(row2+1, col2+1);
        return (sm1 + sm4) - (sm2 + sm3);
    }
};

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix* obj = new NumMatrix(matrix);
 * obj->update(row,col,val);
 * int param_2 = obj->sumRegion(row1,col1,row2,col2);
 */