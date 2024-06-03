package dato;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class data {
    public static void main(String[] args) {
        // Configuración de la conexión a la base de datos
        String url = "jdbc:postgresql://localhost:5432/productos";
        String usuario = "postgres";
        String contraseña = "1234";

        // Corrida de 10 aplicaciones
        for (int i = 0; i < 100000; i++) {
            // Creación de una conexión a la base de datos
            Connection conn = null;
            try {
                conn = DriverManager.getConnection(url, usuario, contraseña);
                System.out.println("Conexion " + (i + 1) + " hecha");
            } catch (Exception e) {
                System.out.println("Error al conectarse a la base de datos: " + e.getMessage());
                return;
            }

            // Creación de un Statement para ejecutar la sentencia SQL
            Statement stmt = null;
            try {
                stmt = conn.createStatement();
            } catch (Exception e) {
                System.out.println("Error al crear el Statement: " + e.getMessage());
                return;
            }

            // Ejecución de la sentencia SQL
            try {
                ResultSet rs = stmt.executeQuery("SELECT * FROM prod");
                while (rs.next()) {
                    System.out.println("Registro encontrado: " + rs.getString(1) + ", " + rs.getString(2));
                }
            } catch (Exception e) {
                System.out.println("Error al ejecutar la sentencia SQL: " + e.getMessage());
            } finally {
                // Cierre del Statement
                try {
                    stmt.close();
                } catch (Exception e) {
                    System.out.println("Error al cerrar el Statement: " + e.getMessage());
                }

                // Cierre de la conexión
                try {
                    conn.close();
                } catch (Exception e) {
                    System.out.println("Error al cerrar la conexión: " + e.getMessage());
                }
            }
        }
    }
}