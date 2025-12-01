import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProfessorDAO {

    public void inserir(Professor professor){
        String sql = "INSERT INTO professor (nome, cpf, estado, cidade, estado_civil, salario) VALUES (?, ?, ?, ?, ?, ?)";

        try(Connection conn = Conexao.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setString(1, professor.getNome());
            stmt.setString(2, professor.getCpf());
            stmt.setString(3, professor.getEstado());
            stmt.setString(4, professor.getCidade());
            stmt.setString(5, professor.getEstado_civil());
            stmt.setDouble(6, professor.getSalario());

            stmt.executeUpdate();
            System.out.println("Professor cadastrado com sucesso!");

        } catch (Exception e){
            System.out.println("Erro ao inserir: " + e.getMessage());
        }
    }

    public List<Professor> listar(){
        List<Professor> lista = new ArrayList<>();
        String sql = "SELECT * FROM professor";

        try(Connection conn = Conexao.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                Professor p = new Professor(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getString("estado"),
                        rs.getString("cidade"),
                        rs.getString("estado_civil"),
                        rs.getDouble("salario")
                );
                lista.add(p);
            }

        } catch (Exception e){
            System.out.println("Erro ao listar: " + e.getMessage());
        }

        return lista;
    }

    public void atualizar(Professor professor){
        String sql = "UPDATE professor SET nome = ? WHERE id = ?";

        try(Connection conn = Conexao.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setString(1, professor.getNome());
            stmt.setInt(2, professor.getId());

            stmt.executeUpdate();
            System.out.println("Professor atualizado!");

        } catch (Exception e) {
            System.out.println("Erro ao atualizar: " + e.getMessage());
        }
    }

    public void deletar(int id){
        String sql = "DELETE FROM professor WHERE id = ?";

        try(Connection conn = Conexao.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setInt(1, id);
            stmt.executeUpdate();

            System.out.println("Professor deletado!");

        } catch (Exception e) {
            System.out.println("Erro ao deletar: " + e.getMessage());
        }
    }
}
