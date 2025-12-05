package Model;

public class Professor {
    private int id;
    private String nome;
    private String email;
    private String senha;
    private String cpf;
    private String estado;
    private String cidade;
    private String estado_civil;
    private double salario;

    public Professor(String nome, String email, String senha,String cpf, String estado, String cidade, String estado_civil, double salario){
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
        this.estado = estado;
        this.cidade = cidade;
        this.estado_civil = estado_civil;
        this.salario = salario;
    }

    public Professor(int id, String nome, String email, String senha, String cpf, String estado, String cidade, String estado_civil, double salario){
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
        this.estado = estado;
        this.cidade = cidade;
        this.estado_civil = estado_civil;
        this.salario = salario;
    }

    // métodos getter
    public int getId(){return id;}
    public String getNome(){return nome;}
    public String getEmail(){return email;}
    public String getSenha(){return senha;}
    public String getCpf(){return cpf;}
    public String getEstado(){return estado;}
    public String getCidade(){return cidade;}
    public String getEstado_civil(){return estado_civil;}
    public double getSalario(){return salario;}

    // métodos setters
    public void setId(int id){this.id = id;}
    public void setNome(String nome){this.nome = nome;}
    public void setEmail(String email){this.email = email;}
    public void setSenha(String senha){this.senha = senha;}
    public void setCpf(String cpf){this.cpf = cpf;}
    public void setEstado(String estado){this.estado = estado;}
    public void setCidade(String cidade){this.cidade = cidade;}
    public void setEstado_civil(String estado_civil){this.estado_civil = estado_civil;}
    public void setSalario(double salario){this.salario = salario;}
}
