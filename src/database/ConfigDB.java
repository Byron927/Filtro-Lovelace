package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConfigDB {
    public static Connection objConnection = null;

    public static Connection openConnection() {
        try {
            //Llamamos el driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            //Creamos las variables de conexión
            String url = "jdbc:mysql://bgegcxr0y7m1bzrbnobt-mysql.services.clever-cloud.com/bgegcxr0y7m1bzrbnobt";
            String user = "ujs8numcsjh6ck8s";
            String password = "XJzfMivA5cPUyUB4JhLt";

            //Establecer la conexión
            objConnection = (Connection) DriverManager.getConnection(url, user, password);
            System.out.println("Me conecté perfectamente!!!!");

        } catch (ClassNotFoundException error) {
            System.out.println("ERROR >> Driver no Instalado " + error.getMessage());
        } catch (SQLException error) {
            System.out.println("ERROR >> error al conectar con la base de datos" + error.getMessage());
        }

        return objConnection;
    }

    public static void closeConnection() {
        try {
            //Si hay una conexión activa entonces la cierra
            if (objConnection != null) {
                objConnection.close();
                System.out.println("Se finalizó la conexión con éxito");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
