package tv;

public class TV {
	private int channel;    // 1~255 
	private int volume;     // 0~100
	private boolean power;
	
	public void power(boolean on) {
	}
	
	public void channel(boolean up) {
	}

	public void channel(int channel) {
	}
	
	public void volume(boolean up) {
	}

	public void volume(int channel) {
	}

	public void status() {
		System.out.println("TV[power=on, channel=11, volume=30]");
	}
	
}
