package Util;

import Model.Professor;

public class Sessao {

    private static Professor professorLogado = null;

    public static void login(Professor professor) {
        professorLogado = professor;
    }

    public static Professor getProfessor() {
        return professorLogado;
    }

    public static boolean estaLogado() {
        return professorLogado != null;
    }

    public static void logout() {
        professorLogado = null;
    }
}