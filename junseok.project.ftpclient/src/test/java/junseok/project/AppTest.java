package junseok.project;


import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;

import org.junit.Test;
import org.mockftpserver.fake.FakeFtpServer;

public class AppTest {
	
	FakeFtpServer fakeFtpServer;
	
	@Test
	public void givenRemoteFile_whenDownloading_thenItIsOnTheLocalFilesystem() throws IOException {
	    String ftpUrl = String.format(
	      "ftp://user:password@localhost:%d/foobar.txt", fakeFtpServer.getServerControlPort());
	 
	    URLConnection urlConnection = new URL(ftpUrl).openConnection();
	    InputStream inputStream = urlConnection.getInputStream();
	    Files.copy(inputStream, new File("downloaded_buz.txt").toPath());
	    inputStream.close();
	    
	    File file = new File("downloaded_buz.txt");
	    
	    boolean exists = file.exists();
	 
	    assertTrue(exists);
	 
	    new File("downloaded_buz.txt").delete(); // cleanup
	}
	
}
