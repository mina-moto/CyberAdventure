package object.chara.player;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import main.Main;
import object.ActionGameObject;
import object.Dir;
import object.chara.Chara;
import object.chara.attack.PlayerAtttack;
import object.chara.enemy.Enemy;
import sound.AllSound;
import stage.Collision;
import densan.s.game.drawing.Drawer;
import densan.s.game.input.KeyInput;
import densan.s.game.sound.SoundManager;

/**
 * 操作キャラ
 * @author k.minamoto
 */
public abstract class Player extends Chara{
	/**
	 * 操作可能かどうか
	 */
	private static boolean handle;
	/**
	 * WALK_SPEED:歩行速度<br>
	 * RUN_SPEED:走行速度
	 */
	protected final double WALK_SPEED=13,RUN_SPEED=14.5;
	/**
	 * flash:プレイヤーがダメージを受けて点滅している状態かどうか<br>
	 * invincibly:プレイヤーが無敵かどうか<br>
	 * isAttack:攻撃してるかどうか<br>
	 */
	protected boolean flash,invincibly,isAttack;;
	/**
	 * flashCount:ダメージを受けた後の点滅に使用するカウント
	 */
	protected int flashCount,attackCount;
	/**
	 * プレイヤーの攻撃時に対する画像名のリスト<br>
	 */
	protected ArrayList<String> attackImages;
	/**
	 * プレイヤーの通常攻撃時間
	 */
	protected int attackTime;
	/**
	 * プレイヤーの通常攻撃
	 */
	protected PlayerAtttack nomalAttack;
	/**
	 * @param x
	 * @param y
	 * @param image 静止時画像
	 */
	public Player(double x, double y,String image) {
		super(x,y,image);
		nomalAttack=new PlayerAtttack(x, y);
		jumpSpeed=3;//ジャンプ力設定
		MAX_HP=100;
	}
	/**
	 * プレイヤーの画像リストの設定
	 */
	@Override
	public abstract void setImages();
	@Override
	public void setUp(){
		super.setUp();
		flashCount=0;
		attackCount=0;
		isAttack=false;
		flash=false;
		invincibly=false;
		moveSpeed=WALK_SPEED;
		dir=Dir.RIGHT;
		handle=true;
	}
	/**
	 * 攻撃処理<br>
	 * 攻撃状態の時に実行される
	 */
	public void attack() {
		nomalAttack.setExist(true);
		nomalAttack.setDir(dir);
		attackCount++;
		if ((attackCount == 7))
			SoundManager.playSE(AllSound.ATTACK_SE);
		if(attackCount<=13){
			if ((attackCount % 7 == 1))
				switchImage(attackImages);
		}
		if(attackCount>20){
			attackCount=0;
			isOriginal=false;
			isAttack=false;
			nomalAttack.setExist(false);
			nomalAttack.setCount(0);
		}
		if(dir.equals(Dir.RIGHT))
			nomalAttack.setPos(getCenterX()+30, getCenterY()-10);
		else if(dir.equals(Dir.LEFT))
			nomalAttack.setPos(getCenterX()-60, getCenterY()-10);
	}
	/**
	 * 引数回復する処理
	 * @param power 
	 */
	public void heal(int healNum){
		hp+=healNum;
		if(hp>MAX_HP){
			hp=MAX_HP;
		}
	}
	/**
	 * 引数のダメージを受けた時の処理
	 * @param power 
	 */
	@Override
	public void damaged(int power){
		super.damaged(power);
		SoundManager.playSE(AllSound.DAMAGED_SE);
	}
	/**
	 * ジャンプ処理.長押しで大ジャンプ.
	 */
	@Override
	public void jump() {
		super.jump();
		if (onGround)//地面から飛ぶ時のみ音鳴らす
			SoundManager.playSE(AllSound.JUMP_SE);
		speedY-=0.15;//長押し
	}
	public int getHp(){
		return hp;
	}
	public ActionGameObject getPAtttack(){
		return nomalAttack;
	}
	/**
	 * プレイヤーを操作不能とする
	 */
	public static void disableHandle(){
		handle=false;
	}
	@Override
	public void update() {
		super.update();
		fallOut();// プレイヤーの落下処理
		if (!handle){
			isOriginal = true;
			imageName="image/player/walk/Walk_Player3.png";
		}
		if (handle) {
			if (KeyInput.isPressing(KeyEvent.VK_Z)) {
				isOriginal = true;
				isAttack = true;
			}
			nomalAttack.update();
			if (isAttack) {
				attack();
			}
			if (KeyInput.isPressing(KeyEvent.VK_UP))
				jump();// プレイヤーのジャンプ
			if (KeyInput.isPressing(KeyEvent.VK_LEFT)) {
				move(Dir.LEFT);
			} else if (KeyInput.isPressing(KeyEvent.VK_RIGHT)) {
				move(Dir.RIGHT);
			} else {
				isMoving = false;
			}
			if (isRun)
				moveSpeed = RUN_SPEED;
			else
				moveSpeed = WALK_SPEED;
			if (flash) {// 点滅中は無敵
				flashCount++;
				if (flashCount > 50) {
					flash = false;// 一定時間で点滅終了
					invincibly = false;// 無敵時間終了
					flashCount = 0;
				}
			}
			if (!invincibly) {// 無敵でない時
				Enemy hitEnemy=(Enemy) Collision.getOverLapObject(this, enemys);
				if (hitEnemy != null) {
					damaged(hitEnemy.getPower());
					flash = true;// 点滅させるようにする
					invincibly = true;// 一定時間無敵にする
				}
			}
			if (getHp() <= 0)
				Main.changeScene(Main.gameOver);
		}
	}
	@Override
	public void draw(Drawer d){
		if(flashCount%3<1)
			super.draw(d);
		if(isAttack)
			nomalAttack.draw(d);
	}
}
