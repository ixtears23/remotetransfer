package junseok.project;

import java.util.Properties;

import org.junit.Test;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public class SftpClientTest {
	
	private String remoteHost = "remoteHost";
	private int remotePort = 2200;
	private String username = "username";
	private String password = "password";

	@Test
	public void whenUploadFileUsingJsch_thenSuccess() throws JSchException, SftpException {
	    ChannelSftp channelSftp = setupJsch();
	    channelSftp.connect();
	  
	    String localFile = "src/main/resources/sample.txt";
	    String remoteDir = "remoteDir";
	  
	    channelSftp.put(localFile, remoteDir + "jschFile.txt");
	  
	    channelSftp.exit();
	}
	
	private ChannelSftp setupJsch() throws JSchException {
	    JSch jsch = new JSch();
//	    jsch.setKnownHosts("/Users/john/.ssh/known_hosts");
	    Session jschSession = jsch.getSession(username, remoteHost, remotePort);
	    Properties config = new Properties(); 
	    config.put("StrictHostKeyChecking", "no");
	    jschSession.setConfig(config);
	    jschSession.setPassword(password);
	    jschSession.connect();
	    
	    return (ChannelSftp) jschSession.openChannel("sftp");
	}
}
