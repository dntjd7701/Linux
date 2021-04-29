package chapter03;

public class StudentTest {

	public static void main(String[] args) {
		Student s1 = new Student();
		s1.setName("둘리");
		s1.setGrade(4);
		s1.setMajor("CS");
		
		Person p1 = s1;				// upcasting(암시적)
		Student s2 = (Student)p1;   // downcasting(명시적)
	}

}
