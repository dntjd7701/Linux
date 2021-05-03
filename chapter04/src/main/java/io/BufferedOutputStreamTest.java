package io;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class BufferedOutputStreamTest {

	public static void main(String[] args) {
		BufferedOutputStream bos = null;
		
		try {
			FileOutputStream fis = new FileOutputStream("test.txt");
			bos = new BufferedOutputStream(fis);
			
			// for(int i = 'a'; i < 'z'; i++) {
			for(int i = 97; i <= 122; i++) {
				bos.write(i);
			}
		} catch (FileNotFoundException e) {
			System.out.println("can't open:" + e);
		} catch (IOException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if(bos != null) {
					bos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
