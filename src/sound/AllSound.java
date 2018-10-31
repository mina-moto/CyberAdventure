package sound;

import densan.s.game.sound.SoundManager;

/**
 * 全サウンドを保持
 * @author k.minamoto
 */
public class AllSound {
	public static final int TITLE_BGM = SoundManager.loadBGM("sound/bgm/Title_Bgm.mp3");
	public static final int STAGESELECT_BGM = SoundManager.loadBGM("sound/bgm/StageSelect_Bgm.mp3");
	public static final int STAGE1_1BGM = SoundManager.loadBGM("sound/bgm/Stage1_1_Bgm.mp3");
	public static final int STAGE1_2BGM = SoundManager.loadBGM("sound/bgm/Stage1_2_Bgm.mp3");
	public static final int STAGE1_3BGM = SoundManager.loadBGM("sound/bgm/Stage1_3_Bgm.mp3");
	
	public static final int TITLEROGO_SE = SoundManager.loadSE("sound/se/TitleRogo_SE.mp3");
	public static final int TITLEDECIDE_SE = SoundManager.loadSE("sound/se/TitleDecide_Se.mp3");
	public static final int DECIDE_SE = SoundManager.loadSE("sound/se/Decide_Se.mp3");
	public static final int CURSOR_MOVE_SE = SoundManager.loadSE("sound/se/CurosrMove_Se.mp3");
	public static final int OPENWINDOW_SE = SoundManager.loadSE("sound/se/OpenWindow_Se.mp3");
	public static final int CLOSEWINDOW_SE = SoundManager.loadSE("sound/se/CloseWindow_Se.mp3");
	public static final int JUMP_SE = SoundManager.loadSE("sound/se/Jump_Se.mp3");
	public static final int DAMAGED_SE = SoundManager.loadSE("sound/se/Damaged_Se.mp3");
	public static final int ATTACK_SE = SoundManager.loadSE("sound/se/Attack_Se.mp3");
}
