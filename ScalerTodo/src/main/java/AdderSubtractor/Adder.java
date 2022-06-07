package AdderSubtractor;

public class Adder implements Runnable{
	
	Counter counter;
	
	public Adder(Counter counter) {
		this.counter=counter;
	}
	
	@Override
	public void run() {
		
		for(int i=0;i<1000;i++) {
			this.counter.value+=1;
		}
	}

}
