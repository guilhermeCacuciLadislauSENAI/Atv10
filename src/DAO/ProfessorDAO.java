package DAO;

import Util.Conexao;
import Model.Professor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProfessorDAO {

    // ---------------------------------------------------------
    // MÉTODO 1 — Verificar se email já existe
    // ---------------------------------------------------------
    public boolean emailExiste(String email) {

        String sql = "SELECT id FROM professores WHERE email = ? LIMIT 1";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);

            ResultSet rs = stmt.executeQuery();
            return rs.next(); // Email encontrado

        } catch (Exception e) {
            System.out.println("Erro ao verificar email (professor): " + e.getMessage());
            return false;
        }
    }

    // ---------------------------------------------------------
    // MÉTODO 2 —Cadastrar com verificação
    // ---------------------------------------------------------
    public boolean cadastrar(Professor professor) {

        if (emailExiste(professor.getEmail())) {
            System.out.println("Erro: E-mail já está cadastrado!");
            return false;
        }

        return inserir(professor);
    }

    // ---------------------------------------------------------
    // MÉTODO 3 — Inserir professor (CREATE)
    // ---------------------------------------------------------
    private boolean inserir(Professor professor) {

        String sql = "INSERT INTO professores (nome, email, senha) VALUES (?, ?, ?)";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, professor.getNome());
            stmt.setString(2, professor.getEmail());
            stmt.setString(3, professor.getSenha());

            stmt.executeUpdate();
            return true;

        } catch (Exception e) {
            System.out.println("Erro ao inserir professor: " + e.getMessage());
            return false;
        }
    }

    // ---------------------------------------------------------
    // MÉTODO 4 — LISTAR TODOS
    // ---------------------------------------------------------
    public List<Professor> listar() {

        List<Professor> lista = new ArrayList<>();
        String sql = "SELECT * FROM professores";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                lista.add(new Professor(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("senha"),
                        rs.getString("cpf"),
                        rs.getString("estado"),
                        rs.getString("cidade"),
                        rs.getString("estado_civil"),
                        rs.getDouble("salario")
                ));
            }

        } catch (Exception e) {
            System.out.println("Erro ao listar professores: " + e.getMessage());
        }

        return lista;
    }

    // ---------------------------------------------------------
    // MÉTODO 5 — BUSCAR POR ID
    // ---------------------------------------------------------
    public Professor buscarPorId(int id) {

        String sql = "SELECT * FROM professores WHERE id = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    return new Professor(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getString("email"),
                            rs.getString("senha"),
                            rs.getString("cpf"),
                            rs.getString("estado"),
                            rs.getString("cidade"),
                            rs.getString("estado_civil"),
                            rs.getDouble("salario")
                    );
                }
            }

        } catch (Exception e) {
            System.out.println("Erro ao buscar professor: " + e.getMessage());
        }

        return null;
    }

    // ---------------------------------------------------------
    // MÉTODO 6 — ATUALIZAR PROFESSOR
    // ---------------------------------------------------------
    public boolean atualizar(Professor professor) {

        String sql = "UPDATE professores SET nome = ?, email = ?, senha = ? WHERE id = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, professor.getNome());
            stmt.setString(2, professor.getEmail());
            stmt.setString(3, professor.getSenha());
            stmt.setInt(4, professor.getId());

            stmt.executeUpdate();
            return true;

        } catch (Exception e) {
            System.out.println("Erro ao atualizar professor: " + e.getMessage());
            return false;
        }
    }

    // ---------------------------------------------------------
    // MÉTODO 7 — DELETAR PROFESSOR
    // ---------------------------------------------------------
    public boolean deletar(int id) {

        String sql = "DELETE FROM professores WHERE id = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;

        } catch (Exception e) {
            System.out.println("Erro ao deletar professor: " + e.getMessage());
            return false;
        }
    }

    // ---------------------------------------------------------
    // MÉTODO 8 — LOGIN (email + senha)
    // ---------------------------------------------------------
    public Professor login(String email, String senha) {

        String sql = "SELECT * FROM professores WHERE email = ? AND senha = ? LIMIT 1";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            stmt.setString(2, senha);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Professor(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("senha"),
                        rs.getString("cpf"),
                        rs.getString("estado"),
                        rs.getString("cidade"),
                        rs.getString("estado_civil"),
                        rs.getDouble("salario")
                );
            }

        } catch (Exception e) {
            System.out.println("Erro no login (professor): " + e.getMessage());
        }

        return null;
    }
}
