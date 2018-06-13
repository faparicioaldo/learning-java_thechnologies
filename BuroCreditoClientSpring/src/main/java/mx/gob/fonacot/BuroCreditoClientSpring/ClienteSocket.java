package mx.gob.fonacot.BuroCreditoClientSpring;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class ClienteSocket {

	/**
	 * Atributo para el manejo del Logger
	 */
	static Logger log = Logger.getLogger(ClienteSocket.class);
	private Socket clientSocket;

	public void init() {
		try {
			clientSocket = new Socket("localhost", 25100);
			log.info("Creando Socket Cliente");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Preparo mi entrada y salida
		PrintWriter out = null;
		try {
			log.info("Creando Salida");
			out = new PrintWriter(clientSocket.getOutputStream(), true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedReader in = null;
		log.info("Creando Entrada");
		try {
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		String inputLine, outputLine, fromServer, fromUser;
		try {
			if ((fromServer = in.readLine()) != null) {
				System.out.println("1 Server: " + fromServer);
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		log.info("Escribiendo");
		outputLine = "Who's there?";
		out.println(outputLine);
		try {
			if ((fromServer = in.readLine()) != null) {
				System.out.println("1 Server: " + fromServer);
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		outputLine = "Turnip who?";
		out.println(outputLine);
		try {
			if ((fromServer = in.readLine()) != null) {
				System.out.println("2 Server: " + fromServer);
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		outputLine = "n";
		out.println(outputLine);
		try {
			if ((fromServer = in.readLine()) != null) {
				System.out.println("3 Server: " + fromServer);
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			log.info("Cerrando salida y entrada");
			// Cierro la entrada y salida
			out.close();
			in.close();
			// Cierro mi socket de cliente y de Server
			log.info("Cerrando cliente Socket");
			clientSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void espera(int esperaMillisegundos) {
		try {
			Thread.sleep(esperaMillisegundos);
		} catch (InterruptedException e) {
			log.error("ERROR en el metodo espera() InterruptedException");
		}
	}
}
