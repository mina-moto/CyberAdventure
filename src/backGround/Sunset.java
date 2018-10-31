package backGround;

import main.Main;
import stage.Stage;
import densan.s.game.drawing.Drawer;
import densan.s.game.image.ImageLoader;

/**
 * @author k.minamoto
 */
public class Sunset implements BackGround{
	@Override
	public void draw(Drawer d) {
		for(int i=0;i<4;i++){
			d.drawImage(ImageLoader.load("image/backGround/Sunset_Up.png")
					,(int)Stage.offsetX/10+i*Main.FW-50
					,(int)Stage.offsetY/10-50);
			d.drawImage(ImageLoader.load("image/backGround/Sunset.jpg")
					, (int)Stage.offsetX/10+i*Main.FW-50
					, (int)Stage.offsetY/10
					,Main.FW,Main.FH);
		}
	}
}
