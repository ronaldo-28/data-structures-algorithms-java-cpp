// class Solution {
//     public int minWastedSpace(int[] packages, int[][] boxes) {
        
//     }
// }
import java.util.Arrays;

class Solution {
    public int minWastedSpace(int[] packages, int[][] boxes) {
        int totalPackages = packages.length;
        int maxPackageSize = 0;
        for (int packageSize : packages) {
            if (packageSize > maxPackageSize) {
                maxPackageSize = packageSize;
            }
        }

        int[] packageCounts = new int[maxPackageSize + 1];
        long sumOfPackageSizes = 0;
        for (int packageSize : packages) {
            packageCounts[packageSize]++;
            sumOfPackageSizes += packageSize;
        }

        int[] prefixPackageCounts = new int[maxPackageSize + 1];
        for (int i = 1; i <= maxPackageSize; i++) {
            prefixPackageCounts[i] = prefixPackageCounts[i - 1] + packageCounts[i];
        }

        long minTotalBoxArea = Long.MAX_VALUE;

        for (int[] supplierBoxes : boxes) {
            Arrays.sort(supplierBoxes);
            
            if (supplierBoxes[supplierBoxes.length - 1] < maxPackageSize) {
                continue;
            }

            long currentSupplierBoxArea = 0;
            int lastPackedIndex = 0;

            for (int boxSize : supplierBoxes) {
                int currentPackedIndex = (boxSize >= maxPackageSize) ? totalPackages : prefixPackageCounts[boxSize];
                
                int packagesInCurrentBox = currentPackedIndex - lastPackedIndex;
                if (packagesInCurrentBox > 0) {
                    currentSupplierBoxArea += (long) packagesInCurrentBox * boxSize;
                }
                
                lastPackedIndex = currentPackedIndex;
                if (lastPackedIndex == totalPackages) {
                    break;
                }
            }
            
            if (currentSupplierBoxArea < minTotalBoxArea) {
                minTotalBoxArea = currentSupplierBoxArea;
            }
        }

        if (minTotalBoxArea == Long.MAX_VALUE) {
            return -1;
        }
        
        return (int) ((minTotalBoxArea - sumOfPackageSizes) % 1000000007);
    }
}