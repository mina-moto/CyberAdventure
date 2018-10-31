package object.item;

import java.util.ArrayList;

import scene.GameScene;
import stage.Collision;
import densan.s.game.drawing.Drawer;

/**
 * @author k.minamoto
 */
public class Coin extends Item{
	int count;
	private ArrayList<String> images=new ArrayList<String>();
	/**
	 * @param x
	 * @param y
	 * @param image
	 */
	public Coin(double x, double y) {
		super(x, y, "image/item/coin/Coin1.png");
		for(int i=1;i<=5;i++)
			images.add("image/item/coin/Coin"+i+".png");
	}
	@Override
	public void setUp(){
		super.setUp();
		exist=true;
		count=0;
//		speedY=-0.02;
		addY(-3);
	}
	@Override
	public void update(){
		super.update();
		count++;
		if(count%10==0){
			super.switchImage(images);
		}
		if(Collision.isOverLap(this, player)){
			exist=false;
			GameScene.addScore(1000);
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
