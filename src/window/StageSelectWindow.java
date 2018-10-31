package window;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import main.Main;
import scene.GameScene;
import scene.StageSelectScene;
import sound.AllSound;
import stage.Stage;
import densan.s.game.drawing.Drawer;
import densan.s.game.input.KeyInput;
import densan.s.game.sound.SoundManager;

/**
 * ステージ選択ウィンドウ
 * @author k.minamoto
 */
public class StageSelectWindow extends Window{
	/**
	 * 矢印が何行目を指しているか
	 */
	private int allowNum=0;
	/**
	 * stageList:ウインドウで表示するステージのリスト
	 * stage1List:第一ステージの全ステージのリスト<br>
	 */
	private ArrayList<Stage> stageList=new ArrayList<Stage>();
	/**
	 * @param x
	 * @param y
	 */
	public StageSelectWindow() {
		super(5,100,"image/window/Syber_Window.png");
		setSize(Main.FW-10, Main.FH/2+100);
	}
	public void setUp(){
		allowNum=0;
	}
	/**
	 * @param stageList
	 */
	public void setStageList(ArrayList<Stage> stageList){
		this.stageList=stageList;
	}
	@Override
	public void update(){
		if (KeyInput.isPress(KeyEvent.VK_UP)){
			if(allowNum>0)
				allowNum--;
			SoundManager.playSE(AllSound.CURSOR_MOVE_SE);
		}
		else if (KeyInput.isPress(KeyEvent.VK_DOWN)){
			if(allowNum<stageList.size()-1)
				allowNum++;
			SoundManager.playSE(AllSound.CURSOR_MOVE_SE);
		}
		if (KeyInput.isPress(KeyEvent.VK_Z)){
			SoundManager.playSE(AllSound.DECIDE_SE);
			GameScene.setStage(stageList.get(allowNum));
			Main.changeScene(Main.gameScene);
		}
		if (KeyInput.isPress(KeyEvent.VK_X)){
			SoundManager.playSE(AllSound.CLOSEWINDOW_SE);
			StageSelectScene.closeWindow();
		}
	}
	@Override
	public void draw(Drawer d){
		d.setColor(Color.cyan);
		d.setFontSize(20);
		super.draw(d);
		String word="";
		for(Stage stage:stageList){
			word+=stage.getName()+"\n";
		}
		d.setFontSize(20);
		showWord(d,word);
		d.drawRect(30, 170+30*allowNum, 200,25);
	}
}
