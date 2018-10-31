package window;

import java.awt.Color;
import java.awt.event.KeyEvent;

import main.Main;
import scene.GameScene;
import sound.AllSound;
import densan.s.game.drawing.Drawer;
import densan.s.game.input.KeyInput;
import densan.s.game.sound.SoundManager;

/**
 * 一時停止中の表示ウィンドウ
 * @author k.minamoto
 */
public class PauseWindow extends Window{
	/**
	 * ポーズ画面で矢印が何行目を指しているか
	 */
	private int allowNum=0;
	/**
	 * @param x
	 * @param y
	 */
	public PauseWindow() {
		super(5,100,"image/window/Syber_Window.png");
		setSize(Main.FW-10, Main.FH/2);
	}
	@Override
	public void showWord(Drawer d, String word) {
		String[] words = word.split("\n");
		for (int i = 0; i < words.length; i++){
			d.drawString(words[i], getX()+30, getY()+40+30 * (i + 1));
		}
		// d.drawScaleImage(window, FRAMEX/8,FRAMEY*4/7, 2);
	}
	@Override
	public void update(){
		if (KeyInput.isPress(KeyEvent.VK_UP)){
			allowNum=0;
			SoundManager.playSE(AllSound.CURSOR_MOVE_SE);
		}
		else if (KeyInput.isPress(KeyEvent.VK_DOWN)){
			allowNum=1;
			SoundManager.playSE(AllSound.CURSOR_MOVE_SE);
		}
		if (KeyInput.isPress(KeyEvent.VK_SPACE)){
			GameScene.setPause(false);
			SoundManager.playSE(AllSound.CLOSEWINDOW_SE);
		}
		if (KeyInput.isPress(KeyEvent.VK_Z)){
			GameScene.setPause(false);
			SoundManager.playSE(AllSound.DECIDE_SE);
			if(allowNum==0){
				GameScene.setPause(false);
			}
			else if(allowNum==1){
				allowNum=0;
				Main.changeScene(Main.selectScene);//ステージ選択画面に戻る
			}
		}
	}
	@Override
	public void draw(Drawer d){
		d.setColor(Color.cyan);
		d.setFontSize(20);
		super.draw(d);
		showWord(d,"ゲームを続ける\nリタイア");
		d.setFontSize(15);
		d.drawRect(30, 150+30*allowNum, 200,25);
//		d.drawString("▶︎",20, 170+30*allowNum);
	}
}
