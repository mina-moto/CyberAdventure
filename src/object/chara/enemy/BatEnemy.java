package object.chara.enemy;

import java.util.Random;

import object.ActionGameObject;
import object.Dir;
import object.chara.attack.Attack;
import stage.Collision;
import densan.s.game.drawing.Drawer;


/**
 * コウモリの敵<br>
 * @author k.minamoto
 */
public class BatEnemy extends Enemy{
	int winceCount;
	int count;
	Random rand;
//	Item item;
	/**
	 * 怯み状態か
	 */
	boolean wince;
	/**
	 * @param x
	 * @param y
	 */
	public BatEnemy(int x,int y) {
		super(x,y, "image/enemy/bat/Bat1.png");
		moveSpeed=3.5;
		this.MAX_HP=20;
		interval=5;
		rand=new Random();
//		item=new Energy(0,0);
	}
	/**
	 * 飛行処理
	 */
	public void flyght(){
		flyghtCount++;
		if(flyghtCount==100){
			speedY=0.3;
		}else if(flyghtCount==200){
			speedY=-0.3;
			flyghtCount=0;
		}
		if(!wince){
			addY(speedY);
		}
		ActionGameObject hitObj=Collision.getHitObject(this, objects);
		if(hitObj==null){
			onGround=false;
		}else if(hitObj!=null){
			if (speedY < 0){ // 天井衝突時
				setY(hitObj.getMaxY());
				flyghtCount++;
			}else if (speedY > 0){//地面衝突時
				setMaxY(hitObj.getY());
			}
		}
	}
	@Override
	public void setUp(){
		super.setUp();
		count=0;
		winceCount=0;
		wince=false;
		dir=Dir.LEFT;
	}
	@Override
	public void setImages() {
		for(int i=1;i<=4;i++){
			walkImages.add("image/enemy/bat/Bat"+i+".png");
		}
	}
	@Override
	public void damaged(int power){
		super.damaged(power);
		wince=true;
	}
	@Override
	public void update() {
		super.update();
		count++;
		if(!wince){
			if(count==200){
				dir=Dir.RIGHT;
			}else if(count==400){
				count=0;
				dir=Dir.LEFT;
			}
			move(dir);
			this.flyght();
			if (Collision.isOverLap(this, player.getPAtttack()))
				damaged(((Attack) player.getPAtttack()).getPower());
		}
		if(wince){
			winceCount++;
			if (winceCount > 50) {
				wince = false;//一定時間で怯み終了
				winceCount = 0;
				if (hp <= 0){
					exist=false;
				}
			}
		}
	}
	@Override
	public void draw(Drawer d){
		if(exist){
			if(winceCount%3<1)
				super.draw(d);
		}
	}
}
