class Solution {

    private static final double EPS = 1e-7;

    public double[] outerTrees(int[][] trees) {
        int n = trees.length;
        int[][] p = Arrays.copyOf(trees, n);

        // Рандомизация -> ожидаемая линейная сложность алгоритма Вельцля
        Random rnd = new Random(20210601);
        for (int i = n - 1; i > 0; i--) {
            int j = rnd.nextInt(i + 1);
            int[] t = p[i]; p[i] = p[j]; p[j] = t;
        }

        return welzl(p, new ArrayList<>(), n);
    }

    /**
     * Итеративный (развёрнутый) Вельцль вместо рекурсии:
     * три вложенных цикла, каждый следующий запускается только тогда,
     * когда очередная точка выпала из текущего круга.
     */
    private double[] welzl(int[][] p, List<int[]> r, int n) {
        if (n == 0) return trivial(r);

        double[] disk = new double[] {p[0][0], p[0][1], 0};

        for (int i = 1; i < n; i++) {
            if (inside(disk, p[i])) continue;

            disk = new double[] {p[i][0], p[i][1], 0};
            for (int j = 0; j < i; j++) {
                if (inside(disk, p[j])) continue;

                disk = getDiskFromTwoPoints(p[i], p[j]);
                for (int k = 0; k < j; k++) {
                    if (inside(disk, p[k])) continue;

                    r.clear();
                    r.add(p[i]);
                    r.add(p[j]);
                    r.add(p[k]);
                    disk = trivial(r);
                }
            }
        }
        return disk;
    }

    private double[] trivial(List<int[]> r) {
        if (r.isEmpty()) return null;

        if (r.size() == 1) {
            return new double[] {r.get(0)[0], r.get(0)[1], 0};
        }

        if (r.size() == 2) {
            return getDiskFromTwoPoints(r.get(0), r.get(1));
        }

        double[] disk01 = getDiskFromTwoPoints(r.get(0), r.get(1));
        if (inside(disk01, r.get(2))) return disk01;
        double[] disk02 = getDiskFromTwoPoints(r.get(0), r.get(2));
        if (inside(disk02, r.get(1))) return disk02;
        double[] disk12 = getDiskFromTwoPoints(r.get(1), r.get(2));
        if (inside(disk12, r.get(0))) return disk12;

        return getDiskFromThreePointsOnTheBoundary(r.get(0), r.get(1), r.get(2));
    }

    private double[] getDiskFromTwoPoints(int[] p1, int[] p2) {
        double dx = p1[0] - p2[0], dy = p1[1] - p2[1];
        return new double[] {
            (p1[0] + p2[0]) / 2.0,
            (p1[1] + p2[1]) / 2.0,
            Math.sqrt(dx * dx + dy * dy) / 2.0
        };
    }

    private double[] getDiskFromThreePointsOnTheBoundary(int[] p1, int[] p2, int[] p3) {
        double[] center = getCenter(p2[0] - p1[0], p2[1] - p1[1], p3[0] - p1[0], p3[1] - p1[1]);
        double cx = center[0] + p1[0];
        double cy = center[1] + p1[1];
        return new double[] {cx, cy, Math.sqrt(center[0] * center[0] + center[1] * center[1])};
    }

    private double[] getCenter(double bx, double by, double cx, double cy) {
        double b = bx * bx + by * by;
        double c = cx * cx + cy * cy;
        double d = bx * cy - by * cx;
        return new double[] {(cy * b - by * c) / (2 * d), (bx * c - cx * b) / (2 * d)};
    }

    private boolean inside(double[] circle, int[] point) {
        if (circle == null) return false;
        double dx = circle[0] - point[0], dy = circle[1] - point[1];
        // допуск EPS страхует от накопления ошибок double на границе круга
        return dx * dx + dy * dy <= circle[2] * circle[2] + EPS;
    }
}