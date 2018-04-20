package activitystreamer.client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import activitystreamer.util.Settings;

public class ClientSkeleton extends Thread {
	private static final Logger log = LogManager.getLogger();
	private static ClientSkeleton clientSolution;
	private TextFrame textFrame;
		
	public static ClientSkeleton getInstance(){
		if(clientSolution==null){
			clientSolution = new ClientSkeleton();
		}
		return clientSolution;
	}
	
	public ClientSkeleton(){
		
		
		textFrame = new TextFrame();
		start();
	}
	
	
	
	private static OutputStream os;

	public static void ActivityMessageClient(String IPAddress, int port) {

		Socket socket = null;
		try {
			// Create a stream socket bounded to any port and connect it to the
			// socket bound to localhost on port 4444
			socket = new Socket(IPAddress ,port);

			// Get the input/output streams for reading/writing data from/to the socket
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
			Scanner scanner = new Scanner(System.in);
			String inputStr = null;

			//While the user input differs from "exit"
			while (!(inputStr = scanner.nextLine()).equals("exit")) {	
				
				// Send the input string to the server by writing to the socket output stream
				activitystreamer.Activity_Message.sendMsg(os, inputStr);
				// Receive the reply from the server by reading from the socket input stream
				String received = in.readLine(); // This method blocks until there
													// is something to read from the
													// input stream
				System.out.println("Message received: " + received);
			}
			
			scanner.close();
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// Close the socket
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	
	
	@SuppressWarnings("unchecked")
	public void sendActivityObject(JSONObject activityObj){
		
	}
	
	
	public void disconnect(){
		
	}
	
	
	public void run(){

	}

	
}
