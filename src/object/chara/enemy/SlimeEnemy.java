package object.chara.enemy;

import java.util.Random;

import object.Dir;
import object.chara.attack.Attack;
import stage.Collision;
import densan.s.game.drawing.Drawer;


/**
 * スライムの敵<br>
 * @author k.minamoto
 */
public class SlimeEnemy extends Enemy{
	int winceCount;
	Random rand;
	/**
	 * 怯み状態か
	 */
	boolean wince;
	/**
	 * @param x
	 * @param y
	 */
	public SlimeEnemy(int x,int y) {
		super(x,y, "image/enemy/slime/Slime1.png");
		rand=new Random();
		moveSpeed=2;
		jumpSpeed=2;
		this.MAX_HP=20;
	}
	
	@Override
	public void setUp(){
		super.setUp();
		dir=Dir.LEFT;
		winceCount=0;
		moveCount=rand.nextInt(50);
		wince=false;
		isOriginal=true;
	}
	@Override
	public void setImages() {
		for(int i=1;i<=5;i++){
			walkImages.add("image/enemy/slime/Slime"+i+".png");
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
		if(!wince){
			moveCount++;
			if(moveCount>100){
				move(dir);
				jump();
				if ((moveCount != 0 && moveCount % 5 == 0))
					switchImage(walkImages);
			}else{
				if(getCenterX()>player.getCenterX()){
					dir=Dir.LEFT;
				}else{
					dir=Dir.RIGHT;
				}
			}
			if(moveCount>125){
				moveCount=0;
				super.setImage("image/enemy/slime/Slime1.png");
			}
			if (Collision.isOverLap(this, player.getPAtttack())) 
				damaged(((Attack) player.getPAtttack()).getPower());
		}
		fallOut();
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
