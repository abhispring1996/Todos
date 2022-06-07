package AdderSubtractor;

public class Subtractor implements Runnable {

	Counter counter;

	public Subtractor(Counter counter) {
		super();
		this.counter = counter;
	}

	@Override
	public void run() {
		for (int i = 0; i < 1000; i++) {
			this.counter.value -= 1;
		}
	}
}
