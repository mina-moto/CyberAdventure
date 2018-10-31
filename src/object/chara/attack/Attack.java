package object.chara.attack;

import object.ActionGameObject;

/**
 * キャラの攻撃
 * @author k.minamoto
 */
public class Attack extends ActionGameObject{
	/**
	 * @param x
	 * @param y
	 * @param image
	 */
	public Attack(double x, double y, String image) {
		super(x, y, image);
		// TODO 自動生成されたコンストラクター・スタブ
	}
	/**
	 * この攻撃の威力
	 */
	protected int power;
	public int getPower(){
		 return power;
	}
	public void getPower(int power){
		 this.power=power;
	}
}
