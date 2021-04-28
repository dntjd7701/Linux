package paint;

public class ColorPoint extends Point {
	private String color;

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public void show() {
		System.out.println("점[" + getX() + "," + getY() + "," + color + "]에 점을 그렸습니다.");
	}
}
