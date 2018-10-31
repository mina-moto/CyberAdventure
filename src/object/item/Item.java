package object.item;
import object.ActionGameObject;
/**
 * アイテム
 * @author k.minamoto
 */
public class Item extends ActionGameObject{
	/**
	 * @param x
	 * @param y
	 * @param image
	 */
	public Item(double x, double y, String image) {
		super(x, y, image);
		collision=false;
	}
	@Override
	public void setUp(){
		super.setUp();
	}
	@Override
	public void update(){
		super.update();
	}
}
