package object.chara.enemy;

import object.ActionGameObject;
import object.Dir;
import object.chara.attack.Attack;
import stage.Collision;
import densan.s.game.drawing.Drawer;


/**
 * 鳥の敵<br>
 * @author k.minamoto
 */
public class BirdEnemy extends Enemy{
	int winceCount;
	/**
	 * 怯み状態か
	 */
	boolean wince;
	/**
	 * @param x
	 * @param y
	 */
	public BirdEnemy(int x,int y) {
		super(x,y, "image/enemy/bird/Bird2.png");
		moveSpeed=1;
		this.MAX_HP=10;
		interval=10;
	}
	/**
	 * 飛行処理
	 */
	public void flyght(){
		flyghtCount++;
		if(flyghtCount==20){
			speedY=0.1;
		}else if(flyghtCount==40){
			speedY=-0.1;
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
		winceCount=0;
		wince=false;
	}
	@Override
	public void setImages() {
		for(int i=1;i<=4;i++){
			walkImages.add("image/enemy/bird/Bird"+i+".png");
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
		if(!wince){
			if(moveCount==500){
				dir=Dir.LEFT;
			}else if(moveCount==1000){
				moveCount=0;
				dir=Dir.RIGHT;
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
				if (hp <= 0)
					exist=false;
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
