/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CodigoWS;


import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

import java.io.*; 
import java.sql.*;
import javax.swing.JOptionPane;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Alexander
 */
@WebService(serviceName = "WebServiceDemo")
public class WebServiceDemo {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }
    
    private int id;
    private String cedula;
    private String nombre;
    private String apellido;
    private String direccion;
    private String sexo;

// funciones que usara el Servicio Web para realizar los diferentes 
// procesos con los datos
// Dichas funciones se explican a continuación.

    @WebMethod
    public boolean insertarUsuario(String ce, String no, String ap, String dir, String se)
    {
            String conexionBD = "jdbc:mysql://127.0.0.1/wsdemo";
                Connection conexion = null;

                    boolean funciono = false;

                         String con;

            try{

            Class.forName("com.mysql.jdbc.Driver");
            conexion=DriverManager.getConnection(conexionBD, "root","prueba"); 
            Statement s = conexion.createStatement();

            con= "INSERT INTO USUARIOS VALUES (NULL,'" + ce + "','" + no + "','" + 
                                               ap + "','" + dir + "','" + se + "')";
            s.executeUpdate(con);

            funciono = true;
             }
            catch(Exception e)
            {
            System.out.println("No se ha completado la petición...");
            }

            return funciono; 
            
    }
    
            // NO EXISTIA ESTE METODO EN LA GUIA TUVE QUE IMPLEMENTARLO ALEXANDER TACK
          /*  @WebMethod
                 public int MostrarId()
                    {
                         int Id;
                             Id = Integer.parseInt("");
                                 Id = id;

                     return Id;
 
                    }*/
    
    
    
    
             @WebMethod
                 public String MostrarNombre()
                    {
                         String nomb;
                             nomb = "";
                                 nomb = nombre;

                     return nomb;
 
                    }

             @WebMethod
                  public String MostrarApellido()
                    {
                        String apell;
                             apell = "";
                                apell = apellido;

                        return apell;

                    }

             @WebMethod
                public String MostrarCedula()
                {
                        String codi;

                             codi = "";
                                codi = cedula;

                          return codi;

                }


             @WebMethod
               public String MostrarDireccion()
               {
                         String dire;

                                dire = "";
                                    dire = direccion;
                             return dire;
                }
               

             @WebMethod
                public String MostrarSexo()
                {
                            String se;

                                 se = "";
                                     se = sexo;
                                 return se;
                 }

    
                
              @WebMethod
                    public boolean buscarUsuarioCedula(String ced)
                    {
                        String conexionBD = "jdbc:mysql://127.0.0.1/wsdemo";
                            Connection conexion = null;

                            boolean funciono = false;

                            String con;
                            ResultSet rs;

                            try
                            {
                            Class.forName("com.mysql.jdbc.Driver");//el driver de mysql
                            conexion=DriverManager.getConnection(conexionBD, "root","prueba"); 
                            Statement s = conexion.createStatement();

                            con="SELECT * FROM usuarios where Cedula = '" + ced + "'" ;
                            rs = s.executeQuery (con); 



                            {
                            while (rs.next()) 
                            {
                           // id = rs.getInt("Id");
                            cedula = rs.getString("cedula");
                            nombre = rs.getString("nombre");
                            apellido = rs.getString("apellido");
                            direccion = rs.getString("direccion");
                            sexo = rs.getString("sexo");

                            funciono=true;
                            
                           // MostrarId();
                            MostrarCedula();
                            MostrarNombre();
                            MostrarApellido();
                            MostrarDireccion();
                            MostrarSexo();

                            break;

                            }
                            }
                            }
                            catch(Exception e)
                            {
                            System.out.println("No se ha completado la petición...");
                            }

                            return funciono;

                     }
                            
                    
 //METODO ACTUALIZAR USUARIO                   
                    @WebMethod
                            public boolean actualizarUsuario(String ced, String no, String ap, String dir, String se){
                            String conexionBD="jdbc:mysql://127.0.0.1/wsdemo";

                            Connection conexion = null;
                            boolean funciono = false;
                            String con;

                            try
                            {
                            Class.forName("com.mysql.jdbc.Driver");
                            conexion = DriverManager.getConnection(conexionBD, "root","prueba"); 
                            Statement s = conexion.createStatement();

                            con = "update usuarios set nombre = '" + no + "', apellido = '" + ap + "', dirección = '" + dir + "', sexo = '" + se + "' where cedula = '" + ced + "'";

                            s.executeUpdate(con);

                            funciono = true;
                             }
                            catch(Exception e)
                            {
                            System.out.println("No se ha completado la peticiÃ³n...");
                            }
                             return funciono; 
                            }

                        
                        @WebMethod
                            public boolean eliminarUsuario(String ced)
                            {
                            String conexionBD="jdbc:mysql://127.0.0.1/wsdemo";

                            Connection conexion = null;
                            boolean funciono = false;

                            String con;

                            try
                            {
                            Class.forName("com.mysql.jdbc.Driver");
                            conexion=DriverManager.getConnection(conexionBD, "root","12345"); 
                            Statement s = conexion.createStatement();
                            con = "delete from usuarios where cedula='"+ced+"'";
                            s.executeUpdate(con);

                            funciono=true;

                            }
                            catch(Exception e)
                            {
                            System.out.println("No se ha completado la peticiÃ³n...");
                            }

                            return funciono;
 
                            }
                            
                            
                            @WebMethod
                                    public List<clsPersona> buscarUsuarios()  
                                    {
                                    List<clsPersona> personaList = new ArrayList<clsPersona>();

                                    String conexionBD = "jdbc:mysql://127.0.0.1/wsdemo";
                                    Connection conexion = null;
                                    String con;
                                    ResultSet rs;

                                    try{
                                    Class.forName("com.mysql.jdbc.Driver"); 
                                    conexion = DriverManager.getConnection(conexionBD, "root","prueba"); 
                                    Statement s = conexion.createStatement();

                                    con = "SELECT * FROM usuarios" ;
                                    rs = s.executeQuery (con); 
                                    while (rs.next()) 
                                    {
                                    // Inicio del Whie
                                        id =  rs.getInt("id");
                                        cedula = rs.getString("cedula");
                                        nombre = rs.getString("nombre");
                                        apellido = rs.getString("apellido");
                                        direccion = rs.getString("direccion");
                                        sexo = rs.getString("sexo");
                                        
                                        personaList.add(new clsPersona(id, cedula, nombre, apellido,direccion, sexo)); 
                                    // Fin del Whie
                                    }
                                    }
                                    catch(Exception e)
                                    {
                                        personaList.add(new clsPersona());
                                        System.out.println("No se ha completado la petición...");
                                    }

                                    return personaList;

                                    }


                        
                    


    
}
