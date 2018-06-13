package gob.mx.fonacot.BuroCreditoServerSpring.thread;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import gob.mx.fonacot.BuroCreditoServerSpring.data.Protocol;

import org.apache.log4j.Logger;

/**
 * Clase Runneable para ejecutar el servidor
 * 
 * @author grfloresb@ultrasist.com.mx
 * @since 11/06/2018
 */
@Component
@Scope("prototype")
public class ServerRunnable implements Runnable {

	/**
	 * Atributo para el manejo del Logger
	 */
	static Logger log = Logger.getLogger(ServerRunnable.class);
	/**
	 * Atributo que define el socket del servidor que atendera al cliente
	 */
	private Socket clientSocket;

	/**
	 * Atributo para el manejo de Threads
	 */
	public String name;

	public void run() {
		// Preparo mi entrada y salida
		PrintWriter out = null;
		try {
			out = new PrintWriter(clientSocket.getOutputStream(), true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String inputLine, outputLine;
		Protocol kkp = new Protocol();

		outputLine = kkp.processInput(null);
		out.println(outputLine);
		// Leo continuamente e interactuo
		try {
			while ((inputLine = in.readLine()) != null) {
//				outputLine = kkp.processInput(inputLine);
				outputLine = inputLine;
				out.println(outputLine);
				if (outputLine.equals("Bye.")){
					break;					
				}
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			// Cierro la entrada y salida
			out.close();
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			log.info("Clientes activos: "+ (taskExecutor.getThreadPoolExecutor().getActiveCount()-1));			
		}
	}

	public void setName(String name) {
		this.name = name;
	}

	public Socket getClientSocket() {
		return clientSocket;
	}

	public void setClientSocket(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}
	
	private ThreadPoolTaskExecutor taskExecutor;

	public ThreadPoolTaskExecutor getTaskExecutor() {
		return taskExecutor;
	}

	public void setTaskExecutor(ThreadPoolTaskExecutor taskExecutor) {
		this.taskExecutor = taskExecutor;
	}
	
}
