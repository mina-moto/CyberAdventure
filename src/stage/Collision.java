package stage;

import java.util.ArrayList;

import object.ActionGameObject;
/**
 * ステージ上の当たり判定を行う
 * @author k.minamoto
 */
public class Collision {
	/**
	 * 引数objectが引数objectsのどれかと重なっているか返す
	 * @return 引数objectが重なっているかどうか
	 */
	public static boolean isOverLapObjects(ActionGameObject object,ArrayList<ActionGameObject> objects) {
		boolean hit = false;
		for (ActionGameObject obj : objects) {
			if(isOverLap(object,obj)){
				if (!obj.equals(object)) {//自分自身は含めない
					hit=true;
					break;
				}
			}
		}
		return hit;
	}
	/**
	 * 引数object1と引数object2が重なっているか返す
	 * @param object1
	 * @param object2
	 * @return 引数object1が重なっているかどうか
	 */
	public static boolean isOverLap(ActionGameObject object1,ActionGameObject object2){
		return (Math.abs(object1.getCenterX() - object2.getCenterX()) 
				< object1.getObjectWidth() / 2 + object2.getObjectWidth() / 2 // 横の判定
				&& 
				Math.abs(object1.getCenterY() - object2.getCenterY()) 
				< object1.getObjectHeight()/ 2+ object2.getObjectHeight() / 2);// 縦の判定
	}
	/**
	 * 引数objectsの中で引数objectと重なっているオブジェクト返す.ない時はnull
	 * @param object
	 * @param objects
	 * @return 引数objectと重なっているオブジェクト
	 */
	public static ActionGameObject getOverLapObject(ActionGameObject object,ArrayList<ActionGameObject> objects) {
		ActionGameObject overLapObj = null;
		for (ActionGameObject obj : objects) {
				if (isOverLap(object, obj)) {
					if (!obj.equals(object)) {// 自分自身は含めない
						overLapObj = obj;
						break;
					}
				}
		}
		return overLapObj;
	}
	/**
	 * 引数objectsの中で引数objectと衝突しているオブジェクト返す.ない時はnull
	 * @param object
	 * @param objects
	 * @return 引数objectと衝突しているオブジェクト
	 */
	public static ActionGameObject getHitObject(ActionGameObject object,ArrayList<ActionGameObject> objects) {
		ActionGameObject hitObj = null;
		for (ActionGameObject obj : objects) {
			if (obj.isCollsion()) {
				if (isOverLap(object, obj)) {
					if (!obj.equals(object)) {// 自分自身は含めない
						hitObj = obj;
						break;
					}
				}
			}
		}
		return hitObj;
	}
}
