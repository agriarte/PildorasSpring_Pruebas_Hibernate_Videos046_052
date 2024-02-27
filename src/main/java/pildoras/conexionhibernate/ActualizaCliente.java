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
public class ActualizaCliente {

    private static int filasAfectadas;

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

        try {
            // *** UPDATE de maneas distintas: ***
            // *** 1- por getter ***
            // id del cliente que queremos modificar
            int ClienteID = 1;

            miSession.beginTransaction();

            // crea un objeto tipo Cliente del registro indicado en ClienteID
            Cliente miCliente = miSession.get(Cliente.class, ClienteID);

            if (miCliente != null) {
                // Actualiza el registro con el setter correspondiente
                miCliente.setNombre("Antonio");

                // Por último, confirmar la modificación de la bbdd con un commit
                miSession.getTransaction().commit();

                System.out.println("Registro Actualizado por getter!");
            } else {
                // En caso de que no se encuentre el registro con el ID proporcionado
                System.out.println("No se encontró el registro con el ID: " + ClienteID);
            }
            // *fin del método 1 por getter *

            // *** 2- por HQL ***
            miSession.beginTransaction();
            // modificación con fines didácticos.
            // Actualiza todos los registros de la tabla Cliente con el nombre Arturo si el nombre empieza por la letra P
            // % es el comodín
            //miSession.createQuery("update Cliente set Nombre='Cinto' where Nombre LIKE 'Art%'").executeUpdate();
            filasAfectadas = miSession.createQuery("update Cliente set nombre='Juan' where nombre like :prefix")
                    .setParameter("prefix", "Juan%")
                    .executeUpdate();
            miSession.getTransaction().commit();
            if (filasAfectadas > 0) {
                System.out.println("Registro Actualizado por HQL");
            } else {
                System.out.println("No se actualizaron registros por HQL");
            }
            // *fin de método 2 por HQL *

            // *** borrado por HQL ***
            miSession.beginTransaction();

            //miSession.createQuery("update Cliente set Nombre='Cinto' where Nombre LIKE 'Art%'").executeUpdate();
            filasAfectadas = miSession.createQuery("delete from Cliente where direccion like :prefix")
                    .setParameter("prefix", "Ingla%")
                    .executeUpdate();
            miSession.getTransaction().commit();
            if (filasAfectadas > 0) {
                System.out.println("Se eliminaron registros por HQL");
            } else {
                System.out.println("No se eliminaron registros por HQL");
            }
            // *fin de método 2 por HQL *

            // cerrar sesion
            miSession.close();

        } finally {
            // tanto si se realiza transacción como si no, cerrar sessionfactory
            miSessionFactory.close();
        }

    }

}
