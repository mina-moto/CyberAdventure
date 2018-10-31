package backGround;

import main.Main;
import stage.Stage;
import densan.s.game.drawing.Drawer;
import densan.s.game.image.ImageLoader;

/**
 * 朝焼け
 * @author k.minamoto
 */
public class MorningGrow implements BackGround{
	@Override
	public void draw(Drawer d) {
		for(int i=0;i<4;i++){
			d.drawImage(ImageLoader.load("image/backGround/MorningGlow.jpg")
					, (int)Stage.offsetX/10+i*Main.FW-50
					, (int)Stage.offsetY/10
					,Main.FW,Main.FH);
		}
	}
}
