package scene;

import java.awt.event.KeyEvent;

import main.Main;
import sound.AllSound;
import stage.Stage;
import window.PauseWindow;
import window.Window;
import densan.s.game.drawing.Drawer;
import densan.s.game.input.KeyInput;
import densan.s.game.sound.SoundManager;

/**
 * 実際にゲームをしている画面
 * @author k.minamoto
 */
public class GameScene implements Scene{
	/**
	 * 一時停止中かどうか
	 */
	private static boolean pause=false;
	/**
	 * 現在のステージ
	 */
	private static Stage nowStage;
	/**
	 * ポーズ中に表示するウインドウ
	 */
	private static Window pauseWindow;
	/**
	 * ゲームクリア時のスコア
	 */
	private static int score;
	public GameScene(){
		pauseWindow=new PauseWindow();
	}
	/**
	 * 引数のステージを
	 * 現在のステージとしてセット
	 * @param stage
	 */
	public static void setStage(Stage stage){
		nowStage=stage;
	}
	@Override
	public void setUp() {
		nowStage.setUp();
		pause=false;
		score=10000;
	}
	/**
	 * 現在のステージをクリアする
	 * @param stage
	 */
	public static void gameClear(){
		((GameClearScene) Main.gameClear).setScore(score);
		Main.changeScene(Main.gameClear);
	}
	@Override
	public void update() {
		if(!pause){//ポーズ画面表示してない時の動作
			if (KeyInput.isPress(KeyEvent.VK_SPACE)){
				SoundManager.playSE(AllSound.OPENWINDOW_SE);
				pause=true;//ポーズ画面表示
			}
			nowStage.update();
		}else{
			pauseWindow.update();
		}
	}
	@Override
	public void draw(Drawer d) {
		nowStage.draw(d);
		if(pause)
			pauseWindow.draw(d);
	}
	/**
	 * 現在のスコアに加算する
	 * @param addScore
	 */
	public static void addScore(int addScore){
		score+=addScore;
	}
	public static void setPause(boolean p){
		pause=p;
	}
}
