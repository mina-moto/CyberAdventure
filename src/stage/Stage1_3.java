package stage;

import sound.AllSound;
import backGround.Night;
import densan.s.game.drawing.Drawer;
import densan.s.game.sound.SoundManager;

/**
 * サンプルステージ3
 * @author k.minamoto
 */
public class Stage1_3 extends Stage{
	@Override
	public void setUp(){
		super.setUp();
		SoundManager.playLoopBGM(AllSound.STAGE1_3BGM);
	}
	@Override
	public void generateStage() {
		super.generateStage("stageFile/Stage1-3");
		name="1-3";
		leftEnd=-200;
		rightEnd=6500;
		upEnd=300;
		downEnd=100;
		backGround=new Night();
	}
	@Override
	public void update() {
		super.update();
	}
	@Override
	public void draw(Drawer d) {
		super.draw(d);
		if(player.getX()>5000){
			leftEnd=5000;
		}
		if(player.getX()>5000){
			rightEnd=6500;
		}
		d.setFontSize(20);
	}
}
