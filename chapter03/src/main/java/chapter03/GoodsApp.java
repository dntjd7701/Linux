package chapter03;

import mypackage.Goods;

public class GoodsApp {
	
	public static void main(String[] args) {
		Goods goods1 = new Goods();
		
		// public은 접근 제한이 없다.
		goods1.name = "camera";
		
		// protected는 같은 패키지에서만 접근이 가능하다.
		// (더 중요한 것은 자식에서만 접근이 가능하다.)
		// goods1.price = 1000;
		
		// 디폴트는 같은 패키지에서만 접근이 가능하다. 
		// goods1.countStock = 50;
		
		// private은 같은 클래스 내에서만 접근이 가능하다.
		// goods1.countSold = 20;

		
		Goods goods2 = new Goods();
		Goods goods3 = new Goods();
		
		System.out.println(Goods.countOfGoods);
	}
}
