package scene;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import main.Main;
import map.Alignment;
import sound.AllSound;
import stage.Stage;
import stage.Stage1_1;
import stage.Stage1_2;
import stage.Stage1_3;
import window.StageSelectWindow;
import densan.s.game.drawing.Drawer;
import densan.s.game.image.ImageLoader;
import densan.s.game.input.KeyInput;
import densan.s.game.sound.SoundManager;
/**
 * ステージ選択画面
 * @author k.minamoto
 */
public class StageSelectScene implements Scene{
	/**
	 * stage1List:第一ステージの全ステージのリスト
	 */
	private ArrayList<Stage> stage1List=new ArrayList<Stage>()
				,stage2List=new ArrayList<Stage>()
				,stage3List=new ArrayList<Stage>()
				,stage4List=new ArrayList<Stage>()
				,stage5List=new ArrayList<Stage>();
	/**
	 * ステージ選択のマップ上の点の描画位置
	 */
	private ArrayList<Point> posList=new ArrayList<Point>();
	/**
	 * 各ステージを表示するウインドウ
	 */
	private static StageSelectWindow window;
	/**
	 * ウインドウを開いているかどうか
	 */
	static boolean windowOpen;
	/**
	 * 矢印が上から何行目を指しているか.一番上を0としてる
	 * 照準がマップ上の何番目のステージを指しているか
	 */
	int allowNum=0;
	/**
	 * 照準
	 */
	Alignment alignment;
	
	public StageSelectScene(){
		stage1List.add(new Stage1_1());
		stage1List.add(new Stage1_2());
		stage1List.add(new Stage1_3());
		stage2List.add(new Stage1_1());
		stage3List.add(new Stage1_1());
		stage4List.add(new Stage1_1());
		stage5List.add(new Stage1_1());
		/*
		 * 新しくステージを追加する場合,ここにステージ追加
		 */
		
		posList.add(new Point(30,370));//平原
		posList.add(new Point(230,200));//海岸
		posList.add(new Point(350,210));//古城
		posList.add(new Point(470,340));//雪原
		posList.add(new Point(580,320));//山岳
//		posList.add(new Point(240,300));//海原
		posList.add(new Point(60,70));//孤島
		alignment=new Alignment();
		window=new StageSelectWindow();
	}
	@Override
	public void setUp() {
		 SoundManager.playLoopBGM(AllSound.STAGESELECT_BGM);
		 alignment.setUp();
		 windowOpen=false;
	}
	/**
	 * ウインドウを閉じる
	 */
	public static void closeWindow() {
		 windowOpen=false;
	}
	@Override
	public void update() {
		if (!windowOpen) {
			alignment.setPos(posList.get(allowNum).getX() - 13,
					posList.get(allowNum).getY() - 12);
//			if(MouseInput.isInFrame())
//				alignment.setPos(MouseInput.getMouseX()-26,MouseInput.getMouseY()-24);
			alignment.update();
			if (KeyInput.isPress(KeyEvent.VK_UP)) {
				SoundManager.playSE(AllSound.CURSOR_MOVE_SE);
				if (allowNum == 0) {
					allowNum=1;
				}
				else if (allowNum == 3) {
					allowNum=2;
				}
			}
			if (KeyInput.isPress(KeyEvent.VK_DOWN)) {
				SoundManager.playSE(AllSound.CURSOR_MOVE_SE);
				if (allowNum == 1) {
					allowNum=0;
				}
				else if (allowNum == 2) {
					allowNum=3;
				}
			}
			if (KeyInput.isPress(KeyEvent.VK_RIGHT)) {
				SoundManager.playSE(AllSound.CURSOR_MOVE_SE);
				if (allowNum == 0) {
					allowNum=1;
				}
				else if (allowNum == 1) {
					allowNum=2;
				}
				else if (allowNum == 2) {
					allowNum=3;
				}
				else if (allowNum == 3) {
					allowNum=4;
				}
			}
			if (KeyInput.isPress(KeyEvent.VK_LEFT)) {
				SoundManager.playSE(AllSound.CURSOR_MOVE_SE);
				if (allowNum == 1) {
					allowNum=0;
				}
				else if (allowNum == 2) {
					allowNum=1;
				}
				else if (allowNum == 3) {
					allowNum=2;
				}
				else if (allowNum == 4) {
					allowNum=3;
				}
			}
			if (KeyInput.isPress(KeyEvent.VK_Z)) {
//				SoundManager.playSE(AllSound.DECIDE_SE);
				SoundManager.playSE(AllSound.OPENWINDOW_SE);
//				GameScene.setStage(stage1List.get(allowNum));
//				Main.changeScene(Main.gameScene);
				windowOpen=true;
				window.setUp();
				if(allowNum==0){
					window.setStageList(stage1List);
				}else if(allowNum==1){
					window.setStageList(stage2List);
				}else if(allowNum==2){
					window.setStageList(stage3List);
				}else if(allowNum==3){
					window.setStageList(stage4List);
				}else if(allowNum==4){
					window.setStageList(stage5List);
				}
			}
			if (KeyInput.isPress(KeyEvent.VK_X)){
				SoundManager.playSE(AllSound.CLOSEWINDOW_SE);
				Main.changeScene(Main.titleScene);
			}
		}else{
			window.update();
		}
	}
	@Override
	public void draw(Drawer d) {
		d.setColor(Color.BLACK);
		d.fillRect(0, 0,Main.FW,Main.FH);
		d.drawImage(ImageLoader.load("image/map/Map.png"), 0, 0,Main.FW,Main.FH);
		for(Point pos:posList)
			d.drawImage(ImageLoader.load("image/map/GreenBall.png"),pos.getX() ,pos.getY());
		alignment.draw(d);
		if(windowOpen){
			window.draw(d);
		}
		d.setColor(Color.BLACK);
		d.fillRect(0,Main.FH-30, Main.FW, 30);
		d.setColor(Color.CYAN);
		d.setFontSize(20);
		d.drawStringCenter("Zで決定, Xで戻る", Main.FW/2, Main.FH-17);
	}
}
