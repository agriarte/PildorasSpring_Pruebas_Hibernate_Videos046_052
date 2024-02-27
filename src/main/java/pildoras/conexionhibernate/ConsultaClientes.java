/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/*
 * Uso de JavaSE con Hibernate y JDBC. No hay ninguna dependencia con Spring.  
 */


package pildoras.conexionhibernate;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Pedro
 */
public class ConsultaClientes {

    public static void main(String[] args) {

        // Pasos:
        // 1
        // Crear objeto SessionFactory para que lea el archivo de configuración xml que sea capaz de construir un sessionfactory
        // Se indica la clase con la que va a trabajar y por último se construye
        SessionFactory miSessionFactory = (SessionFactory) new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Cliente.class).buildSessionFactory();
        // 2
        // Crear objeto Session
        Session miSession = miSessionFactory.openSession();

        try {
            //consulta 1. Dame todos los registros
            //comenzar sesión
            miSession.beginTransaction();
            //consulta de clientes
            //List<Cliente> listaClientes = miSession.createQuery("from Cliente").getResultList();
            List<Cliente> listaClientes = miSession.createQuery("FROM Cliente c").getResultList();
            //mostrar los Cliente
            for (Cliente unCliente : listaClientes) {
                System.out.println("ID: " + unCliente.getID() + " Nombre: " + unCliente.getNombre()
                        + " Apellidos: " + unCliente.getApellidos() + " Dirección: " + unCliente.getDireccion());
            }
            imprimirConsulta(listaClientes);
            
            // consulta 2. Dame los que se apelliden "Barret"
            // se utilizan sentencias en HQL. Se crea un álias de la clase cliente. No se está declarando otra 
            // instancia de la clase. 
            // El 'where' es idéntico al de SQL.
            // Para referirnos a las columnas no se usa el nombre usado en la base de datos. Se usa el 
            // nombre de la propiedad(atributo) en la clase Cliente
            listaClientes = miSession.createQuery("from Cliente cl where cl.apellidos='Barret'").getResultList();
            imprimirConsulta(listaClientes);
            
            // consulta 3. Dame los que se llamen Syd o dirección contenga la palabra 'Malaga'
            // atención a que tolera no poner acentos
            
            listaClientes = miSession.createQuery("from Cliente cl where cl.nombre='Carlos' " +
                    " or cl.direccion LIKE '%Malaga%'").getResultList();
            

            
            imprimirConsulta(listaClientes);
            // cerrar sesion
            miSession.close();
        } finally {
            // tanto si se realiza transacción como si no, cerrar sessionfactory
            miSessionFactory.close();
        }
    }
    
    private static void imprimirConsulta(List<Cliente> listaClientes){
        System.err.println("\nIMPRIMIR CONSULTA:\n");
        for (Cliente unCliente : listaClientes) {         
                System.err.println(unCliente);
            }
    }
}
