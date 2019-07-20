package problem;

public class MedianOfTwoSortArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] a;// short
        int[] b;// longer
        if (nums1.length > nums2.length) {
            a = nums2;
            b = nums1;
        } else {
            a = nums1;
            b = nums2;
        }


        int aLen = a.length;
        int bLen = b.length;

        if (aLen == 0) {
            if (bLen % 2 == 0) {
                return (b[(bLen-1)/2] + b[bLen / 2]) / 2.0;
            }
            else return (double)b[bLen/2];
        }
        int i=0;
        int j=0;
        for (i = 0; i <aLen; i++) {
            j = (aLen + bLen + 1) / 2 - i;
            if (i == 0) {
                if (b[j-1] <= a[i])
                    break;
            }

            // 如果 当i = aLen -1 时，不符合，那么只有i = aLen时符合了，无需要比较

            // 如果i != 0，则比较a[i-1] <= b[j] && b[j-1] <= a[i]
            else if(a[i-1] <= b[j] && b[j-1] <= a[i]) {
                break;
            }
        }
        j = (aLen + bLen + 1) / 2 - i;

        if((aLen + bLen) % 2 == 1) {

            // 1、 i == 0
            if (i ==0)
                return b[j-1];

            // 2、 i == aLen;
            // 3、 i != aLen
            return max(a[i-1], b[j-1]);

        }

        else {
            if (i == 0 && j == bLen)
                return (a[i] + b[bLen - 1]) / 2.0;
            if (i == 0 && j != bLen) {
                return (min(a[i],b[j]) + b[j-1]) / 2.0;
            }
            if ( i == aLen && j == 0)
                return (a[i-1] + b[j]) / 2.0;
            else if (i == aLen) {
                return (max(a[i-1],b[j-1]) + b[j]) / 2.0;
            }
            else{ return (max(a[i-1], b[j-1]) + min(a[i], b[j])) / 2.0;}
        }

    }
    private int max(int a, int b) {
        return a > b ? a:b;
    }
    private int min(int a, int b) {
        return a < b ? a:b;
    }



    public static void main(String[] args) {
        int[] a1 = {-1};
        int[] a2 = {2,3,4};
        System.out.println( new MedianOfTwoSortArrays().findMedianSortedArrays(a1, a2));
    }
}
