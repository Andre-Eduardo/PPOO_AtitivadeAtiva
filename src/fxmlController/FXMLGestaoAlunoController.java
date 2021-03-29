package fxmlController;

import Models.Aluno;
import Models.Disciplina;
import gestaoAcademica.Main;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

public class FXMLGestaoAlunoController {

	Aluno alunoAtual;
	@FXML
	private Button btnEditAluno;

	@FXML
	private Button btnNewAluno;

	@FXML
	private Button btnDeleteAluno;

	@FXML
	private ListView<Aluno> lvAlunos;

	@FXML
	private Label lblNome;

	@FXML
	private Label lblMatricula;

	@FXML
	private Label lblCpf;

	@FXML
	private Label lblCurso;

	@FXML
	private Button btnReturn;

	@FXML
	private Button btnAddDisciplina;

	@FXML
	private Button btnRemoveDisciplina;

	@FXML
	private ListView<Disciplina> lvDisciplinas;

	// verica se o controller pertence a tela em exibicão e inicializa valores
	@FXML
	protected void initialize() {
		Main.addOnChangeScreenListener(new Main.OnChangeScreen() {

			@Override
			public void onScreenChaged(String newScreen, Object userData) {
				if (newScreen.contentEquals("gestaoAluno"))
					lblNome.setText("");
				lblMatricula.setText("");
				lblCpf.setText("");
				lblCurso.setText("");
				updateList();
			}
		});
	}

	// volta para a tela anterior
	@FXML
	protected void btNavigateSelecao(ActionEvent event) {

		Main.changeScreen("selecao");

	}

	// direciona para pagina que cadrastra um novo aluno
	@FXML
	protected void btNewAluno(ActionEvent event) {

		Main.changeScreen("cadAluno");

	}

	// direciona para pagina que edita um aluno
	@FXML
	protected void btEditAluno(ActionEvent event) {

		ObservableList<Aluno> a = lvAlunos.getSelectionModel().getSelectedItems();
		if (!a.isEmpty()) {
			Aluno aluno = a.get(0);
			Main.changeScreen("cadAluno", aluno);
		}

	}

	// direciona para pagina que cadrastra uma nova disciplina para o aluno
	@FXML
	protected void btNewDisciplina(ActionEvent event) {

		Main.changeScreen("addDisciplinaAluno", alunoAtual);

	}

	// metodo que atualiza as listas na tela
	private void updateList() {
		lvAlunos.getItems().clear();

		try {
			for (Aluno a : Aluno.all()) {

				lvAlunos.getItems().add(a);

			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// // deleta dados do aluno selecionado
	@FXML
	protected void deleteAlunoAction(ActionEvent event) throws ClassNotFoundException {
		ObservableList<Aluno> a = lvAlunos.getSelectionModel().getSelectedItems();
		if (!a.isEmpty()) {
			Aluno aluno = a.get(0);
			aluno.delete();
			updateList();
		}
	}

	// deleta a disciplina do aluno
	@FXML
	protected void DeleteDisciplinaAction(ActionEvent event) throws ClassNotFoundException { // // deleta dados da
																								// disciplina cadastrada
		ObservableList<Disciplina> d = lvDisciplinas.getSelectionModel().getSelectedItems();

		if (!d.isEmpty()) {
			Disciplina disciplina = d.get(0);

			alunoAtual.delete(disciplina);
			atualiza(null);
		}
	}

	// atualiza os dados do aluno ao mudar a seleçao
	@FXML
	protected void atualiza(MouseEvent event) throws ClassNotFoundException {
		lvDisciplinas.getItems().clear();
		ObservableList<Aluno> a = lvAlunos.getSelectionModel().getSelectedItems();

		if (!a.isEmpty()) {
			alunoAtual = a.get(0);
			lblNome.setText(alunoAtual.getNome());
			lblMatricula.setText(String.valueOf(alunoAtual.getMatricula()));
			lblCpf.setText(alunoAtual.getCpf());
			lblCurso.setText(alunoAtual.getCurso().getNome());
			for (Disciplina d : alunoAtual.findDisciplinas()) {
				lvDisciplinas.getItems().add(d);
			}
		}

	}

}
