package com.gc.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gaochao
 * @create 2020-08-21 10:24
 */
public class Main {

  private static List<List<Integer>> lists =new ArrayList<>();;

  public static void main(String[] args) {
    int[] nums = new int[]{1,2,3};
    permute(nums);
    //打印
    lists.stream().forEach(list -> {
      System.out.print(list.toString()+ " ");
    });
  }


  public static void permute(int[] nums) {
    process(nums,0);
  }

  //变量start表示到达了某一层。
  private static void process(int[] nums, int start) {
    //如果起始位置已经到达了末尾，那么这就是一组解。
    if(start == nums.length){
      List<Integer> list = new ArrayList<>();
      for(int num:nums){
        list.add(num);
      }
      lists.add(list);
    }
    for (int i = start; i < nums.length ; i++) {
      swap(nums,i,start);
      process(nums,start+1);
      swap(nums,i,start);
    }
  }

  //把第一个元素分别与后面的元素进行交换，递归的调用其子数组进行排序
  private static void swap(int[] nums, int index1, int index2){
    int tmp = nums[index1];
    nums[index1] = nums[index2];
    nums[index2] = tmp;
  }

}
