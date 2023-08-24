package com.example.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import com.example.data.AlunoDao;
import com.example.model.Aluno;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class PrimaryController {

    // Atributos
    @FXML
    ListView<Aluno> lstAlunos;
    @FXML
    TextField txtNome;
    @FXML
    TextField txtTurma;
    @FXML
    TextField txtRm;
    @FXML
    RadioButton rdbCrescente;
    @FXML
    RadioButton rdbDecrescente;

    AlunoDao alunoDao = new AlunoDao();

    // Collections - ArrayList
    // private String[] nomes; // <- arrays primitivos
    // <String> - generics (como são chamados)
    private ArrayList<Aluno> alunos = new ArrayList<>(); // Chamando o construtor para criar um arraylist vazio

    // Metodos
    public void adicionarAluno() {
        var aluno = new Aluno(txtNome.getText(), txtTurma.getText(), Integer.valueOf(txtRm.getText()));
        try {

            alunoDao.inserir(aluno);

            mostrarMensagem(
                    AlertType.INFORMATION,
                    "Suceso",
                    "Aluno cadastrado com sucesso");

        } catch (Exception e) {

            mostrarMensagem(
                    AlertType.ERROR,
                    "Erro ao cadastrar aluno",
                    e.getLocalizedMessage());

        }

        txtNome.clear();
        txtTurma.clear();
        txtRm.clear();

        atualizarAluno();
    }

    public void atualizarAluno() {
        lstAlunos.getItems().clear();
        try {

            alunoDao.listarTodos()
                    .forEach(aluno -> lstAlunos.getItems().add(aluno));

        } catch (Exception e) {

            mostrarMensagem(
                    AlertType.ERROR,
                    "Erro ao buscar dados do aluno",
                    e.getLocalizedMessage());

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

        try {
            alunoDao.apagar(aluno);
            atualizarAluno();

            mostrarMensagem(
                    AlertType.INFORMATION,
                    "Sucesso",
                    "Aluno(a) " + aluno + " apagado com sucesso!");
        } catch (SQLException e) {

            mostrarMensagem(
                AlertType.ERROR,
                "",
                "");

        }

        

    }

    private void mostrarMensagem(AlertType tipo, String titulo, String texto) {

        Alert mensagem = new Alert(AlertType.ERROR);
        mensagem.setHeaderText(titulo);
        mensagem.setContentText(texto);
        mensagem.show();

    }

}
