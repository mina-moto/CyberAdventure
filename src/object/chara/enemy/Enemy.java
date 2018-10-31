package object.chara.enemy;

import object.chara.Chara;

/**
 * @author k.minamoto
 */
public abstract class Enemy extends Chara{
	/**
	 * プレイヤーと衝突した時にプレイヤに与えるダメージ(初期値5)
	 */
	int power;
	/**
	 * @param x
	 * @param y
	 * @param imageName
	 */
	public Enemy(double x, double y,String imageName) {
		super(x,y,imageName);
		collision=false;
		power=5;
	}
	/**
	 * 敵キャラの画像リストの設定
	 */
	@Override
	public abstract void setImages();
	/* (非 Javadoc)
	 * @see densan.s.game.object.GameObjectBase#update()
	 */
	@Override
	public void update() {
		super.update();
	}
	/**
	 * プレイヤーに与えるダメージ
	 * @return
	 */
	public int getPower(){
		return power;
	}
}
