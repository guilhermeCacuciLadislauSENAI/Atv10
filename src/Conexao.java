import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Classe de Utilidade para Gerenciamento de Conexão.
 * Contém as credenciais e o método estático para obter a conexão JDBC.
 */
public class Conexao {

    // 1. Definição das constantes de conexão:
    private static final String URL = "jdbc:mysql://localhost:3306/escola"; // Endereço, porta e nome do banco.
    private static final String USER = "root"; // Usuário do banco.
    private static final String PASSWORD = ""; // Senha do banco.

    /**
     * Tenta estabelecer a conexão com o banco de dados.
     * @return Objeto Connection do JDBC.
     */
    public static Connection getConnection() {

        try {
            // Tenta estabelecer a conexão usando as credenciais definidas.
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            // Em caso de falha na conexão, lança uma exceção em tempo de execução.
            throw new RuntimeException("Erro ao conectar: " + e.getMessage());
        }
    }
}