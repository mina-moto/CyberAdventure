package map;

import java.awt.Color;

import densan.s.game.drawing.Drawer;
import densan.s.game.image.ImageLoader;

/**
 * 照準
 * @author k.minamoto
 */
public class Alignment{
	double x=0,y=0;
	double thick=2;//線の太さ
	int count;
	public Alignment(){
//		x=37;
//		y=388;
	}
	public void setUp() {
		count=0;
	}
	/**
	 * 描画位置セット
	 */
	public void setPos(double x,double y){
		this.x= x;
		this.y= y;
	}
	public void update() {
		count++;
	}
	public void draw(Drawer d) {
			d.drawImage(ImageLoader.load("image/map/Alignment.png"),(int) x,(int) y,50,50);
			d.setColor(Color.BLUE);
			d.fillRect(x+50, y+24, 1000, thick);//右の線
			d.fillRect(x+24, y+50, thick, 1000);//下の線
			d.fillRect(x-1000, y+24, 1000, thick);//左の線
			d.fillRect(x+24, y-1000, thick, 1000);//上の線
	}
}
