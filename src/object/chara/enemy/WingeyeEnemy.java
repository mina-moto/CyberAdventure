package object.chara.enemy;

import object.ActionGameObject;
import object.Dir;
import object.chara.attack.Attack;
import stage.Collision;
import densan.s.game.drawing.Drawer;


/**
 * 一つ目のコウモリの敵<br>
 * @author k.minamoto
 */
public class WingeyeEnemy extends Enemy{
	int winceCount;
	/**
	 * 怯み状態か
	 */
	boolean wince;
	private int count;
	/**
	 * @param x
	 * @param y
	 */
	public WingeyeEnemy(int x,int y) {
		super(x,y, "image/enemy/wingeye/Wingeye1.png");
		moveSpeed=2.5;
		this.MAX_HP=20;
	}
	/**
	 * 飛行処理
	 */
	public void flyght(){
		flyghtCount++;
		if(flyghtCount==40){
			speedY=0.2;
		}else if(flyghtCount==80){
			speedY=-0.2;
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
		count=0;
		wince=false;
		dir=Dir.LEFT;
	}
	@Override
	public void setImages() {
		for(int i=1;i<=8;i++){
			walkImages.add("image/enemy/wingeye/Wingeye"+i+".png");
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
			if(count==150){
				if(dir==Dir.RIGHT)
					dir=Dir.LEFT;
				else
					dir=Dir.RIGHT;
				count=0;
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
