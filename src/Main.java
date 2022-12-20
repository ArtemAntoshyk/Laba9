import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
       try{
           String url = "jdbc:mysql://localhost:3306/films";
           String username = "root";
           String password = "43206283";
           Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
           String sqlCommand = "CREATE TABLE film ( Id INT PRIMARY KEY AUTO_INCREMENT, name_film VARCHAR(30), year_prodaction INT)"; //створення
           String sqlCommand1 = "INSERT film(name_film, year_prodaction) VALUES ('Cruella', 2020)," +
                   "('Home Alone', 2004)," +                                                                                         //Внесення даних
                   "('Furious', 2017)," +
                   "('Sniper: White raven', 2022)";
           String sqlCommand2 = "UPDATE film SET year_prodaction = 2019 WHERE Id = 3" ;
           String sqlCommand3 = "DELETE FROM film WHERE Id > 3";
           try (Connection connection = DriverManager.getConnection(url, username,password)){
               Statement statement = connection.createStatement();
//               statement.executeUpdate(sqlCommand3);
               ResultSet resultSet = statement.executeQuery("SELECT * FROM film");
               while(resultSet.next()) {

                   int id = resultSet.getInt("Id");
                   String name_film = resultSet.getString("name_film");
                   int year_prodaction = resultSet.getInt("year_prodaction");
                   System.out.printf("%d. %s - %d \n", id, name_film, year_prodaction);
               }



               System.out.print("Input name film: ");
               String name_film = scanner.nextLine();

               System.out.print("Input year production: ");
               int year_production = scanner.nextInt();

               String sql = "INSERT INTO film (name_film, year_prodaction) Values (?, ?)";
               PreparedStatement preparedStatement = connection.prepareStatement(sql);
               preparedStatement.setString(1, name_film);
               preparedStatement.setInt(2, year_production);
               preparedStatement.executeUpdate();

           } catch (SQLException e) {
               throw new RuntimeException(e);
           }
       } catch (ClassNotFoundException e) {
           throw new RuntimeException(e);
       } catch (InvocationTargetException e) {
           throw new RuntimeException(e);
       } catch (InstantiationException e) {
           throw new RuntimeException(e);
       } catch (IllegalAccessException e) {
           throw new RuntimeException(e);
       } catch (NoSuchMethodException e) {
           throw new RuntimeException(e);
       }
    }
}