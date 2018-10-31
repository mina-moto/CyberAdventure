package object.chara.enemy;

import object.Dir;
import object.chara.attack.Attack;
import stage.Collision;
import densan.s.game.drawing.Drawer;


/**
 * キノコ型の敵<br>
 * @author k.minamoto
 */
public class MushroomEnemy extends Enemy{
	int winceCount;
	/**
	 * 怯み状態か
	 */
	boolean wince;
	/**
	 * @param x
	 * @param y
	 */
	public MushroomEnemy(int x,int y) {
		super(x,y, "image/enemy/mushroom/Mushroom1.png");
		moveSpeed=1;
		this.MAX_HP=10;
	}
	@Override
	public void setUp(){
		super.setUp();
		winceCount=0;
		wince=false;
		dir=Dir.LEFT;
	}
	@Override
	public void setImages() {
		for(int i=1;i<=8;i++)
			walkImages.add("image/enemy/mushroom/Mushroom"+i+".png");
	}
	@Override
	public void damaged(int power){
		super.damaged(power);
		wince=true;
	}
	@Override
	public void update() {
		super.update();
		fallOut();//落下処理
		if(!wince){
			if(moveCount==450){
				dir=Dir.LEFT;
			}else if(moveCount==900){
				moveCount=0;
				dir=Dir.RIGHT;
			}
			move(dir);
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
