package prob5;

public class MyStack {
	private String[] data;
	private int top = -1;
	
	public MyStack(int size) {
		data = new String[size];
	}
	
	public void push(String data) {
		if(data.length == top) {
			resize();
		}
		top++;
	}
	
	public String pop() throws MyStackException {
	}
	}
}