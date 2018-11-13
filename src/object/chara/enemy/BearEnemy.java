package object.chara.enemy;

import java.util.ArrayList;
import java.util.Random;

import object.Dir;
import object.chara.attack.Attack;
import stage.Collision;
import densan.s.game.drawing.Drawer;
import densan.s.game.image.ImageLoader;


/**
 * スライムの敵<br>
 * @author k.minamoto
 */
public class BearEnemy extends Enemy{
	int winceCount;
	Random rand;
	int count;
	/**
	 * 怯み状態か
	 */
	boolean wince;
	private int attackCount;
	private ArrayList<String>attackImages;
	private boolean isAttack;
	/**
	 * @param x
	 * @param y
	 */
	public BearEnemy(int x,int y) {
		super(x,y, "image/enemy/bear/walk/Walk_Bear4.png");
		moveSpeed=1;
		jumpSpeed=2;
		this.MAX_HP=1000;
	}
	
	@Override
	public void setUp(){
		super.setUp();
		power=5;
		dir=Dir.LEFT;
		count=0;
		winceCount=0;
		attackCount=0;
		isAttack=false;
		isOriginal=true;
	}
	@Override
	public void setImages() {
		for(int i=1;i<=8;i++){
			walkImages.add("image/enemy/bear/walk/Walk_Bear"+i+".png");
		}
		attackImages=new ArrayList<String>();
		for(int i=1;i<=4;i++){
			attackImages.add("image/enemy/bear/attack/Attack_Bear"+i+".png");
		}
	}
	/**
	 * 攻撃処理<br>
	 * 攻撃状態の時に実行される
	 */
	public void attack() {
		attackCount++;
		if(attackCount<28){
			moveSpeed=2;
			if ((attackCount % 7 == 1))
				switchImage(attackImages);
		}
		if(attackCount>=15){
			power=20;
//			isOriginal=false;
			move(dir);
			if(attackCount>=50){
				super.setObjectSize(ImageLoader.load(imageName).getWidth(null)
						, super.getObjectHeight());
				power=5;
				attackCount=0;
				moveSpeed=1;
				isAttack=false;
			}
		}
	}

	@Override
	public void damaged(int power){
		super.damaged(power);
		wince=true;
	}

	/**
	 * 画像の切り替えを完全に新しく実装
	 */
	@Override
	public void update() {
		super.update();
		count++;
		if(!isAttack){
			if ((count != 0 && count % 7 == 0))
				switchImage(walkImages);
			if (getCenterX() > player.getCenterX()) {
				dir = Dir.LEFT;
			} else {
				dir = Dir.RIGHT;
			}
			move(dir);
		}
		if (Math.abs(getCenterX()-player.getCenterX())<180){
			isAttack=true;
		}
		if (isAttack) {
			attack();
		}
		if (Collision.isOverLap(this, player.getPAtttack()))
			damaged(((Attack) player.getPAtttack()).getPower());
		fallOut();
		if (wince) {
			winceCount++;
			if (winceCount > 50) {
				wince = false;// 一定時間で怯み終了
				winceCount = 0;
				if (hp <= 0)
					exist = false;
			}
		}
	}
	@Override
	public void draw(Drawer d){
		if(exist){
			if(winceCount%2<1)
				super.draw(d);
		}
	}
}
