package window;

import densan.s.game.drawing.Drawer;
import densan.s.game.object.ImageObjectBase;

/**
 * ウインドウ
 * @author k.minamoto
 */
public class Window extends ImageObjectBase {
	/**
	 * @param x
	 * @param y
	 * @param string
	 */
	public Window(double x,double y,String image) {
		super(x, y, image);
		//setCenterPos(Scene.FW/2, Scene.FH/2);
	}
	/**
	 * 第二引数の文字列を"\n"で分割し改行していい感じにウィンドウ上に描画
	 * 
	 * @param d
	 * @param word
	 */
	public void showWord(Drawer d, String word) {
		String[] words = word.split("\n");
		for (int i = 0; i < words.length; i++){
			d.drawString(words[i], getX()+35, getY()+60+30 * (i + 1));
		}
		// d.drawScaleImage(window, FRAMEX/8,FRAMEY*4/7, 2);
	}
	@Override
	public void draw(Drawer d){
		super.draw(d);
	}
	@Override
	public void update(){
	}
}
