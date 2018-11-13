package stage;

import java.util.ArrayList;

import main.Main;
import object.ActionGameObject;
import object.block.Block;
import object.block.Lift;
import object.chara.enemy.BatEnemy;
import object.chara.enemy.BearEnemy;
import object.chara.enemy.BirdEnemy;
import object.chara.enemy.MushroomEnemy;
import object.chara.enemy.SlimeEnemy;
import object.chara.enemy.WingeyeEnemy;
import object.chara.player.Player;
import object.chara.player.RobotPlayer;
import object.chara.player.TitlePlayer;
import object.goal.Goal;
import object.goal.Goal1;
import object.item.Coin;
import object.item.Energy;
import object.item.ItemBox;
import scene.GameScene;
import backGround.BackGround;
import densan.s.game.drawing.Drawer;
/**
 * ステージ上の処理,
 * ブロックや敵の生成はgenerateメソッドで行い各変数に入れる
 * @author k.minamoto
 */
public abstract class Stage {
	boolean clear;
	/**
	 * このステージの名前
	 */
	protected String name;
	/**
	 * プレイヤー
	 */
	protected Player player;
	/**
	 * objects:ステージ上の全オブジェクト(プレイヤー除く)<br>
	 * blocks:ステージ上の全ブロック<br>
	 * enemys:ステージ上の全ての敵
	 */
	protected ArrayList<ActionGameObject> objects=new ArrayList<ActionGameObject>()
			,blocks=new ArrayList<ActionGameObject>()
			,enemys=new ArrayList<ActionGameObject>()
			,items=new ArrayList<ActionGameObject>();
	int count,clearCount;
	/**
	 * ゴール
	 */
	protected Goal goal;
	/**
	 * 背景
	 */
	protected BackGround backGround;
	/**
	 * スクロールする限界点(怪しい)<br>
	 * downEnd:下方向の限界点,大きいほど下方向へスクロールできる<br>
	 * upEnd:上方向の限界点,大きいほど上方向へスクロールできる<br>
	 * leftEnd:左方向の限界点<br>
	 * rightEnd:右方向の限界点<br>
	 * プレイヤーが下方向に画面外に行くと死亡.
	 */
	protected double downEnd=0,upEnd=0,leftEnd=0,rightEnd=0;
	/**
	 * 開始後何秒たったか
	 */
	protected int time;
	/**
	 * ステージの重力
	 */
	public final static double GRAVITY=0.02;
	/**
	 * スクロールに使用する値<br>
	 * それぞれ描画時にこの値だけずらす
	 */
	public static double offsetX,offsetY;
	public Stage(){
		generateStage();
		for(ActionGameObject block:blocks)
			objects.add(block);
		if(goal!=null)
			objects.add(goal);
		for(ActionGameObject enemy:enemys)
			objects.add(enemy);
		for(ActionGameObject item:items)
			objects.add(item);
		player.setObjects(objects);
		player.setEnemys(enemys);
		player.setItems(items);
		for(ActionGameObject object:objects){
			object.setObjects(objects);
			object.setEnemys(enemys);
			object.setItems(items);
			object.setPlayer(player);
		}
	}
	/**
	 * ステージ開始時の初期設定
	 */
	public void setUp(){
		for(ActionGameObject obj:objects)
			obj.setUp();
		player.setUp();
		count=0;
		clearCount=0;
		time=0;
		clear=false;
	}
	/**
	 * ステージ生成時の設定
	 */
	public abstract void generateStage();
	/**
	 * ステージ上のオブジェクト生成<br>
	 * 引数のファイル名のファイルに基づき,
	 * ステージ上のプレイヤーやブロックなどのオブジェクトの生成,初期場所の設定を行う
	 */
	public void generateStage(String stageFileName){
		ArrayList<String> lines=StageLoader.loadStage("/"+stageFileName);
		for(int i=0;i<lines.size();i++){//テキストの行数分
			for(int j=0;j<lines.get(i).length();j++){//テキストの現在の行の列数分
				switch(lines.get(i).charAt(j)){
					case ' '://空白
						break;
					case '0'://見えないブロック
						blocks.add(new Block(j,i,"image/block/StelsBlock.png"));
						break;
					case 'P'://プレイヤー
						player=new RobotPlayer(j,i);
						break;
					case 'C'://コイン
						items.add(new Coin(j,i));
						break;
					case 'E'://回復
						items.add(new Energy(j,i));
						break;
					case 'L':
						blocks.add(new Block(j,i,"image/block/LeafSoil.png"));
						break;
					case 'S':
						blocks.add(new Block(j,i,"image/block/Soil.png"));
						break;
					case 'l':
						blocks.add(new Lift(j,i));
						break;
					case 't'://鳥
						enemys.add(new BirdEnemy(j,i));
						break;
					case 's'://スライム
						enemys.add(new SlimeEnemy(j,i));
						break;
					case 'm'://キノコ型敵
						enemys.add(new MushroomEnemy(j,i));
						break;
					case 'w'://一つ目のコウモリ
						enemys.add(new WingeyeEnemy(j,i));
						break;
					case 'b'://コウモリ
						enemys.add(new BatEnemy(j,i));
						break;
					case 'B'://熊(ボス)
						enemys.add(new BearEnemy(j,i));
						break;
					case 'G':
						goal=new Goal1(j,i);
						break;
					case 'I':
						blocks.add(new ItemBox(j,i));
						break;
					case 'T':
						player=new TitlePlayer(j,i);
						break;
					/*
					 * ここにcase追加
					 */
					default:
						System.err.println(stageFileName+"で使えない文字が使用されています:"+lines.get(i).charAt(j));
						break;
				 }
			}
		}
	}
	public String getName(){
		return name;
	}
	/**
	 * ステージ全体の更新処理。 (非 Javadoc)
	 * @see scene.Scene#update()
	 */
	public void update() {
		count++;
		for (ActionGameObject object : objects)
			object.update();
		player.update();
		if(goal!=null){
			if(Collision.isOverLap(player, goal)){
				clear=true;
				Player.disableHandle();
			}
		}
		if(clear){
			clearCount++;
			if(clearCount>10){
				GameScene.gameClear();
			}
		}
		if(!clear){
			//プレイヤーが下方向画面外に行くとゲームオーバー
			if(player.getY()>downEnd+Main.FH) {
				Main.changeScene(Main.gameOver);
			}
			if(count%60==0)
				time++;
		}
	}
	/**
	 * (非 Javadoc)
	 * @see scene.Scene#draw(densan.s.game.drawing.Drawer)
	 */
	public void draw(Drawer d){
		offsetX = Main.FW / 2 - player.getX();
		offsetX = Math.min(offsetX, leftEnd);//左
		offsetX = Math.max(offsetX, -rightEnd);//右
		offsetY = Main.FH / 2 - player.getY();
		offsetY = Math.min(offsetY, upEnd);
		offsetY = Math.max(offsetY, -downEnd);
		if(backGround!=null)
			backGround.draw(d);//背景
		for(ActionGameObject object:objects)
			object.draw(d);
		player.draw(d);
		d.drawStringRight("TIME "+time, Main.FW-10,25);
	}
}
