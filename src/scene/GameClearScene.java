package scene;

import java.awt.Color;
import java.awt.event.KeyEvent;

import main.Main;
import densan.s.game.drawing.Drawer;
import densan.s.game.input.KeyInput;

/**
 * @author k.minamoto
 */
public class GameClearScene implements Scene {
	int num,score;
	/* (非 Javadoc)
	 * @see scene.Scene#setUp()
	 */
	@Override
	public void setUp() {
		num=0;
	}
	public void setScore(int score) {
		this.score=score;
	}
	@Override
	public void update() {
		if(num<score){
			num+=99;
			if (KeyInput.isPress(KeyEvent.VK_X)||(num>score)) {
				num=score;
			}
		}else if (KeyInput.isPress(KeyEvent.VK_X)) {//Xが押された時に現在のシーンをselectにする(ステージ選択画面へ遷移)
			Main.changeScene(Main.selectScene);
		}
	}
	@Override
	public void draw(Drawer d) {
		d.setColor(Color.DARK_GRAY);
		d.fillRect(0,0,Main.FW, Main.FH);
		d.setColor(Color.CYAN);
		d.setFontSize(70);//文字サイズ設定
		
		d.drawStringCenter("GAME CLEAR", Main.FW/2, Main.FH/2-100);//(座標(30,30)にテキスト表示)
		d.drawString("SCORE:"+num, Main.FW/2-250, Main.FH/2+200);
		d.setColor(Color.BLACK);
		d.fillRect(0,Main.FH-30, Main.FW, 30);
		d.setColor(Color.CYAN);
		d.setFontSize(20);
		d.drawStringCenter("X:Back", Main.FW/2, Main.FH-17);
	}
}
