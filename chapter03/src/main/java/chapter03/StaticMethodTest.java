package chapter03;

public class StaticMethodTest {

	public static void main(String[] args) {
		String s = "123";
		int i = Integer.parseInt(s);
		int j = Math.abs(-1);
		int k = Math.max(10, 20);
		
		System.out.println(i + ":" + j + ":" + k);
	}

}
