package main;
import scene.GameClearScene;
import scene.GameOverScene;
import scene.GameScene;
import scene.LoadScene;
import scene.Scene;
import scene.StageSelectScene;
import scene.TitleScean;
import densan.s.game.drawing.Drawer;
import densan.s.game.manager.GameManager;
import densan.s.game.manager.Updatable;
	/**
	 * Main
	 * 1秒間に60ループ、1ループするごとにupdate、drawを行う
	 * @author k.minamoto
	 */
	public class Main implements Updatable {
		/**
		 * FW:フレームの幅<br>
		 * FH:フレームの高さ
		 */
		public final static int FW=675,FH=540;
		/**
		 * 現在実行しているシーン
		 */
		public static Scene nowScene;
		/**
		 * titleScene:タイトル画面<br>
		 * selectScene:ステージ選択画面<br>
		 * gameScene:ゲーム実行画面<br>
		 * gameClearScene:ゲームクリア画面<br>
		 * gameOverScene:ゲームオーバー画面<br>
		 */
		public static Scene titleScene,gameScene,selectScene,gameClear,gameOver,loadScene;
		public Main(){
			titleScene= new TitleScean();
			gameScene=new GameScene();
			selectScene=new StageSelectScene();
			gameClear=new GameClearScene();
			gameOver=new GameOverScene();
			loadScene=new LoadScene();
			nowScene = titleScene;
			nowScene.setUp();
		}
		@Override
		public void update(){
			nowScene.update();
		}
		@Override
		public void draw(Drawer g) {
			nowScene.draw(g);
		}
		/**
		 * 現在のシーンを引数のシーンに変更
		 * @param scene
		 */
		public static void changeScene(Scene scene){
			if(nowScene!=loadScene){
				nowScene = loadScene;
				LoadScene.setNextScene(scene);
			}else nowScene = scene;
			nowScene.setUp();
			//nowScene = scene;
		}
		public static void main(String[] args) {
			//画面サイズ(横,縦)
			GameManager.getInstance().createFrame(FW,FH);
			GameManager.getInstance().setUpdatable(new Main());
		}
	}