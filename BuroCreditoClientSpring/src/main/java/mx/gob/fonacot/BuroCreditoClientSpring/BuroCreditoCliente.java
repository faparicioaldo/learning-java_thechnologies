package mx.gob.fonacot.BuroCreditoClientSpring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;



/**
 * Hello world!
 *
 */
public class BuroCreditoCliente 
{
    public static void main( String[] args )
    {
    	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DIConfiguration.class);
    	ClienteSocket cliente = context.getBean(ClienteSocket.class);
    	cliente.init();
		//close the context
		context.close();
    }
}
