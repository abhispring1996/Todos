package MergeSortUsingExecutors;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {

	public static void main(String[] args) {
		List<Integer> list = List.of(1, 2, 3, 5, 7, 89, 78, 3, 0, 198);

		MergeSorter sorter = new MergeSorter(list);
		ExecutorService executor = Executors.newCachedThreadPool();

		Future<List<Integer>> res = executor.submit(sorter);
		try {
			List<Integer> sortedList = res.get();
			System.out.println(sortedList);
		} catch (InterruptedException | ExecutionException e) {
			System.out.println("Something went wrong"+e);
		}
	}

}
