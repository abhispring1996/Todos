package com.example.revision.search;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BinaryTest {

    /**
     * To find max water trapped
     * @param heights
     * @return
     */
    public static int maxWaterTrapped(int [] heights){

        // will construct left and right max height standing at a building and will subtract current building height

        // net water trapped = Math.min(left,right) - currheight;

        int [] leftMax = new int[heights.length];
        int [] rightMax = new int[heights.length];

        // A = [1,8,6,2,5,4,8,3,7]
        leftMax[0] = heights[0];

        for(int i=1;i<heights.length;i++){
            leftMax[i] = Math.max(leftMax[i-1],heights[i]);
        }


        rightMax[heights.length-1] = heights[heights.length-1];

        for(int i=heights.length-2;i>=0;i--){
            rightMax[i] = Math.max(rightMax[i+1],heights[i]);
        }

        //            [0,1,0,2,1,0,1,3,2,1,2,1]
        // left max = [0,1,1,2,2,2,2,3,3,3,3,3]
        // right max =[3,3,3,3,3,3,3,3,2,2,2,1]
        // water trap= 0 0 1 0 1 2 1 0 0 1 0 0
        int totalWaterTrapped = 0;

        for(int i=0;i<heights.length;i++){
            totalWaterTrapped += Math.min(leftMax[i],rightMax[i]) - heights[i];
        }
        return totalWaterTrapped;
    }

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


    public static void main(String[] args) {
        threeSum(new int[]{0,0,0});
    }
}
