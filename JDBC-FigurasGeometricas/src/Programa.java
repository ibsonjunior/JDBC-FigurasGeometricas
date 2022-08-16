import java.sql.*;

public class Programa {

    private static final String sqlCreateTable = "DROP TABLE IF EXISTS figurasGeometricas; CREATE TABLE figurasGeometricas(id INT PRIMARY KEY, figura VARCHAR(32), cor VARCHAR(32))";

    private static final String sqlInsert1 = "INSERT INTO figurasGeometricas (id, figura, cor) VALUES (1, 'Circulo', 'Azul')";

    private static final String sqlInsert2 = "INSERT INTO figurasGeometricas (id, figura, cor) VALUES (2, 'Circulo', 'vermelho')";

    private static final String sqlInsert3= "INSERT INTO figurasGeometricas (id, figura, cor) VALUES (3, 'Quadrado', 'Amarelo')";

    private static final String sqlInsert4= "INSERT INTO figurasGeometricas (id, figura, cor) VALUES (4, 'Quadrado', 'Marrom')";

    private static final String sqlInsert5= "INSERT INTO figurasGeometricas (id, figura, cor) VALUES (5, 'Quadrado', 'Lilas')";

    private static final String sqlInsert6= "INSERT INTO figurasGeometricas (id, figura, cor) VALUES (6, 'Triangulo', 'rosa')";

    public static void main(String[] args) throws SQLException {
           Connection connection = null;
        try {
            System.out.println("Conexão iniciada");
            connection = conectarBD();

            Statement preparacao = conectarBD().createStatement();
            preparacao.execute(sqlCreateTable);
            preparacao.execute(sqlInsert1);
            preparacao.execute(sqlInsert2);
            preparacao.execute(sqlInsert3);
            preparacao.execute(sqlInsert4);
            preparacao.execute(sqlInsert5);
            preparacao.execute(sqlInsert6);
            listarTodos(connection);

        } catch (Exception e){

            e.printStackTrace();

        }finally {
            System.out.println("Conexão Encerrada...");
            connection.close();

        }
    }

    public static Connection conectarBD() throws Exception {
        Class.forName("org.h2.Driver");
        return DriverManager.getConnection("jdbc:h2:~/jdbc-FigurasGeo", "sa", "");
    }

    public static void listarTodos(Connection connection) throws SQLException {

        String sqlQuery = "SELECT * FROM figurasGeometricas WHERE figura ~* 'circulo' AND cor ~* 'vermelho'";

        Statement preparaQuery = connection.createStatement();

        ResultSet rs = preparaQuery.executeQuery(sqlQuery);


        while (rs.next()){
            System.out.println("ID: " + rs.getInt(1) + " - " + "Figura: " + rs.getString(2) + "Cor: " + rs.getString(3));
        }

    }


}
