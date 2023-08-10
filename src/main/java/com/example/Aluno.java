package com.example;

public record Aluno(String nome, String turma, Integer rm) {

    @Override
    public String toString(){
        // 551409 Pedro (turma)
        return rm + " " + nome + " (" + turma + ") "; 
    }

}
