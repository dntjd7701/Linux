package chapter03;

public class MyClassTest {

	public static void main(String[] args) {
		MyClass myClass01 = MyClass.getInstance();
		MyClass myClass02 = MyClass.getInstance();
		
		System.out.println(myClass01 == myClass02);
	}

}
