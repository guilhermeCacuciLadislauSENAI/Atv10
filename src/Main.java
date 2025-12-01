import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        AlunoDAO alunoDAO = new AlunoDAO();
        ProfessorDAO professorDAO = new ProfessorDAO();

        System.out.println("1 - Aluno");
        System.out.println("2 - Professor");
        int opcao = sc.nextInt();
        sc.nextLine(); // limpar buffer

        switch (opcao) {

            // =============================
            // ======== ALUNOS =============
            // =============================
            case 1:
                System.out.println("=== CADASTRO DE ALUNO ===");

                // CREATE
                System.out.print("Digite o nome do aluno: ");
                String nomeAluno = sc.nextLine();

                System.out.print("Digite o email do aluno: ");
                String emailAluno = sc.nextLine();

                Aluno novoAluno = new Aluno(nomeAluno, emailAluno);
                alunoDAO.inserir(novoAluno);

                // READ
                System.out.println("\n--- LISTA DE ALUNOS ---");
                for (Aluno a : alunoDAO.listar()) {
                    System.out.println(a.getId() + " - " + a.getNome() + " - " + a.getEmail());
                }

                // UPDATE
                System.out.println("\n=== ATUALIZAR UM ALUNO ===");
                System.out.print("Digite o ID do aluno que deseja atualizar: ");
                int idAtualizarAluno = sc.nextInt();
                sc.nextLine();

                System.out.print("Novo nome: ");
                String novoNomeAluno = sc.nextLine();

                System.out.print("Novo email: ");
                String novoEmailAluno = sc.nextLine();

                Aluno alunoAtualizado = new Aluno(idAtualizarAluno, novoNomeAluno, novoEmailAluno);
                alunoDAO.atualizar(alunoAtualizado);

                // DELETE
                System.out.println("\n=== DELETAR UM ALUNO ===");
                System.out.print("Digite o ID do aluno que deseja deletar: ");
                int idDeleteAluno = sc.nextInt();

                alunoDAO.deletar(idDeleteAluno);

                break;

            // =============================
            // ======= PROFESSORES =========
            // =============================
            case 2:
                System.out.println("=== CADASTRO DE PROFESSOR ===");

                // CREATE
                System.out.print("Digite o nome do professor: ");
                String nomeProf = sc.nextLine();

                System.out.print("Digite o CPF do professor: ");
                String cpf = sc.nextLine();

                System.out.print("Digite o estado do professor: ");
                String estado = sc.nextLine();

                System.out.print("Digite a cidade do professor: ");
                String cidade = sc.nextLine();

                System.out.print("Digite o estado civil do professor: ");
                String estadoCivil = sc.nextLine();

                System.out.print("Digite o salário do professor: ");
                double salario = sc.nextDouble();
                sc.nextLine(); // limpar buffer

                Professor novoProf = new Professor(nomeProf, cpf, estado, cidade, estadoCivil, salario);
                professorDAO.inserir(novoProf);

                // READ
                System.out.println("\n--- LISTA DE PROFESSORES ---");
                for (Professor p : professorDAO.listar()) {
                    System.out.println(
                            p.getId() + " - " + p.getNome() + " - " + p.getCpf() +
                                    " - " + p.getEstado() + " - " + p.getCidade() +
                                    " - " + p.getEstado_civil() + " - " + p.getSalario()
                    );
                }

                // UPDATE
                System.out.println("\n=== ATUALIZAR UM PROFESSOR ===");
                System.out.print("Digite o ID do professor que deseja atualizar: ");
                int idAtualizarProf = sc.nextInt();
                sc.nextLine();

                System.out.print("Novo nome: ");
                String novoNomeProf = sc.nextLine();

                // usa setters
                Professor profAtualizado = new Professor(nomeProf, cpf, estado, cidade, estadoCivil, salario);
                profAtualizado.setId(idAtualizarProf);
                profAtualizado.setNome(novoNomeProf);

                professorDAO.atualizar(profAtualizado);

                // DELETE
                System.out.println("\n=== DELETAR UM PROFESSOR ===");
                System.out.print("Digite o ID do professor que deseja deletar: ");
                int idDeleteProf = sc.nextInt();

                professorDAO.deletar(idDeleteProf);

                break;
        }

        sc.close();
    }
}
