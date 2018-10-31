package object.goal;

import object.ActionGameObject;
import densan.s.game.drawing.Drawer;
/**
 * ゴール
 * @author k.minamoto
 */
public class Goal extends ActionGameObject{
	/**
	 * 
	 * @param x
	 * @param y
	 * @param image
	 */
	public Goal(double x, double y, String image) {
		super(x, y, image);
		//衝突しないよう設定
		collision=false;
	}
	@Override
	public void draw(Drawer d) {
		super.draw(d);
	}
}
