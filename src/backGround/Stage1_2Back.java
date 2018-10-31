package backGround;

import densan.s.game.drawing.Drawer;

/**
 * @author k.minamoto
 */
public class Stage1_2Back implements BackGround{
	BackGround sunset=new Sunset();
//	BackGround mountain=new Mountain();
	@Override
	public void draw(Drawer d) {
		sunset.draw(d);
//		mountain.draw(d);
	}
}
