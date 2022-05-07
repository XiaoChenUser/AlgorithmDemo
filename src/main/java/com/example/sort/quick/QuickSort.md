快速排序（QuickSort）：
理论：  
任取一个数组中的数 pivot（轴）（比如：index=0,length-1,middle,...），从左向右遍历数组，比它大的放在它的右边，比它小的放在它的左边，一次遍历结束，数组就分成了两部分：pivot 左边的都小于它，pivot 右边的都大于等于它。  
  
实现方式：  
取第 0 个数作为 pivot，从第 1 个数开始从左向右遍历，找到第 1 个大于 pivot 的数（index=low）；从第 n-1 个数开始从右向左遍历，找到第一个小于 pivot 的数（index=high），swap(low, high)。重复这一过程，直到 low<high == false。  
若 nums[low]<nums[pivot],  
=> swap(low, pivot);  pivot = low;
else  
=> swap(low-1, pivot); pivot = low - 1;
此时，第一次遍历完成。  
  
时间复杂度：  
平均：O(NlogN), 最坏：O(N^2)
空间复杂度：O(logN)，最坏：O(N)  
稳定性：不稳定  
最差情况出现在数组元素原本有序。  