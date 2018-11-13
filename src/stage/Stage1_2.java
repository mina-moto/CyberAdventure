package stage;

import sound.AllSound;
import backGround.Stage1_2Back;
import densan.s.game.drawing.Drawer;
import densan.s.game.sound.SoundManager;

/**
 * ステージ1-2
 * @author k.minamoto
 */
public class Stage1_2 extends Stage{
	@Override
	public void setUp(){
		super.setUp();
		SoundManager.playLoopBGM(AllSound.STAGE1_2BGM);
	}
	@Override
	public void generateStage() {
		super.generateStage("stageFile/Stage1-2");
		name="1-2";
		upEnd=50;
		leftEnd=-200;
		rightEnd=3500;
		backGround=new Stage1_2Back();
	}
	@Override
	public void update() {
		super.update();
	}
	@Override
	public void draw(Drawer d) {
		super.draw(d);
		d.setFontSize(20);
	}
}
