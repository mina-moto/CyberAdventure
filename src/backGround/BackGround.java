package backGround;

import densan.s.game.drawing.Drawer;

/**
 * 背景
 * @author k.minamoto
 */
public interface BackGround {
	/**
	 * 1ループごとの背景の描画
	 * @param d
	 */
	public void draw(Drawer d);
}
