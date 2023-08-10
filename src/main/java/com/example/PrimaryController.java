package com.example;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class PrimaryController {

    // Atributos
    @FXML
    ListView<Aluno> lstAlunos;
    @FXML
    TextField txtNome;
    @FXML
    RadioButton rdbCrescente;
    @FXML
    RadioButton rdbDecrescente;

    // Collections - ArrayList
    // private String[] nomes; // <- arrays primitivos
    // <String> - generics (como são chamados)
    private ArrayList<Aluno> alunos = new ArrayList<>(); // Chamando o construtor para criar um arraylist vazio

    // Metodos
    public void adicionarAluno() {
        var aluno = new Aluno(txtNome.getText(), "1TDSPG", 200399);
        alunos.add(aluno);
        // alunos.add(txtNome.getText());
        txtNome.clear();
        mostrarAluno();
    }

    public void mostrarAluno() {
        ordenar();

        lstAlunos.getItems().clear();

        for (var aluno : alunos) {
            lstAlunos.getItems().add(aluno);
        }
    }

    private void ordenar() {

        // SOLID
        if (rdbCrescente.isSelected()) {
            // lambda axpression (java) - arrow function (js)
            // Ordem alfabética A - Z
            alunos.sort((a1, a2) -> a1.nome().compareToIgnoreCase(a2.nome()));
        }
        if (rdbDecrescente.isSelected()) {
            // Ordem alfabética Z - A
            alunos.sort((a1, a2) -> a2.nome().compareToIgnoreCase(a1.nome()));
        }
    }

    public void apagarAluno() {
        // Pegar o aluno da Selecionado na lista
        var aluno = lstAlunos.getSelectionModel().getSelectedItem();
        
        // Apagar o aluno do array
        alunos.remove(aluno);
        
        // Atualizar a tela
        mostrarAluno();

        // Mostrar um alerta na tela
        Alert alert = new Alert(AlertType.INFORMATION, "Aluno(a) " + aluno + " apagado com sucesso!");
        alert.show();
    }

}
