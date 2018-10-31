package object;
import densan.s.game.drawing.Drawer;
import densan.s.game.image.ImageLoader;
import densan.s.game.object.GameRectBase;
/**
 * ゲームのオブジェクト
 * @author k.minamoto
 */
public abstract class GameObject extends GameRectBase{
	/**
	 * このオブジェクトのx,y
	 */
//	protected double x,y;
	/**
	 * このオブジェクトの幅とサイズ
	 */
	protected double width,height;
	/**
	 * 画像imageのオブジェクトを座標(x,y)に生成
	 * オブジェクトのサイズは画像のものとなる
	 * @param x
	 * @param y
	 */
	public GameObject(double x, double y,String image) {
		super(x, y, ImageLoader.load(image).getWidth(null), ImageLoader.load(image).getHeight(null));
//		this.x=x;
//		this.y=y;
		width=ImageLoader.load(image).getWidth(null);
		height=ImageLoader.load(image).getHeight(null);
	}
	public abstract void update();
	public abstract void draw(Drawer d);
	/*
	@Override
	public double getX(){
		return x;
	}
	@Override
	public double getY(){
		return y;
	}
	@Override
	public void setX(double x){
		this.x=x;
	}
	@Override
	public void setY(double y){
		this.y=y;
	}*/
}
