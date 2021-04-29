package paint;

public class PaintApp {

	public static void main(String[] args) {
		Point point1 = new Point(10, 20);
		point1.show();
		point1.show(false);
		
		Point point2 = new Point(100, 200);
		// point2.show();
		drawPoint(point2);

		Point point3 = new ColorPoint(50, 100, "red");
		// point3.show();
		// point3.show(true);
		drawPoint(point3);
		
		Rect rect = new Rect();
		// drawRect(rect);
		drawShape(rect);
		
		Triangle triangle = new Triangle();
		// drawTriangle(triangle);
		drawShape(triangle);
		
		Circle circle = new Circle();
		drawShape(circle);
	}
	
//	public static void drawColorPoint(ColorPoint pt) {
//		pt.show();
//	}
	
	public static void drawPoint(Point pt) {
		pt.show();
	}

	public static void drawShape(Shape shape) {
		shape.draw();
	}

//	public static void drawRect(Rect rect) {
//		rect.draw();
//	}
//
//	public static void drawTriangle(Triangle triangle) {
//		triangle.draw();
//	}
	
}
