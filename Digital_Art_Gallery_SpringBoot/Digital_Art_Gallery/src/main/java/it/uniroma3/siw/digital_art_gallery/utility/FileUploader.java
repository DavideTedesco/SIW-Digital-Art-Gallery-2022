package it.uniroma3.siw.digital_art_gallery.utility;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.web.multipart.MultipartFile;

public class FileUploader {


	     
	    public static void saveFile(String uploadDir, String fileName,
	            MultipartFile multipartFile) throws IOException {
	        Path uploadPath = Paths.get(uploadDir);
	        
	         
	        if (!Files.exists(uploadPath)) {
	            Files.createDirectories(uploadPath);
	        }
	         
	        try (InputStream inputStream = multipartFile.getInputStream()) {
	            Path filePath = uploadPath.resolve(fileName);
	            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
	        } catch (IOException ioe) {        
	            throw new IOException("Could not save image file: " + fileName, ioe);
	        }      
	    }
	    
	    public static void deleteFile(String deletionDir, String fileName) throws IOException {
	    	Path storagePath = Paths.get(deletionDir);
	    	Path deletionPath = storagePath.resolve(fileName);
	    	
	    	
	    	try {
	    		Files.delete(deletionPath);
	        } catch (IOException ioe) {        
	            throw new IOException("Could not delete image file: " + fileName, ioe);
	        }     
	    }
	
}
