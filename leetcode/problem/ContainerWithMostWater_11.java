package problem;

public class ContainerWithMostWater_11 {
    public int maxArea(int[] height) {
       // 假设最左边和最右边的形成最大的水桶
        int left = 0;
        int right = height.length - 1;
        int max = 0;

        while (left < right) {
            max = getMax(max, (right - left) * getMin(height[left], height[right]));
            // 如果left边小于右边,则说明此（left，right）对为以left为边界中最大的一个，left = left + 1
            if (height[left] < height[right]) {
                left ++;
            }

            // 如果left大于等于right,则说明（left，right）对为以right为边界中最大的一个，right = right - 1
            else right--;
        }
        return max;
    }

    public int getMin(int x, int y) {
        return x > y ? y : x;
    }

    public int getMax(int x, int y) {
        return x < y ? y : x;
    }

    public static void main(String[] args) {
        int[] height = {1, 2, 4, 5, 6, 21, 14, 87};
        System.out.println(new ContainerWithMostWater_11().maxArea(height));
    }

}
