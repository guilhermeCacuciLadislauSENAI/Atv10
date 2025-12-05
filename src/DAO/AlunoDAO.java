package DAO;

import Model.Aluno;
import Util.Conexao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe de Acesso a Dados (DAO) para a entidade Model.Aluno.
 * Implementa todas as operações CRUD (Create, Read, Update, Delete).
 */
public class AlunoDAO {

    /**
     * MÉTODO 1 - VERIFICAR SE EMAIL EXISTE
     */
    public boolean emailExiste (String email){
        String sql = "SELECT id FROM alunos WHERE email = ? LIMIT 1";

        try (Connection conn = Conexao.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        }catch(Exception e){
            System.out.println("Erro ao verificar o email: " + e.getMessage());
            return false;
        }
    }

    /**
     * MÉTODO 2 - CADASTRAR COM VERIFICAÇÃO
     */
    public boolean cadastrar(Aluno aluno){
        if(emailExiste((aluno.getEmail()))){
            System.out.println("Erro: Email já está cadastrado!");
            return false;
        }
        return inserir(aluno);
    }

    /**
     * MÉTODO 3 - CREATE: Insere um novo aluno no BD.
     */
    public boolean inserir(Aluno aluno) {
        // Query SQL usa '?' (placeholders) para segurança (PreparedStatement).
        String sql = "INSERT INTO alunos (nome, email, senha) VALUES (?, ?, ?)";

        // try-with-resources: Garante o fechamento automático da Conexão e do PreparedStatement.
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Mapeamento do objeto Model.Aluno para a Query SQL (1=nome, 2=email).
            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getEmail());
            stmt.setString(3, aluno.getSenha());

            // Executa a instrução SQL de modificação (INSERT).
            stmt.executeUpdate();
            System.out.println("Model.Aluno inserido com sucesso!");
            return true;

        } catch (Exception e) {
            System.out.println("Erro ao inserir: " + e.getMessage());
            return false;
        }
    }


    /**
     * MÉTODO 4 - READ ALL: Lista todos os alunos do BD.
     */
    public List<Aluno> listar() {
        List<Aluno> lista = new ArrayList<>();
        String sql = "SELECT * FROM alunos";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             // executeQuery() retorna o ResultSet (conjunto de resultados).
             ResultSet rs = stmt.executeQuery()) {

            // Itera sobre cada linha retornada pelo banco de dados.
            while (rs.next()) {
                // Mapeamento reverso: Converte a linha do BD em um objeto Model.Aluno.
                Aluno a = new Aluno(
                        rs.getInt("id"), // Pega o valor da coluna 'id'
                        rs.getString("nome"), // Pega o valor da coluna 'nome'
                        rs.getString("email"), // Pega o valor da coluna 'email'
                        rs.getString("senha")
                );
                lista.add(a);
            }

        } catch (Exception e) {
            System.out.println("Erro ao listar: " + e.getMessage());
        }

        return lista;
    }

    /**
     * MÉTODO 5 - BUSCAR POR ID
     */
    public Aluno buscarPorId(int id){
        String sql = "SELECT * FROM alunos WHERE id = ?";

        try(Connection conn = Conexao.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setInt(1, id);

            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    return new Aluno(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getString("email"),
                            rs.getString("senha")
                    );
                }
            }

        }catch(Exception e){
            System.out.println("Erro ao buscar o aluno: " + e.getMessage());
        }

        return null;
    }


    /**
     * MÉTODO 6 - UPDATE: Atualiza os dados de um aluno existente pelo ID.
     */
    public boolean atualizar(Aluno aluno) {
        // A cláusula WHERE id = ? é essencial para garantir a atualização do registro correto.
        String sql = "UPDATE alunos SET nome = ?, email = ?, senha = ? WHERE id = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // 1. Novos valores para Nome e Email.
            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getEmail());
            stmt.setString(3, aluno.getSenha());

            // 2. ID do registro a ser atualizado (condição WHERE).
            stmt.setInt(4, aluno.getId());

            stmt.executeUpdate();
            System.out.println("Model.Aluno atualizado!");
            return true;

        } catch (Exception e) {
            System.out.println("Erro ao atualizar: " + e.getMessage());
            return false;
        }
    }

    /**
     * MÉTODO 7 - DELETE: Remove um registro do BD com base no ID.
     */
    public boolean deletar(int id) {
        String sql = "DELETE FROM alunos WHERE id = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Define o ID do aluno a ser deletado.
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Model.Aluno deletado!");

            return true;

        } catch (Exception e) {
            System.out.println("Erro ao deletar: " + e.getMessage());
            return false;
        }
    }

    /**
     * MÉTODO 8 - LOGIN
     */
    public boolean login (String email, String senha){
        String sql = "SELECT id FROM alunos WHERE email = ? AND senha = ? LIMIT 1";

        try(Connection conn = Conexao.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, email);
            stmt.setString(2, senha);

            ResultSet rs = stmt.executeQuery();
            return rs.next();
        }catch(Exception e){
            System.out.println("Erro no login: " + e.getMessage());
            return false;
        }
    }
}

