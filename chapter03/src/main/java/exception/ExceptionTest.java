package exception;

/**
 * 
 * @author bit-user
 *
 * try~catch~finally 구문 만들기 연습
 */
public class ExceptionTest {

	public static void main(String[] args) {
		int a = 10;
		int b = 10 -a;
		
		System.out.println("some codes...1");
		
		try {
			System.out.println("some codes...2");
			int result = (1 + 2 + 3) / b;
			System.out.println("some codes...3");
		} catch(ArithmeticException e) {
			/* 예외 처리 */
			// 1. 사과
			System.out.println("미안합니다.....");
			// 2. 로깅
			System.out.println("error:" + e);
			// 3. 정상 종료
			return;
		} finally {
			/* 자원정리 */
			System.out.println("some codes...final(자원정리)");
		}
	}

}
