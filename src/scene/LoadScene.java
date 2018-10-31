package scene;
import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Random;

import main.Main;
import densan.s.game.drawing.Drawer;
import densan.s.game.image.ImageLoader;
import densan.s.game.sound.SoundManager;
/**
 * あたかもロードしているかのように振る舞うシーン<br>
 * 一つ前のシーンのBgmの停止も行う
 * @author k.minamoto
 */
public class LoadScene implements Scene{
	/**
	 * 次に行くシーン
	 */
	static Scene nextScene;
	int count;
	final int bloacOutTime1=20;
	int bloacOutTime2;
	/**
	 * ロード時間. 毎回変わる
	 */
	int loadTime;
	Random rand = new Random();
	private Image image1;
	ArrayList<Image> images1=new ArrayList<Image>();
	private Image image2;
	ArrayList<Image> images2=new ArrayList<Image>();
	public LoadScene(){
		for(int i=1;i<12;i++){
			images1.add(ImageLoader.load("image/loadImage1/loadCircle0"+i+".gif"));
		}
		for(int i=1;i<49;i++){
			images2.add(ImageLoader.load("image/loadImage2/loading0"+i+".gif"));
		}
	}
	@Override
	public void setUp() {
		image1=images1.get(0);
		image2=images2.get(0);
		count=0;
//		loadTime=200+bloacOutTime1;
		//loadTime=200*rand.nextInt(3)+100+bloacOutTime1;
//		bloacOutTime2=loadTime+bloacOutTime1;
		SoundManager.stopBGM();
	}
	/**
	 * 次のシーンをセット
	 * @param nextScene
	 */
	public static void setNextScene(Scene nextScene){
		LoadScene.nextScene=nextScene;
	}
	/**
	 * 引数の画像のリストに基づいて現在の画像切り替え
	 */
	public void switchImage(ArrayList<Image> images){
		if(images.equals(images1)){
			for(int i=0;i<images.size();i++){
				if(i==images.size()-1){//最後の要素なら
					image1=images.get(0);
					break;
				}
				if(image1.equals(images.get(i))){
					image1=images.get(i+1);
					break;
				}
			}
		}else if(images.equals(images2)){
			for(int i=0;i<images.size();i++){
				if(i==images.size()-1){//最後の要素なら
					image2=images.get(0);
					break;
				}
				if(image2.equals(images.get(i))){
					image2=images.get(i+1);
					break;
				}
			}
		}
	}
	@Override
	public void update() {
		count++;
		if(count<bloacOutTime1){
		}else if(count<loadTime){
			if ((count != 0 && count % 4 == 0))
				switchImage(images1);
			if ((count != 0 && count % 3 == 0))
				switchImage(images2);
		}else if(count<bloacOutTime2){
		}else Main.changeScene(nextScene);
	}
	@Override
	public void draw(Drawer d) {
		d.setColor(Color.BLACK);
		d.fillRect(0, 0,Main.FW, Main.FH);
		if(count<loadTime&&count>bloacOutTime1){
			d.drawImage(image2, Main.FW/2-305, Main.FH/2-240);
			d.drawImage(image1,Main.FW-250, Main.FH-200,400,280);
		}
	}
}
