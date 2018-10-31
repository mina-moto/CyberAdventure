package backGround;

import densan.s.game.drawing.Drawer;

/**
 * @author k.minamoto
 */
public class Stage1_1Back implements BackGround{
	BackGround blueSky=new BlueSky();
//	BackGround mountain=new Mountain();
	@Override
	public void draw(Drawer d) {
		blueSky.draw(d);
//		mountain.draw(d);
	}
}
