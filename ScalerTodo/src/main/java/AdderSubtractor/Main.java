package AdderSubtractor;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

	// TODO -> Use synchronized for methods for limited access to object method and semaphore 
	public static void main(String[] args) {
		Counter c = new Counter();
		ExecutorService executor = Executors.newFixedThreadPool(2);
		Adder adder = new Adder(c);
		Subtractor sub = new Subtractor(c);
		try {
			executor.submit(adder).get();
			executor.submit(sub).get();
			executor.shutdown();
		} catch (InterruptedException | ExecutionException e) {
		}

		System.out.println("Main Result" + c.value);
	}
	
}
