/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pildoras.conexionhibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Pedro
 */
public class GuardaClientePrueba {

    public static void main(String[] args) {

        System.out.println("Directorio de trabajo: " + System.getProperty("user.dir"));

        // Pasos:
        // 1
        // Crear objeto SessionFactory para que lea el archivo de configuración xml que sea capaz de construir un sessionfactory
        // Se indica la clase con la que va a trabajar y por último se construye
        SessionFactory miSessionFactory = (SessionFactory) new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Cliente.class).buildSessionFactory();
        // 2
        // Crear objeto Session
        Session miSession = miSessionFactory.openSession();

        //3
        // Crear objeto cliente dentro de try/finally
        try {
            // *** GRABAR DATOS ***
            Cliente cliente1 = new Cliente("Perico", "de los Palotes", "Calle Mercurio, Teruel");
            // Comenzar transacción a la base de datos
            // Una transacción es un conjunto de sentencias SQL que se ejecutan únicamente si todas pueden completarse sin errores. 
            // En caso de que alguna de las sentencias no pueda llevarse a cabo, no se realizará ningún cambio.
            miSession.beginTransaction();
            // guardar datos de cliente en la base de datos.
            // Gracias a Hibernate y a las anotaciones de la clase, no hay que escribir complejas líneas de SQL para guardar los datos
            miSession.save(cliente1);
            // Por último, confirmar la modificación de la bbdd con un commit
            miSession.getTransaction().commit();

            System.out.println("Registro Guardado");
            // *** FIN GRABAR DATOS ***

            // *** LECTURA DATOS ***
            miSession.beginTransaction();
            System.out.println("Lectura del registro con ID: " + cliente1.getID());
            
            Cliente clienteInsertado = miSession.get(Cliente.class,  cliente1.getID());
            
            System.out.println("Registro: " + clienteInsertado);
            
             miSession.getTransaction().commit();
             
             System.out.println("Terminado!");
            
           

            // cerrar sesion
            miSession.close();

        } finally {
            // tanto si se realiza transacción como si no, cerrar sessionfactory
            miSessionFactory.close();
        }

    }

}
