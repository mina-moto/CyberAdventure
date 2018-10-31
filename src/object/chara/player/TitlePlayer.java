package object.chara.player;

import java.util.ArrayList;

/**
 * @author k.minamoto
 */
public class TitlePlayer extends Player{
	/**
	 * @param x
	 * @param y
	 * @param image
	 */
	public TitlePlayer(double x, double y) {
		super(x, y, "image/player/walk/Walk_Player3.png");
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
}
