package object.chara.player;

import java.awt.Color;
import java.util.ArrayList;

import densan.s.game.drawing.Drawer;
/**
 * @author k.minamoto
 */
public class RobotPlayer extends Player{
	int hpGageX=12,hpGageY=20,hpGageWidht=200,hpGageHeight=20;
	/**
	 * @param x
	 * @param y
	 */
	public RobotPlayer(double x, double y) {
		super(x, y,"image/player/walk/Walk_Player2.png");
	}
	@Override
	public void setUp(){
		super.setUp();
	}
	@Override
	public void setImages() {
		//プレイヤーが動いている間,画像がmoveImagesに設定した画像に順番に入れ替わる.
		for(int i=1;i<=8;i++){
			walkImages.add("image/player/walk/Walk_Player"+i+".png");
		}
		//静止画も同様,このサンプルでは一枚しか用意していないため静止中はずっとこのまま
		stopImages.add("image/player/walk/Walk_Player3.png");
		for(int i=1;i<=8;i++){
			runImages.add("image/player/run/Run_Player"+i+".png");
		}
		jumpImages.add("image/player/Jump_Player.png");
		attackImages=new ArrayList<String>();
		for(int i=1;i<=2;i++){
			attackImages.add("image/player/attack/Player_Attack"+i+".png");
		}
	}
	@Override
	public void update(){
		super.update();
	}
	@Override
	public void draw(Drawer d){
		super.draw(d);
//		d.fillRect(hpGauzeX-10, hpGauzeY-10, hpGauzeWidht+20,hpGauzeHeight+20);
//		d.fillRect(hpGauzeX-9, hpGauzeY-9, hpGauzeWidht+18,hpGauzeHeight+18);
//		d.setColor(Color.WHITE);
		d.setColor(Color.BLACK);
		d.fillRect(hpGageX-8, hpGageY-8, hpGageWidht+16,hpGageHeight+16);
//		d.fillRect(hpGauzeX-7, hpGauzeY-7, hpGauzeWidht+14,hpGauzeHeight+14);
		d.setColor(Color.LIGHT_GRAY);
		d.fillRect(hpGageX-6, hpGageY-6, hpGageWidht+12,hpGageHeight+12);
		d.setColor(Color.GRAY);
		d.fillRect(hpGageX-5, hpGageY-5, hpGageWidht+10,hpGageHeight+10);
		d.setColor(Color.DARK_GRAY);
		d.fillRect(hpGageX-4, hpGageY-4, hpGageWidht+8,hpGageHeight+8);
//		d.fillRect(hpGauzeX-3, hpGauzeY-3, hpGauzeWidht+6,hpGauzeHeight+6);
		d.setColor(Color.BLACK);
		d.fillRect(hpGageX-2, hpGageY-2, hpGageWidht+4,hpGageHeight+4);
		d.setColor(Color.RED);
		d.fillRect(hpGageX,hpGageY ,hpGageWidht,hpGageHeight);
		d.setColor(Color.green);
		d.fillRect(hpGageX,hpGageY ,hpGageWidht*hp/MAX_HP,hpGageHeight);
	} 
}
