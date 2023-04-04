package com.example.revision.arrays;

import org.hibernate.query.criteria.internal.expression.function.AggregationFunction;
import org.springframework.util.LinkedCaseInsensitiveMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ArrayTest {

    /**
     * Find the max value for f(i,j) = |A[i]-A[j]| +|i-j|
     * @param nums
     * @return
     */

    public static int maxDiff(int [] nums){
        return 0;
    }

    /**
     * To find first missing positive number
     * @param nums
     * @return
     */
    public static int firstMissingNumber(int[] nums) {
        // 1. To create a visited array  TC -> O(n)     SC-> O(n)
        // 2. To sort and then find      TC -> 0(nlogn) SC -> O(1)

        // A = [100,2,3,1,-1]
        // TC -> 0(n) SC -> O(n)
        // place numbers at the exact position
        for (int i = 0; i < nums.length; i++) {

            while (nums[i] > 0 && nums[i] < nums.length && nums[i]!=nums[nums[i]-1]) {

                int toBeSwap = nums[i];
                nums[i] = nums[nums[i] - 1];
                nums[toBeSwap - 1] = toBeSwap;
            }

        }

        int firstMissNumber = 1;

        // iterate to find the first missing positive number
        for (int i = 0; i < nums.length; i++) {

            if (firstMissNumber != nums[i]) {
                return firstMissNumber;
            }
            firstMissNumber++;
        }
        // returning if every number is present
        return nums.length+1;
    }

    public static class Interval {
        int start;
        int end;

        public Interval(){

        }
    }

    /**
     * To insert a  interval and merge if necessary
     *
     * @param intervals
     * @param newInterval
     * @return
     */
    public ArrayList<Interval> insert(ArrayList<Interval> intervals, Interval newInterval) {


        ArrayList<Interval> ret = new ArrayList<>();

        int index = 0;

        // inserting all intervals having start time less than start time of interval to be merged
        while (index < intervals.size()) {

            Interval currentInterval = intervals.get(index);

            if (currentInterval.start < newInterval.start) {
                ret.add(currentInterval);
                index++;
            } else {
                break;
            }
        }

        // inserting the new interval
        if (ret.size() == 0 || ret.get(ret.size() - 1).end < newInterval.start) {
            ret.add(newInterval);
        } else {
            Interval lastInterval = ret.get(ret.size() - 1);
            lastInterval.end = Math.max(lastInterval.end, newInterval.end);
        }
        // 1-3 4-6 5-9
        // merging
        while (index < intervals.size()) {

            Interval lastInterval = ret.get(ret.size() - 1);
            Interval currInterval = intervals.get(index);

            if (lastInterval.end >= currInterval.start) {
                lastInterval.end = Math.max(lastInterval.end, currInterval.end);
            } else {
                ret.add(currInterval);
            }
            index++;
        }

        return ret;
    }

    /**
     * To find maxWaterBetweenContainers
     * @param heights
     * @return
     */
    public static int maxWaterBetweenContainers(int[] heights) {

        int maxWaterTrapped = 0;

        // brute force -> try every combo standing at each container
        // TC : O(n^2) SC : O(1)
        for (int i = 0; i < heights.length; i++) {
            for (int j = i + 1; j < heights.length; j++) {
                int waterTrapped = Math.min(heights[i], heights[j]) * (j - i);
                maxWaterTrapped = Math.max(maxWaterTrapped, waterTrapped);
            }
        }


        // optimal solution -> use two pointer solution
        // choice -> try moving towards max min height so that multiplication gives more value
        // TC : O(n) SC : O(1)

        int low = 0;
        int high = heights.length - 1;

        while (low < high) {

            int waterTrapped = Math.min(heights[low], heights[high]) * (high - low);
            maxWaterTrapped = Math.max(maxWaterTrapped, waterTrapped);
            // if height on right side is greater,then shift to left pointer to right to find larger left and maximise product
            // if we move right pointer to left then either we will have small right height or big right height thus no improvement
            if (heights[low] < heights[high]) {
                low++;
            } else {
                high--;
            }

        }

        return maxWaterTrapped;
    }

    /**
     * To print spiral matrix
     * @param nums
     */
    public static void spiralMatrix(int [][] nums){
        int rows = nums.length-1;
        int cols = nums[0].length-1;
        int top = 0;
        int right = cols;
        int left = 0;
        int bottom = rows;

        int []ret = new int[(rows+1)*(cols+1)];
        int index =0;

        while(left<=right && top<=bottom){
            // left to right
            for(int i=left;i<=right;i++){
                ret[index++] = nums[top][i];
            }

            top++;
            // top to bottom
            for(int i=top;i<=bottom;i++){
                ret[index++] = nums[i][right];
            }

            right--;

            // right to left
            for(int i=right;i>=left && top<=bottom;i--){
                ret[index++] = nums[bottom][i];
            }

            bottom--;

            // bottom to top
            for(int i=bottom;i>=top && left<=right;i--){
                ret[index++] = nums[i][left];
            }

            left++;
        }

        System.out.println(Arrays.toString(ret));
    }

    /**
     * To find subMatrix for Q queries
     * @param nums
     * @param B
     * @param C
     * @param D
     * @param E
     * @return
     */
    public static int[] subMatrixSum(int [][] nums,int [] B,int [] C ,int [] D,int []E) {

        int [] subMatrixSum = new int[B.length];

        // Brute force -> to do N^2 traversal for Q queries -> O(Q*(N^2))

        // Optimal approach -> to construct prefix sum for matrix then calculating queries in O(1)
        // TC   ->  O(N^2 + Q*1) -> O(N^2)
        // construct row sum
        for (int row = 0; row < nums.length; row++) {

            for (int col = 1; col < nums[row].length; col++) {
                nums[row][col] += nums[row][col - 1];
            }
        }

        // construct column sum
        for (int col = 0; col < nums[0].length; col++) {

            for (int row = 1; row < nums.length; row++) {
                nums[row][col] += nums[row - 1][col];
            }
        }

        // rather than doing two iterations for calculating row sum and column
        // we can take a new array and construct that but increase space complexity to O(N^2)

        int [][] dp = new int[nums.length+1][nums[0].length+1];

        for(int i =1;i< dp.length;i++){
            for(int j=1;j<dp[i].length;j++){
                dp[i][j] = dp[i-1][j] + dp[i][j-1] + nums[i-1][j-1] - dp[i-1][j-1];
            }
        }

        System.out.println(Arrays.deepToString(nums));
        System.out.println(Arrays.deepToString(dp));

        for (int q = 0; q < B.length; q++) {

            int topRow = B[q] - 1;
            int topCol = C[q] - 1;
            int bottomRow = D[q] - 1;
            int bottomCol = E[q] - 1;

            int currSum = nums[bottomRow][bottomCol];
            // removing row above topRow
            if (topRow > 0) {
                currSum -= nums[topRow - 1][bottomCol];
            }
            // removing col before topCol
            if (topCol > 0) {
                currSum -= nums[bottomRow][topCol - 1];
            }
            // removing twice added part
            if (topCol > 0 && topRow > 0) {
                currSum += nums[topRow - 1][topCol - 1];
            }

            subMatrixSum[q] = currSum;
        }

        return subMatrixSum;
    }


    public static void main(String[] args) {

        int [][] nums = {{1,2,3},
                         {4,5,6},
                         {7,8,9}};

        System.out.println(Arrays.toString(subMatrixSum(nums,new int[]{1,2},new int[]{1,2},new int[]{2,3},new int[]{2,3})));
    }
}
