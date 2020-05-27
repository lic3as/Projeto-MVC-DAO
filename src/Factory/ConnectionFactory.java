package Factory;

import java.sql.*;

public class ConnectionFactory {
      public Connection getConnection() {
          try {
              return DriverManager.getConnection("jdbc:mysql://localhost:3306/?user=root");
              
          }
          catch(SQLException excecao){
              throw new RuntimeException(excecao);
          }
      }
}