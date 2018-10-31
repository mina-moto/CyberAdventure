package scene;

import java.awt.Color;
import java.awt.event.KeyEvent;

import main.Main;
import sound.AllSound;
import stage.Stage;
import stage.TitleStage;
import densan.s.game.drawing.Drawer;
import densan.s.game.image.ImageLoader;
import densan.s.game.input.KeyInput;
import densan.s.game.input.MouseInput;
import densan.s.game.sound.SoundManager;

/**
 * タイトル画面の処理
 * @author k.minamoto
 */
public class TitleScean implements Scene {
	private Stage titleStage;
	int count=0;
	int txtCount;
	boolean isPointStart;//GamestartにカーソルがあるかどうかfalseでOptionにカーソル
	public TitleScean(){
		titleStage=new TitleStage();
		setUp();
	}
	@Override
	public void setUp(){
		SoundManager.playLoopBGM(AllSound.TITLE_BGM);//一度実行すると止めるまで流れ続けるみたい
		titleStage.setUp();
		count=0;
		isPointStart=true;
		txtCount=30;
	}
	/**
	 * マウスがゲームスタートの上にあるかどうか
	 * @return
	 */
	public boolean isLocateStartMouse(){
		return(MouseInput.getMouseX()>Main.FW/2-170&&MouseInput.getMouseY()>Main.FH-170)
				&&(MouseInput.getMouseX()<Main.FW/2+190&&MouseInput.getMouseY()<Main.FH-130);
	}
	/**
	 * マウスがOptionの上にあるかどうか
	 * @return
	 */
	public boolean isLocateOptionMouse(){
		return(MouseInput.getMouseX()>Main.FW/2-170&&MouseInput.getMouseY()>Main.FH-100)
				&&(MouseInput.getMouseX()<Main.FW/2+45&&MouseInput.getMouseY()<Main.FH-60);
	}
	@Override
	public void update() {
		count++;
		if (KeyInput.isPress(KeyEvent.VK_UP)||isLocateStartMouse()){
			if(!isPointStart)
				SoundManager.playSE(AllSound.CURSOR_MOVE_SE);
			isPointStart=true;
		}
		if (KeyInput.isPress(KeyEvent.VK_DOWN)||isLocateOptionMouse()) {
			if(isPointStart)
				SoundManager.playSE(AllSound.CURSOR_MOVE_SE);
			isPointStart=false;
		}
		if(txtCount==1)
			SoundManager.playSE(AllSound.TITLEROGO_SE);
		if(txtCount>0)
			txtCount--;
		//zが押された時に現在のシーンをselectにする(ステージ選択画面へ遷移)
		if (KeyInput.isPress(KeyEvent.VK_Z)||(MouseInput.isClicking()&&this.isLocateStartMouse())) {
			if(isPointStart){
				Main.changeScene(Main.selectScene);
				SoundManager.playSE(AllSound.TITLEDECIDE_SE);
			}
		}
	}
	@Override
	public void draw(Drawer d) {
		d.drawString("A",-10,-10);//なぜか1文字目が潰れるバグの回避
		d.drawImage(ImageLoader.load("image/title/TitleBack.png"), 0, 0,Main.FW,Main.FH);
		d.drawImage(ImageLoader.load("image/title/Cyber_txt.png"), Main.FW/2-340, 20-txtCount*20);
		d.drawImage(ImageLoader.load("image/title/Adventure_txt.png"), Main.FW/2-310, 90+txtCount*20);
		if(isPointStart){
			d.drawImage(ImageLoader.load("image/title/Gamestart2.png"), Main.FW/2-230,Main.FH-200);
			d.drawImage(ImageLoader.load("image/title/Option1.png"), Main.FW/2-180,Main.FH-130);
		}
		else{
			d.drawImage(ImageLoader.load("image/title/Gamestart1.png"), Main.FW/2-180,Main.FH-200);
			d.drawImage(ImageLoader.load("image/title/Option2.png"), Main.FW/2-226,Main.FH-130);
		}
		d.setFontSize(30);//文字サイズ設定
		d.setColor(Color.CYAN);//文字色設定
	}
}
