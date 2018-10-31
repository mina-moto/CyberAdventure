package object.goal;

import densan.s.game.drawing.Drawer;

/**
 * ゴールのサンプル
 * @author k.minamoto
 */
public class Goal1 extends Goal{
	/**
	 * @param x
	 * @param y
	 * @param image
	 */
	public Goal1(double x, double y) {
		super(x, y, "image/door/Door.png");
		//初期位置若干上に調整
		startPosition.translate(0, -52);
	}
	@Override
	public void setUp(){
		super.setUp();
	}
	@Override
	public void draw(Drawer d){
		super.draw(d);
	}
}
