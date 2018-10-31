package scene;

import densan.s.game.drawing.Drawer;

/**
 * 各シーンで行う処理の定義
 * @author k.minamoto
 */
public interface Scene {
	/**
	 * 1ループごとに行う更新処理
	 */
	public void update();

	/**
	 *  1ループごとに行う描画処理
	 * @param g
	 */
	public void draw(Drawer g);
	/**
	 * シーン変更時に行う初期設定
	 */
	
	public void setUp();

}
