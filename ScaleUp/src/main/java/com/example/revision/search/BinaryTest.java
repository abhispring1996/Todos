package com.example.revision.search;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BinaryTest {

    /**
     * To find triplets -> nums[i] + nums[j] + nums[k] == 0
     * @param nums
     */
    public static void threeSum(int [] nums){

        Arrays.sort(nums);
        Set<List<Integer>> ret = new HashSet<>();

        for(int i=0;i<nums.length;i++){
            int target = 0 - nums[i];
            // two sum using two pointers
            int low = i+1;
            int high = nums.length-1;

            while(low<high){

                int currSum = nums[low] + nums[high];

                if(currSum==target){
                    List<Integer> triplets = Arrays.asList(nums[i],nums[low],nums[high]);
                    ret.add(triplets);
                    break;
                }else if(currSum > target){
                    high--;
                }else{
                    low++;
                }

            }

        }

        // TC -> O(nlogn + n^2) -> O(N^2)
        // SC -> O(No of triplets*3)
        System.out.println(ret);
    }

    /**
     * To find a target in an rotated sorted array
     * @param nums
     * @param numberToFind
     * @return
     */
    public static int searchRotatedSortedArray(int [] nums,int numberToFind) {
        //4, 5, 6, 7, 0, 1, 2, 3

        int low = 0;
        int high = nums.length - 1;
        // TC -> O(logn)
        while (low<=high) {

            int mid = low + (high - low) / 2;
            if (nums[mid] == numberToFind) {
                return mid;
            }
            // there will be always atleast 2 increasing subarrays around the pivot element
            // right is an increasing subarray
            if (nums[mid] <= nums[low]) {
                if (numberToFind>=nums[mid] && numberToFind<=nums[high]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            } else {
                if (numberToFind>=nums[low] && numberToFind<=nums[mid]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }

        }

        return -1;
    }

    /**
     * Find the peak element
     * @param nums
     * @return
     */
    public static int findPeakElement(int [] nums) {
        //1,2,1,3,5,6,4

        int low = 0;
        int high = nums.length - 1;
        int mid = 0;
        // TC -> O(logN) and SC -> O(1)
        while (low <= high) {
            mid = low + (high - low) / 2;

            if ((mid == 0 || nums[mid] > nums[mid - 1]) && (mid == nums.length - 1 || nums[mid] > nums[mid + 1])) {
                return nums[mid];
            }

            if (mid > 0 && nums[mid] < nums[mid - 1]) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return nums[mid];
    }

    /**
     *  minimum possible value of the absolute difference between any two adjacent elements of the array by picking
     *  one element from each row
     * @param rows
     * @param cols
     * @param nums
     * @return
     */
    public static int getMinDiffArray(int rows,int cols,int [][]nums){

        // brute force
        // 1. iterate through the matrix and then for one selected element
        // 2. search for min diff in the next row
        // TC -> O(n^3) SC ->O(1)


        // Optimal Approach
        // 1. Sort the rows
        // 2. Iterate through the matrix and then for one selected element
        // 3. Then do binary search the next row and find upper and lower bound for the selected element to get min diff.
        // TC -> O(n^2logn) SC ->O(1)

        for(int i=0;i<rows;i++){
            Arrays.sort(nums[i]);
        }

        int ans = Integer.MAX_VALUE;

        for(int i=0;i<rows-1;i++){

            for(int j=0;j<cols;j++){

                int upperBoundIndex = findUpperBoundIndex(nums,nums[i][j],cols,i+1);
                // upper bound usage
                if(upperBoundIndex!=cols){
                    ans = Math.min(ans,Math.abs(nums[i][j]-nums[i+1][upperBoundIndex]));
                }
                // lower Bound usage
                if(upperBoundIndex!=0){
                    ans = Math.min(ans,Math.abs(nums[i][j]-nums[i+1][upperBoundIndex-1]));
                }
            }
        }

        return ans;
    }

    public static int findUpperBoundIndex(int [][]nums, int reference, int cols, int concernedRow){

        int low =0;
        int high = cols-1;

        int ans = cols;

        while(low<=high){

            int mid = low +(high-low)/2;

            if(nums[concernedRow][mid]<reference){
                low = mid+1;
            }else{
                ans = mid;
                high = mid-1;
            }
        }

        return ans;
    }

    /**
     * To find the square root of a number
     * @param number
     * @return
     */
    public static int findSquareRoot(int number){

        // for ex -> 9
        long low =0;
        long high =number;

        long ansSqRoot = -1;
        // TC -> O(log(number))
        // SC -> O(1)
        while(low<=high){

            long mid = low +(high-low)/2;

            long square = mid*mid;

            if(square == number){
                return (int)mid;
            }
            else if(square >number){
                high= mid-1;
            }else {
                ansSqRoot = mid;
                low = mid + 1;
            }
        }
        return (int)ansSqRoot;
    }


    public static void main(String[] args) {
        System.out.println(findSquareRoot(11));
    }
}
