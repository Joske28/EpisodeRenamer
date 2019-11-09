package joske28.renamer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
	
	public Renamer(String path, String fileExtension, String name, String season, int episodeNumber) {
		this.path = path;
		this.fileExtension = fileExtension;
		this.name = name;
		this.episodeNumber = episodeNumber;
		this.season = season;
		this.episodes = getAllFiles(path, fileExtension);
	}
	
	
	public List<String> getAllFiles(String path, String fileExtenString) {
	
		List<String> episodes = null;
		
		try (Stream<Path> walk = Files.walk(Paths.get(path))) {

			episodes = walk.map(x -> x.toString())
					.filter(f -> f.endsWith(fileExtenString)).collect(Collectors.toList());

		} catch (IOException e) {
			e.printStackTrace();
		}
	
		return episodes;
	}
	
	public void rename(String pathToFile) {
		File file = new File(pathToFile);
		if (file.isFile()) {
			File file2 = new File(path + File.separator + name + "-" + "S" + season + "E" + episodeNumber + fileExtension);
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
