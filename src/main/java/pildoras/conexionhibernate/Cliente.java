/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pildoras.conexionhibernate;

// Ojo al import. Aunque parezca contradictorio se importa este paquete y no el org.hibernate.anotations.*

import javax.persistence.*;



/**
 * Hibernate transforma cada clase en entidades para realizar el mapeo. @Entity
 * 1- Un atributo por cada columna de la tabla
 * 2- @Table: Se crean anotaciones para crear mapeo de class a tabla. 
 * 3- @Column: anotacion de las columnas. Name debe ser el nombre tal cual está escrito
 * 4- @Id es para indicar que esa columna es "clave" o Primary tal como se ve en phpMyAdmin. Le añade un icono de llave
 * 
 * 5- Deben crearse 2 constructores: una vacío y otro con todos los campos salvo el ID que es automático
 * 6- Getters/Setter de todos los campos, incluyendo ID porque se puede usar para búsquedas
 * 7- generar método toString para recibir datos en lenguaje humano.
 */
@Entity
@Table (name="clientes")
public class Cliente {
    
        /*
    @GeneratedValue(strategy = GenerationType.IDENTITY): El más indicado para mySQL. Especifica la estrategia de 
    generación de valores para la clave primaria. En este caso, se utiliza la identidad de la base de datos, lo 
    que significa que la base de datos generará automáticamente valores únicos. Si no se pone Hibernate no puede 
    obtener el valor del ID dando 0 al hacer System.out.println("Lectura del registro con ID: " + cliente1.getID());
    */
    
    @Id
    @GeneratedValue (strategy=GenerationType.IDENTITY)
    @Column(name="ID")
    private int ID;
    
    // según curso las anotaciones deben llamarse exactamente igual que las columnas(respetando las mayúsculas si las hay). Me funciona igual si no respeto
    
    @Column(name="Nombre")
    private String nombre;
    @Column(name="Apellidos")
    private String apellidos;
    @Column(name="Direccion")
    private String direccion;

    public Cliente() {
    }

    public Cliente(String nombre, String apellidos, String direccion) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.direccion = direccion;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "Clientes{" + "ID=" + ID + ", nombre=" + nombre + ", apellidos=" + apellidos + ", direccion=" + direccion + '}';
    }
    
    
    
}
