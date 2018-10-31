package stage;

/**
 * ステージ上の一マスの大きさ
 * @author k.minamoto
 */
public enum Trout{
	MEASURE(32);
	private int measure;
    private Trout(int length ){
        this.measure = length;
    }
    /**
     * 一マスの長さを返す
     * @return
     */
    public int getMeasure() {
        return measure;
    }
}
