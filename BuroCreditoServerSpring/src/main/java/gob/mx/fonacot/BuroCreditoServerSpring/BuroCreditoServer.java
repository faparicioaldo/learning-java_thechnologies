package gob.mx.fonacot.BuroCreditoServerSpring;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import gob.mx.fonacot.BuroCreditoServerSpring.configuration.DIConfiguration;
import gob.mx.fonacot.BuroCreditoServerSpring.thread.ServerRunnable;

/**
 * Clase para emular el Servidor de Buro de Crédito
 * 
 * @author grfloresb@ultrasist.com.mx
 * @since 11/06/2018
 *
 */
public class BuroCreditoServer {
	/**
	 * Atributo para el manejo del Log
	 */
	static Logger log = Logger.getLogger(BuroCreditoServer.class);

	/**
	 * Metodo principal del proyecto
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// Inicialización del contexto Spring con Annotaciones
		ApplicationContext context = new AnnotationConfigApplicationContext(DIConfiguration.class);
		// Manejador de los threads
		ThreadPoolTaskExecutor taskExecutor = (ThreadPoolTaskExecutor) context.getBean("taskExecutor");
		// SocketServer este socket siempre estará a la escucha
		ServerSocket serverSocket = null;
		// Cuando Socket Server escuche una petición la manejara en este socket
		Socket clientSocket;
		// Puerto de escucha
		int puerto = 25100;
		try {
			// Inicializo mi socket de eschua
			serverSocket = new ServerSocket(puerto);
			log.info("Inicializando socket de escucha en puerto: " + puerto);
		} catch (IOException e) {
			log.error("No puedo escuchar en el puerto: 25100. Tal vez este ocupado por otro proceso");
		}
		// Mientras el ServerSocket este abierto
		while (!serverSocket.isClosed()) {
			try {
				// Proceso de escucha bloqueante
				clientSocket = serverSocket.accept();
				// obtengo la IP del cliente
				String hostCliente = clientSocket.getInetAddress().getHostAddress();
				log.info("Aceptando un cliente: " + hostCliente);
				// Genero un Thread para atender al cliente
				ServerRunnable thread = (ServerRunnable) context.getBean(ServerRunnable.class);
				// Le asigno el socket al thread
				thread.setClientSocket(clientSocket);
				thread.setTaskExecutor(taskExecutor);
				thread.setName("Thread del cliente:" + hostCliente);
				// Ejecuto mi thread
				taskExecutor.execute(thread);

				log.info("Clientes activos: "+ taskExecutor.getThreadPoolExecutor().getActiveCount());
//				log.info("Clientes activos: "+ taskExecutor.getThreadPoolExecutor().getCompletedTaskCount());

			} catch (IOException e) {
				log.error("ERROR No puedo aceptar clientes");
			} catch (Exception e2) {
				log.error("ERROR Excepcion no identificada");
			}
			// finally {
			// try {
			// if(serverSocket!= null) {
			// serverSocket.close();
			// }
			// if(taskExecutor!= null) {
			// taskExecutor.shutdown();
			// }
			// } catch (Exception e1) {
			// // TODO Auto-generated catch block
			// log.error("ERROR No puedo cerrar los sockets");
			// break;
			// }
			// }

		}
		log.fatal("FATAL Salida forzosa del bucle (while)");
	}
}
