package problem;

import java.util.Arrays;

public class Sum3Closest_16 {
    public int threeSumClosest(int[] nums, int target) {

        // 排序数组
        Arrays.sort(nums);

//        int absolute = 0x7fffffff;
        int absolute = Integer.MAX_VALUE
        int result = 0;
        for (int i = 0; i < nums.length - 2; i++) {
            int left = i+1;
            int right = nums.length - 1;
            while (right > left) {
                int sum = nums[i] + nums[left] + nums[right];
                int abs = Math.abs(target - sum);
                if (abs == 0)
                    return sum;

                if (abs < absolute) {
                    result = sum;
                    absolute = abs;
                }


                if (sum > target)
                    right --;

                else
                    left ++;
            }
        }
        return result ;
    }
}
