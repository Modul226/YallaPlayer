import java.io.File;
import java.io.RandomAccessFile;

import org.farng.mp3.id3.ID3v1_1;

public class YallaReader {
	public static void main(String[] args){
		RandomAccessFile sourceFile;
		ID3v1_1 reader;
		File[] listOfFiles;
		File folder = null;
		
		try {
			folder = new File("C:\\Temp\\TestBibliothek");
			listOfFiles = folder.listFiles();
			
			for (File file : listOfFiles) {
				if (file.isFile()) {
					sourceFile = new RandomAccessFile(file.getAbsolutePath(), "rw");
					try {
						reader = new ID3v1_1(sourceFile);
						System.out.println(reader.getTitle() + " - " + reader.getArtist());
					} catch (Exception e){
						System.out.println("Probleme mit File!");
					}
					
					
					
				}
			}
		} catch (Exception e){
			e.printStackTrace();
			System.out.println("Angegebener Pfad existiert nicht!");
		} finally {
			if(folder != null){
				folder.deleteOnExit();
			}
		}
	}
}
