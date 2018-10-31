package object.block;

import object.ActionGameObject;
import densan.s.game.drawing.Drawer;

/**
 * ブロック
 * @author k.minamoto
 */
public class Block extends ActionGameObject{
	/**
	 * @param x
	 * @param y
	 * @param image
	 */
	public Block(double x, double y,String image) {
		super(x, y, image);
	}
	@Override
	public void update() {
		super.update();
	}
	@Override
	public void draw(Drawer d) {
		super.draw(d);
	}
}
