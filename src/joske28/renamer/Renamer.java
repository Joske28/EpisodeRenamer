package joske28.renamer;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Renamer {

	private String path;
	private List<String> episodes;
	private String fileExtension;
	private String name;
	private String season;
	private int episodeNumber;

	public Renamer(String path, String fileExtension) throws IOException {
		this.path = path;
		this.fileExtension = fileExtension;
		this.name = null;
		this.season = null;
		this.episodeNumber = 1;
		this.episodes = getAllFiles(path, fileExtension);
	}

	public Renamer(String path, String fileExtension, String name, String season, int episodeNumber)
			throws IOException {
		this.path = path;
		this.fileExtension = fileExtension;
		this.name = name;
		this.episodeNumber = episodeNumber;
		this.season = season;
		this.episodes = getAllFiles(path, fileExtension);
	}

	public List<String> getAllFiles(String path, String fileExtenString) throws IOException {

		List<String> episodes = null;

		Stream<Path> walk = Files.walk(Paths.get(path));

		episodes = walk.map(x -> x.toString()).filter(f -> f.endsWith(fileExtenString)).collect(Collectors.toList());
		episodes.sort(new Comparator<String>() {

			@Override
			public int compare(String episode1, String episode2) {
				//using big ints in case someone has a lot of numbers in their file name
				String digitRegex = "\\D+";
				BigInteger episode1Digits = new BigInteger(episode1.replaceAll(digitRegex, ""));
				BigInteger episode2Digits = new BigInteger(episode2.replaceAll(digitRegex, ""));
				
				return episode1Digits.compareTo(episode2Digits);
			}
		});
		walk.close();
		return episodes;
	}

	public void rename(String pathToFile) {
		File file = new File(pathToFile);
		if (file.isFile()) {
			File file2 = new File(
					path + File.separator + name + "-" + "S" + season + "E" + episodeNumber + fileExtension);
			file.renameTo(file2);
			++episodeNumber;
		}

	}

	public void renameAll() {
		if (this.episodes.size() > 0) {
			this.episodes.forEach(episode -> rename(episode));
		}
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public List<String> getEpisodes() {
		return episodes;
	}

	public void setEpisodes(List<String> episodes) {
		this.episodes = episodes;
	}

	public String getFileExtension() {
		return fileExtension;
	}

	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}

}
