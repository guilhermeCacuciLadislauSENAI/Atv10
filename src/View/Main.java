package View;

import DAO.AlunoDAO;
import DAO.ProfessorDAO;
import Model.Aluno;
import Model.Professor;
import Util.Sessao;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        AlunoDAO alunoDAO = new AlunoDAO();
        ProfessorDAO professorDAO = new ProfessorDAO();

        while (true) {

            System.out.println("\n=== SISTEMA ESCOLA ===\n");

            if (!Sessao.estaLogado()) {
                System.out.println("1) Login Professor");
                System.out.println("2) Cadastrar Professor");
                System.out.println("0) Sair");
                System.out.print("\nEscolha: ");
                int op = sc.nextInt();
                sc.nextLine();

                if (op == 1){
                    System.out.print("Email: ");
                    String email = sc.nextLine();

                    System.out.print("Senha: ");
                    String senha = sc.nextLine();

                    Professor prof = professorDAO.login(email, senha);

                    if (prof != null){
                        Sessao.login(prof);
                        System.out.println("Professor logado: " + prof.getNome());
                    } else {
                        System.out.println("Login inválido!");
                    }

                }
                else if (op == 2) {
                    System.out.println("\n=== CADASTRAR PROFESSOR ===");

                    System.out.print("Nome: ");
                    String nome = sc.nextLine();

                    System.out.print("Email: ");
                    String email = sc.nextLine();

                    System.out.print("Senha: ");
                    String senha = sc.nextLine();

                    System.out.print("CPF: ");
                    String cpf = sc.nextLine();

                    System.out.print("Estado: ");
                    String estado = sc.nextLine();

                    System.out.print("Cidade: ");
                    String cidade = sc.nextLine();

                    System.out.print("Estado Civil: ");
                    String estadoCivil = sc.nextLine();

                    System.out.print("Salário: ");
                    double salario = sc.nextDouble();
                    sc.nextLine();

                    Professor novoProf = new Professor(
                            nome, email, senha, cpf, estado, cidade, estadoCivil, salario
                    );

                    if (professorDAO.cadastrar(novoProf)) {
                        System.out.println("Professor cadastrado com sucesso!");
                    } else {
                        System.out.println("Erro ao cadastrar professor.");
                    }
                }
                else if (op == 0) {
                    System.out.println("Saindo...");
                    sc.close();
                    System.exit(0);
                }

                continue;
            }


            // MENU APÓS LOGIN
            System.out.println("\nProfessor Logado: " + Sessao.getProfessor().getNome());
            System.out.println("\n1) Cadastrar aluno");
            System.out.println("2) Listar alunos");
            System.out.println("3) Buscar aluno por ID");
            System.out.println("4) Atualizar aluno");
            System.out.println("5) Deletar aluno");
            System.out.println("6) Logout");
            System.out.println("0) Sair");

            System.out.print("\nEscolha: ");
            int op = sc.nextInt();
            sc.nextLine();

            switch (op){

                case 1:
                    if (!Sessao.estaLogado()){break;}

                    System.out.print("Nome: ");
                    String nome = sc.nextLine();

                    System.out.print("Email: ");
                    String email = sc.nextLine();

                    System.out.print("Senha: ");
                    String senha = sc.nextLine();

                    Aluno novo = new Aluno(nome, email, senha);

                    if (alunoDAO.cadastrar(novo))
                        System.out.println("Aluno cadastrado!");
                    else
                        System.out.println("Falha ao cadastrar.");
                    break;

                case 2:
                    if (!Sessao.estaLogado()){break;}

                    System.out.println("\n--- LISTA DE ALUNOS ---");
                    for (Aluno a : alunoDAO.listar()) {
                        System.out.println(a.getId() + " | " + a.getNome() + " | " + a.getEmail());
                    }
                    break;

                case 3:
                    if (!Sessao.estaLogado()){break;}

                    System.out.print("ID: ");
                    int idBusca = sc.nextInt();

                    Aluno encontrado = alunoDAO.buscarPorId(idBusca);

                    if (encontrado != null)
                        System.out.println(encontrado.getId() + " - " + encontrado.getNome());
                    else
                        System.out.println("Não encontrado.");
                    break;

                case 4:
                    if (!Sessao.estaLogado()){break;}

                    System.out.print("ID: ");
                    int idUp = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Novo nome: ");
                    String nomeUp = sc.nextLine();

                    System.out.print("Novo email: ");
                    String emailUp = sc.nextLine();

                    System.out.print("Nova senha: ");
                    String senhaUp = sc.nextLine();

                    Aluno alt = new Aluno(idUp, nomeUp, emailUp, senhaUp);

                    if (alunoDAO.atualizar(alt))
                        System.out.println("Atualizado!");
                    else
                        System.out.println("Erro ao atualizar.");
                    break;

                case 5:
                    if (!Sessao.estaLogado()){break;}

                    System.out.print("ID: ");
                    int idDel = sc.nextInt();

                    if (alunoDAO.deletar(idDel))
                        System.out.println("Deletado!");
                    else
                        System.out.println("Erro ao deletar.");
                    break;

                case 6:
                    Sessao.logout();
                    System.out.println("Logout realizado.");
                    break;

                case 0:
                    System.out.println("Encerrando...");
                    sc.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Opção inválida.");
            }

            System.out.println("\nPressione ENTER para continuar...");
            sc.nextLine();
        }
    }
}
