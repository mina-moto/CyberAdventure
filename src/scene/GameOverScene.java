package scene;

import java.awt.Color;
import java.awt.event.KeyEvent;

import main.Main;
import densan.s.game.drawing.Drawer;
import densan.s.game.input.KeyInput;

/**
 * @author k.minamoto
 */
public class GameOverScene implements Scene {
	@Override
	public void update() {
		//Zが押された時に現在のシーンをselectにする(ステージ選択画面へ遷移)
		if (KeyInput.isPress(KeyEvent.VK_Z)) {
			Main.changeScene(Main.selectScene);
		}
	}
	@Override
	public void draw(Drawer d) {
		d.setColor(Color.BLACK);//文字色設定
		d.setFontSize(30);//文字サイズ設定
		d.drawString("GAME OVER", 30, 30);//(座標(30,30)にテキスト表示)
		d.drawString("Z:Back", 30, 330);
	}
	/* (非 Javadoc)
	 * @see scene.Scene#setUp()
	 */
	@Override
	public void setUp() {
		// TODO 自動生成されたメソッド・スタブ

	}
}
