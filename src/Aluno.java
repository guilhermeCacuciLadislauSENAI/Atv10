/**
 * Entidade Aluno (Model).
 * Armazena os dados do aluno e segue o princípio do Encapsulamento.
 */
public class Aluno {
    // Atributos privados
    private int id; // Chave primária
    private String nome;
    private String email;

    /**
     * Construtor para INSERÇÃO (CREATE), onde o ID ainda não existe.
     */
    public Aluno(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

    /**
     * Construtor para LEITURA (READ) ou ATUALIZAÇÃO (UPDATE), onde o ID já é conhecido.
     */
    public Aluno(int id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
    }

    // Métodos Getters (Acessores)
    public int getId() {
        return id;
    }
    public String getNome() {
        return nome;
    }
    public String getEmail() {
        return email;
    }

    // Métodos Setters (Modificadores)
    public void setId(int id) {
        this.id = id;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}