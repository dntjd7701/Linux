package thread;

public class UppercaseAlphabetRunnableImpl extends UppercaseAlphabetThread implements Runnable {
	@Override
	public void run() {
		print();
	}
}