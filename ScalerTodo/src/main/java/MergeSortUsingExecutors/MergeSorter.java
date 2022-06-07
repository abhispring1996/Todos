package MergeSortUsingExecutors;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MergeSorter implements Callable<List<Integer>> {
	List<Integer> list;

	MergeSorter(List<Integer> list) {
		this.list = list;
	}

	@Override
	public List<Integer> call() throws Exception {

		if (this.list.size() == 0) {
			return new ArrayList<Integer>();
		}

		if (this.list.size() == 1) {
			return this.list;
		}

		int size = this.list.size();
		int mid = size / 2;

		List<Integer> leftSide = new ArrayList<Integer>();
		List<Integer> rightSide = new ArrayList<Integer>();
		// 1 3 4 5 6 7
		for (int i = 0; i < mid; i++) {
			leftSide.add(this.list.get(i));
		}

		for (int i = mid; i < size; i++) {
			rightSide.add(this.list.get(i));
		}

		MergeSorter leftSorter = new MergeSorter(leftSide);
		MergeSorter rightSorter = new MergeSorter(rightSide);

		ExecutorService executor = Executors.newCachedThreadPool();

		Future<List<Integer>> leftSorted = executor.submit(leftSorter);
		Future<List<Integer>> rightSorted = executor.submit(rightSorter);

		List<Integer> leftSortedArray = leftSorted.get();
		List<Integer> rightSortedArray = rightSorted.get();

		int i = 0;
		int j = 0;

		List<Integer> mergedArray = new ArrayList<Integer>();

		while (i < leftSortedArray.size() && j < rightSortedArray.size()) {

			if (leftSortedArray.get(i) < rightSortedArray.get(j)) {
				mergedArray.add(leftSortedArray.get(i));
				i++;
			} else {
				mergedArray.add(rightSortedArray.get(j));
				j++;
			}

		}

		while (i < leftSortedArray.size()) {
			mergedArray.add(leftSortedArray.get(i));
			i++;
		}

		while (j < rightSortedArray.size()) {
			mergedArray.add(rightSortedArray.get(j));
			j++;
		}

		return mergedArray;
	}

}
