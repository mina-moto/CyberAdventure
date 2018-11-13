package stage;
import sound.AllSound;
import backGround.Stage1_1Back;
import densan.s.game.drawing.Drawer;
import densan.s.game.sound.SoundManager;

/**
 * サンプルステージ1
 * @author k.minamoto
 */
public class Stage1_1 extends Stage{
	@Override
	public void setUp(){
		super.setUp();
		SoundManager.playLoopBGM(AllSound.STAGE1_1BGM);
	}
	@Override
	public void generateStage() {
		super.generateStage("stageFile/Stage1-1");
		name="1-1";
		backGround=new Stage1_1Back();
		leftEnd=-200;
		rightEnd=3500;
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
