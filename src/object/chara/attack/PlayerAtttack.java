package object.chara.attack;

import object.Dir;
import stage.Stage;
import densan.s.game.drawing.Drawer;
import densan.s.game.image.ImageLoader;

/**
 * プレイヤーの攻撃
 * @author k.minamoto
 */
public class PlayerAtttack extends Attack{
	Dir dir;
	int count;
//	private ArrayList<String> images;
	/**
	 * @param x
	 * @param y
	 */
	public PlayerAtttack(double x, double y) {
		super(x, y, "image/player/attack/NomalAttack_Effect.png");
		collision=false;
		dir=Dir.RIGHT;
//		images=new ArrayList<String>();
		power=10;
//		images.add("image/player/attack/Attack_Effect"+i+".png");
	}
	@Override
	public void setUp(){
		super.setUp();
		super.setCenterPos(-500,-500);
		count=0;
		exist=false;
	}
	public void setCount(int count){
		this.count=count;
	}
	public void getDir(Dir dir){
		this.dir=dir;
	}
	@Override
	public void setDir(Dir dir){
		this.dir=dir;
	}
	@Override
	public void update() {
		super.update();
		if(exist){
		/*
			count++;
			if ((count%13==0))
				switchImage(images);
			//オブジェクトのサイズを現在の画像のサイズへ
			super.setObjectSize(ImageLoader.load(imageName).getWidth(null),ImageLoader.load(imageName).getWidth(null));
		}else{
			imageName=images.get(0);
		}*/
		}
	}
	@Override
	public void draw(Drawer d){
		if(dir.equals(Dir.RIGHT))//右向きならそのまま描画
			d.drawFlipImage(ImageLoader.load(imageName), getX()+Stage.offsetX, getY()+Stage.offsetY,false, false);
		else if((dir.equals(Dir.LEFT)))//反転して描画
			d.drawFlipImage(ImageLoader.load(imageName), getX()+Stage.offsetX, getY()+Stage.offsetY,true, false);
	}
}
