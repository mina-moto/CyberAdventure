package stage;

import java.util.ArrayList;
import java.util.Random;

import backGround.BackGround;
import backGround.BlueSky;
import backGround.MorningGrow;
import backGround.Night;
import backGround.Sunset;
import densan.s.game.drawing.Drawer;

/**
 * タイトル画面で表示するステージ
 * @author k.minamoto
 */
public class TitleStage extends Stage{
	ArrayList<BackGround> backGrounds;
	Random rand;
	@Override
	public void setUp(){
		super.setUp();
		for(int i=0;i<backGrounds.size();i++){
			if(i==backGrounds.size()-1){
				backGround=backGrounds.get(0);
				break;
			}
			else if(backGround.equals(backGrounds.get(i))){
				backGround=backGrounds.get(i+1);
				break;
			}
		}
		/*
		if(backGround.equals(morningGrow))
				backGround=blueSky;
		else if(backGround==blueSky)
			backGround=sunset;
		else if(backGround==sunset)
			backGround=night;
		else if(backGround==night)
			backGround=morningGrow;
			*/
	}
	@Override
	public void generateStage() {
		super.generateStage("stageFile/titleStage");
		backGrounds=new ArrayList<BackGround>();
		backGrounds.add(new BlueSky());
		backGrounds.add(new Sunset());
		backGrounds.add(new Night());
		backGrounds.add(new  MorningGrow());
		rand = new Random();
		backGround=backGrounds.get(rand.nextInt(backGrounds.size()));
		leftEnd=-860;
		rightEnd=2420;
	}
	@Override
	public void update() {
		super.update();
	}
	@Override
	public void draw(Drawer d) {
		super.draw(d);
	}
}
