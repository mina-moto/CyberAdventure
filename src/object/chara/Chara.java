package object.chara;

import java.util.ArrayList;

import object.ActionGameObject;
import object.Dir;
import stage.Collision;
import stage.Stage;
import densan.s.game.drawing.Drawer;
import densan.s.game.image.ImageLoader;

/**
 * キャラクター
 * @author k.minamoto
 */
public abstract class Chara extends ActionGameObject{
	/**
	 * jumpSpeed:ジャンプ時の初速、ジャンプ力(初期値3)<br>
	 * moveSpeed:横移動速度(初期値3)
	 */
	protected double jumpSpeed=3,moveSpeed=3;
	/**
	 * 現在のHP
	 */
	protected int hp;
	/**
	 * 歩行時の画像切り替えを何カウントごとに行うか(初期値7)
	 */
	protected int interval;
	/**
	 * MAX_HP:このキャラの最大HP<br>
	 */
	protected int MAX_HP=10;
	/**
	 * 落下速度の最大値、これ以上速い速度で落下させない
	 */
	protected final double MAX_DROP_SPEED=6;
	/**
	 * flyghtCount:空中にいる間のカウンタ<br>
	 * moveCount:動いてる間進むカウンタ
	 */
	protected int flyghtCount=0,moveCount=0;
	/**
	 * キャラの各状態に対する画像名のリスト<br>
	 * moveImages:歩行時<br>
	 * runImages:走行時<br>
	 * stopImages:静止時<br>
	 * jumpImages:ジャンプ時(没)
	 */
	protected ArrayList<String> walkImages=new ArrayList<String>()
			,stopImages=new ArrayList<String>()
			,runImages=new ArrayList<String>()
			,jumpImages=new ArrayList<String>();
	/**
	 * 左右どちらを向いているか
	 */
	protected Dir dir;
	/**
	 * isMoving:動いているかどうか<br>
	 * onGround:地面に着地しているかどうか<br>
	 * isRun:走っているかどうか<br>
	 * isOriginal:このクラスで定義している状態以外の時
	 */
	protected boolean isOriginal,isMoving,onGround,isRun,isJump;
	/**
	 * @param x
	 * @param y
	 * @param image
	 */
	public Chara(double x, double y, String image) {
		super(x, y, image);
		setImages();
		interval=7;
	}
	/**
	 * キャラの画像リストの設定
	 */
	public abstract void setImages();
	@Override
	public void setUp(){
		super.setUp();
		isMoving=false;
		isRun=false;
		isOriginal=false;
//		isJump=false;
		dir=Dir.LEFT;
		hp=MAX_HP;
	}
	/**
	 * 横移動、速度分dirの方向へ移動
	 * @param dir
	 */
	public void move(Dir dir) {
		isMoving=true;
		this.dir = dir;
		if (dir.equals(Dir.LEFT)) {// 左移動処理
			speedX = -moveSpeed;
			addX(speedX);
			ActionGameObject hitObj=Collision.getHitObject(this, objects);
			if(hitObj!=null)
				setX(hitObj.getMaxX());
		} else if (dir.equals(Dir.RIGHT)) {// 右移動処理
			speedX = moveSpeed;
			addX(speedX);
			ActionGameObject hitObj=Collision.getHitObject(this, objects);
			if(hitObj!=null)
				setMaxX(hitObj.getX());
		}
	}
	/**
	 * ジャンプ処理<br>
	 * 地面についていない場合何もしない
	 */
	public void jump() {
		if (onGround) {
//			isJump=true;
			speedY=-jumpSpeed;
			addY(speedY);
		}
	}
	/**
	 * 重力に従い落下処理,上方向速度の方が大きい場合は上昇
	 */
	public void fallOut(){
		speedY+=Stage.GRAVITY*(flyghtCount+1)/2;
		if(speedY>MAX_DROP_SPEED)
			speedY=MAX_DROP_SPEED;
		addY(speedY);
		ActionGameObject hitObj=Collision.getHitObject(this, objects);
		if(hitObj==null){
			onGround=false;
			flyghtCount++;
		}else if(hitObj!=null){
			if (speedY < 0){ // 天井衝突時
				setY(hitObj.getMaxY());
				flyghtCount++;
			}else if (speedY > 0){//着地時
				setMaxY(hitObj.getY());
				onGround=true;
//				isJump=false;
				flyghtCount=0;
			}
			speedY=0;
		}
	}
	/**
	 * ダメージを受けた時の処理
	 * @param power 
	 */
	public void damaged(int power){
		hp-=power;
	}
	/**
	 * 歩行時,走行時,静止時に対する画像切り替え
	 */
	@Override
	public void update(){
		super.update();
		if(isOriginal){//その他の画像切り替え処理はサブクラスで行う
		}
		else if (isMoving){
			moveCount++;
			if(!runImages.isEmpty()){
				if(moveCount>80){//実質プレイヤー専用
					isRun=true;
					if ((moveCount != 0 && moveCount % 5 == 0))
						switchImage(runImages);
				}
				else{
					if ((moveCount != 0 && moveCount % interval == 0))
						switchImage(walkImages);
				}
			}
			else{
				if ((moveCount != 0 && moveCount % interval == 0))
					switchImage(walkImages);
			}
		}
		else{
			isRun=false;
			moveCount = 0;
			switchImage(stopImages);
		}
	}
	@Override
	public void draw(Drawer d){
		if(dir.equals(Dir.RIGHT))//右向きならそのまま描画
			d.drawFlipImage(ImageLoader.load(imageName), getX()+Stage.offsetX, getY()+Stage.offsetY,false, false);
		else if((dir.equals(Dir.LEFT)))//反転して描画
			d.drawFlipImage(ImageLoader.load(imageName), getX()+Stage.offsetX, getY()+Stage.offsetY,true, false);
	}
}
