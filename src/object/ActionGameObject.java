package object;

import java.awt.Point;
import java.util.ArrayList;

import object.chara.player.Player;
import stage.Stage;
import stage.Trout;
import densan.s.game.drawing.Drawer;
import densan.s.game.image.ImageLoader;
import densan.s.game.object.ImageObjectBase;
/**
 * このアクションゲーム上のオブジェクト(プレイヤーや敵、ブロック、アイテムetc)
 * @author k.minamoto
 */
public class ActionGameObject extends ImageObjectBase{
	/**
	 * このオブジェクトの幅とサイズ
	 */
	protected double objectWidth,objectHeight;
	/**
	 * このオブジェクトが現在存在してるかどうか
	 */
	protected boolean exist;
	/**
	 * speedX:横方向移動速度<br>
	 * speedY:縦方向移動速度
	 */
	protected double speedX,speedY;
	/**
	 * 他のオブジェクトから見て衝突するかどうか、初期値はtrue(falseの場合貫通する)<br>
	 * あくまで当たり判定時に他のオブジェクトから見て衝突するかどうかであり、
	 * このオブジェクトが壁抜けしたりはしない
	 */
	protected boolean collision=true;
	/**
	 * 今、このオブジェクトで描画している画像の名前
	 */
	protected String imageName;
	/**
	 * 自身のいるステージ上のプレイヤー
	 */
	protected Player player;
	/**
	 * objects:自身のいるステージ上の全オブジェクト(プレイヤー除く)<br>
	 * blocks:自身のいるステージ上の全ブロック<br>
	 * enemys:自身のいるステージ上の全ての敵
	 */
	protected ArrayList<ActionGameObject> objects=new ArrayList<ActionGameObject>()
			,blocks=new ArrayList<ActionGameObject>()
			,enemys=new ArrayList<ActionGameObject>()
			,items=new ArrayList<ActionGameObject>();
	/**
	 * このオブジェクトのステージ開始時の初期位置
	 */
	protected Point startPosition=new Point();
	/**
	 * 画像imageのオブジェクトを
	 * 左からx番目、右からy番目のマス目の左上の座標に生成
	 * オブジェクトのサイズは画像のものとなる
	 * @param x
	 * @param y
	 * @param image
	 */
	public ActionGameObject(double x, double y,String image) {
		super(x*Trout.MEASURE.getMeasure()
				, y*Trout.MEASURE.getMeasure(),image);
		setObjectSize(getWidth(),getHeight());
		this.imageName=image;
		startPosition.setLocation(x*Trout.MEASURE.getMeasure()
				, y*Trout.MEASURE.getMeasure());
	}
	/**
	 * ステージ開始時の設定
	 */
	public void setUp(){
		setPos(startPosition);
		exist=true;
		speedX=0;
		speedY=0;
	}
	/**
	 * このオブジェクトのサイズを設定する(画像のサイズとは別)
	 * @param width
	 * @param height
	 */
	public void setObjectSize(double width,double height){
		this.objectWidth=width;
		this.objectHeight=height;
	}
	public void setImage(String image){
		this.imageName=image;
	}
	public double getObjectWidth(){
		return objectWidth;
	}
	public double getObjectHeight(){
		return objectHeight;
	}
	@Override
	public double getCenterX(){
		return getX()+ objectWidth/2;
	}
	@Override
	public double getCenterY(){
		return getY() +objectHeight/2;
	}
	public double getSpeedX(){
		return speedX;
	}
	public double getSpeedY(){
		return speedY;
	}
	public void setPlayer(Player player){
		this.player=player;
	}
	public void setEnemys(ArrayList<ActionGameObject> enemys){
		this.enemys=enemys;
	}
	public void setItems(ArrayList<ActionGameObject> items){
		this.items=items;
	}
	public void setObjects(ArrayList<ActionGameObject> objects){
		this.objects=objects;
	}
	public boolean getExist(){
		return exist;
	}
	public void setExist(boolean b){
		exist=b;
	}
	/**
	 * 他のオブジェクトから衝突されるかどうか返す
	 * @return このオブジェクトが衝突されるかどうか(falseで貫通)
	 */
	public boolean isCollsion(){
		return collision;
	}
	public void setCollsion(boolean b){
		collision=b;
	}
	/**
	 * 引数の画像のリストに基づいて現在の画像切り替え
	 */
	public void switchImage(ArrayList<String> images){
		for(int i=0;i<images.size();i++){
			if(i==images.size()-1){//最後の要素なら
				imageName=images.get(0);
				break;
			}
			if(imageName.equals(images.get(i))){
				imageName=images.get(i+1);
				break;
			}
		}
	}
	@Override
	public void update() {
		if(!exist)
			super.setPos(-500,-500);
	}
	@Override
	public void draw(Drawer d){
		if(imageName!=null)
			d.drawImage(ImageLoader.load(imageName),getX()+Stage.offsetX,getY()+Stage.offsetY);
	}
	/**
	 * 仕方なく
	 * @param dir
	 */
	public void setDir(Dir dir) {
	}
}

