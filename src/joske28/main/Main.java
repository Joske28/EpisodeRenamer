package joske28.main;

import joske28.renamer.Renamer;

public class Main {

	public static void main(String[] args) {

		Renamer renamer = new Renamer("D:\\Downloads\\Infinite Stratos", ".mp4", "Infinite Stratos", "1", 1);
		renamer.renameAll();
	}

}
