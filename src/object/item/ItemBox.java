package object.item;

import object.ActionGameObject;

/**
 * アイテムボックス()
 * @author k.minamoto
 */
public class ItemBox extends ActionGameObject{
	/**
	 * @param x
	 * @param y
	 * @param image
	 */
	public ItemBox(double x, double y) {
		super(x, y, "image/ItemBox.png");
	}
}
