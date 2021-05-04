package thread;

public class ThreadEx03 {

	public static void main(String[] args) {
		Thread thread1 = new DigitThread();
		Thread thread2 = new AlphabetThread();
		Thread thread3 = new Thread(new UppercaseAlphabetRunnableImpl());
		
		thread2.start();
		thread1.start();
		thread3.start();
	}

}
