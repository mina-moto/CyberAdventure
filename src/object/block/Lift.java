package object.block;

import densan.s.game.drawing.Drawer;

/**
 * @author k.minamoto
 */
public class Lift extends Block{
	int count;
	boolean stop,up,down;
	/**
	 * @param x
	 * @param y
	 * @param image
	 */
	public Lift(double x, double y) {
		super(x, y, "image/block/Lift.png");
	}
	@Override
	public void setUp(){
		super.setUp();
		count=0;
		up=true;
		down=false;
	}
	@Override
	public void update() {
		super.update();
		count++;
		if(up){
			if(count<150){
				addY(-2);
			}
			if(count>300){
				count=0;
				up=false;
				down=true;
			}
		}else if(down){
			if(count<150){
				addY(2);
			}
			if(count>300){
				count=0;
				up=true;
				down=false;
			}
		}
		
	}
	@Override
	public void draw(Drawer d) {
		super.draw(d);
	}
}
