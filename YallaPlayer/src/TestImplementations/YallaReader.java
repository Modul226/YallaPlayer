package TestImplementations;
import java.io.File;
import com.beaglebuddy.mp3.MP3;

public class YallaReader {
	public static void main(String[] args) {
		String path = "C:\\Temp\\TestBibliothek";
		File[] listOfFiles;
		File folder = null;
		String format = null;
		int countElements = 0;

		try {
			folder = new File(path);
			listOfFiles = folder.listFiles();

			for (File file : listOfFiles) {
				int i = file.getAbsolutePath().lastIndexOf('.');
				if (i > 0) {
					format = file.getAbsolutePath().substring(i + 1);
				}
				if (file.isFile() && format.equals("mp3")) {
					try {
						MP3 mp3 = new MP3(file.getAbsolutePath());
						countElements++;
						if (mp3.getBand() != null) {
							System.out.println(countElements + " " + mp3.getTitle() + " - " + mp3.getBand());
						} else {
							System.out.println(countElements + " " + mp3.getTitle() + " - " + mp3.getID3v1Tag().getArtist());
						}

					} catch (Exception e) {
						System.out.println("Probleme mit File " + file.getAbsolutePath() + "\n");
						e.printStackTrace();
					}
				} else {
					System.out.println("Keine Mp3-Datei! Dies war eine " + format + " Datei!");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Angegebener Pfad existiert nicht!");
		} finally {
			if (folder != null) {
				folder.deleteOnExit();
			}
		}
		
	}
}