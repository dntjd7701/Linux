package thread;

public class ThreadEx02 {

	public static void main(String[] args) {
		Thread thread1 = new DigitThread();
		Thread thread2 = new AlphabetThread();
		
		thread2.start();
		thread1.start();
	}

}
