package backGround;

import java.awt.Image;

import stage.Stage;
import densan.s.game.drawing.Drawer;
import densan.s.game.image.ImageLoader;

/**
 * 城内のような背景描画
 * @author k.minamoto
 */
public class Castle implements BackGround{
	Image image=ImageLoader.load("image/backGround/CastleOfSilver1.jpg");
	@Override
	public void draw(Drawer d) {
		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
			d.drawImage(image
					, (int)Stage.offsetX/2+i*image.getWidth(null)-150
					, (int)Stage.offsetY/2+j*(image.getHeight(null))-150
					);
			}
		}
	}
}
