package backGround;

import main.Main;
import stage.Stage;
import densan.s.game.drawing.Drawer;
import densan.s.game.image.ImageLoader;

/**
 * 山
 * @author k.minamoto
 */
public class Mountain implements BackGround{
	@Override
	public void draw(Drawer d) {
		for(int i=0;i<4;i++){
			d.drawScaleImage(ImageLoader.load("image/backGround/Mountain.png")
					, (int)Stage.offsetX/6+i*Main.FW-50
					, (int)Stage.offsetY/6-450,2);
		}
	}
}
