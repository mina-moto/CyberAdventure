package object.item;

import java.util.ArrayList;

import stage.Collision;
import densan.s.game.drawing.Drawer;

/**
 * @author k.minamoto
 */
public class Energy extends Item{
	int count;
	/**
	 * 回復量
	 */
	int healNum;
	private ArrayList<String> images=new ArrayList<String>();
	/**
	 * @param x
	 * @param y
	 * @param image
	 */
	public Energy(double x, double y) {
		super(x, y, "image/item/energy/Energy1.png");
		for(int i=1;i<=3;i++)
			images.add("image/item/energy/Energy"+i+".png");
		healNum=30;
	}
	@Override
	public void setUp(){
		super.setUp();
		exist=true;
		count=0;
//		speedY=-0.02;
		addY(-1);
	}
	@Override
	public void update(){
		super.update();
		count++;
		if(count%15==0){
			super.switchImage(images);
		}
		if(Collision.isOverLap(this, player)){
			exist=false;
			player.heal(healNum);
		}
		/*
		if(count%100==0)
			speedY=-speedY;
		addY(speedY);
		*/
	}

	@Override
	public void draw(Drawer d){
		super.draw(d);
	}
}
