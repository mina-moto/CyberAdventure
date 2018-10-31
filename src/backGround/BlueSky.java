package backGround;
import java.awt.Image;
import java.util.ArrayList;

import main.Main;
import stage.Stage;
import densan.s.game.drawing.Drawer;
import densan.s.game.image.ImageLoader;

/**
 * 青空の背景描画
 * @author k.minamoto
 */
public class BlueSky implements BackGround{
	protected ArrayList<Image> images=new ArrayList<Image>();
	@Override
	public void draw(Drawer d){
		for(int i=0;i<4;i++){
			d.drawImage(ImageLoader.load("image/backGround/BlueSky.jpg")
					, (int)Stage.offsetX/10+i*Main.FW-50
					, (int)Stage.offsetY/10
					,Main.FW,Main.FH);
		}
	}
}
