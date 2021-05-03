package io;

import java.io.File;

public class PhoneList01 {

	public static void main(String[] args) {
		File file = new File("phone.txt");
		
		System.out.println(file.length() + "bytes");
	}

}
