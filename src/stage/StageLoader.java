package stage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * テキストファイルからステージの情報を読み込み.
 * テキストの文字列を一行づつのリストとして渡すことができる
 * @author k.minamoto
 */
public class StageLoader {
	private static BufferedReader br;
	private static InputStream is;
	/**
	 * テキストファイルの文字列を一行づつ保持するリスト
	 */
	private static ArrayList<String> lines = new ArrayList<String>();
	/**
	 * 引数のファイル名のテキストを一行づつのの文字列のリストとして区切って返す
	 * @param stageFile
	 * @return stageの情報
	 */
	public static ArrayList<String> loadStage(String stageFile){
		lines.clear();
		is = Stage.class.getResourceAsStream(stageFile);
		try {
			br = new BufferedReader(new InputStreamReader(is));
			String str;
			while ((str = br.readLine()) != null) {
				lines.add(str);
			}
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}
		return lines;
	}
}
