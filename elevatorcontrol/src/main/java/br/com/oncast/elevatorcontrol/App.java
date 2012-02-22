package br.com.oncast.elevatorcontrol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

import br.com.oncast.elevatorcontrol.domain.Elevator;
import br.com.oncast.elevatorcontrol.service.ElevatorService;
import br.com.oncast.elevatorcontrol.service.EngineService;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {

		boolean isConnected = false;
		Socket clientSocket = null;
		List<Thread> engineServicePool = new ArrayList<Thread>();
		Integer serverPort = 8800;
		
		ElevatorService service = ElevatorService.getElevatorService();
		for (int i : service.getElevatorPool().keySet()) {
			EngineService engineService = new EngineService(service.getElevatorPool().get(i));
			Thread engineThread = new Thread(engineService);
			engineThread.start();
			engineServicePool.add(engineThread);
		}
		try{
			ServerSocket listenSocket = new ServerSocket(serverPort);
			String message = "";
			System.out.println("Server is up - accepting connections on port " + serverPort);
			System.out.println("==>> Connect via Telnet");
			System.out.println("Usage [direction (u)p|(d)own] [floor]");
			System.out.println("      Example: to up from 4th floor: u 4");
			while(!message.equalsIgnoreCase("quit")) {
				if (!isConnected) {
					clientSocket= listenSocket.accept();
					System.out.println("Sensor connected. type 'quit' to disconnect");

					isConnected = true;
				} else {
					InputStreamReader inputReader = new InputStreamReader(clientSocket.getInputStream());
					BufferedReader bufferReader = new BufferedReader(inputReader);
					message = bufferReader.readLine();

					if (!message.equalsIgnoreCase("quit")) {
						StringTokenizer tokenizer = new StringTokenizer(message);
						try {
							String token = tokenizer.nextToken(" ");
							Character direction = token.charAt(0);
							if (direction != Elevator.UP && direction != Elevator.DOWN) {
								System.out.println("Invalid direction: [" + message + "]");
							} else {
								token = tokenizer.nextToken(" ");
								Integer floor = new Integer(token);
								System.out.println("Call " + direction + " in floor " + floor);
								service.callElevator(direction, floor);
							}
						} catch(NoSuchElementException e) {
							System.out.println("Invalid message: [" + message + "]");
						} catch (NumberFormatException e) {
							System.out.println("Invalid floor: [" + message + "]");
						}
					}
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (clientSocket != null)
					clientSocket.close();
			} catch (IOException e){}
		}
		for(Thread t : engineServicePool) {
			t.interrupt();
		}
		System.out.println("System is down.");
	}
}
