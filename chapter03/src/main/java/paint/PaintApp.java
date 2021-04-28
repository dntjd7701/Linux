package paint;

public class PaintApp {

	public static void main(String[] args) {
		Point point1 = new Point();
		point1.setX(10);
		point1.setY(20);
		point1.show();
		point1.show(false);
		
		Point point2 = new Point(100, 200);
		point2.show();

		Point point3 = new ColorPoint();
		point3.setX(50);
		point3.setY(100);
		((ColorPoint)point3).setColor("red");
		// point3.show();
		point3.show(true);
	}
}
