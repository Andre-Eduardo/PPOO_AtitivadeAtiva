package fxmlController;

import Models.Curso;
import Models.Disciplina;
import gestaoAcademica.Main;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

public class FXMLGestaoCursoController {
	Curso cursoAtual;
	@FXML
	private Button btnEditCurso;

	@FXML
	private Button btnDeleteCurso;

	@FXML
	private Button btnNewCurso1;

	@FXML
	private ListView<Curso> listCurso;

	@FXML
	private Button btnReturn;

	@FXML
	private Button btnNewDisciplina;

	@FXML
	private Button btnRemoveDisciplina;

	@FXML
	private Button btnEditDiscipina;

	@FXML
	private Label lbCHorariaCurso;

	@FXML
	private Label lbCodigoCurso;

	@FXML
	private ListView<Disciplina> ListDisciplnas;

	// verica se o controller pertence a tela em exibicão e inicializa valores
	@FXML
	protected void initialize() {

		Main.addOnChangeScreenListener(new Main.OnChangeScreen() {

			@Override
			public void onScreenChaged(String newScreen, Object userData) {
				if (newScreen.contentEquals("gestaoCurso"))
					lbCHorariaCurso.setText("");
				lbCodigoCurso.setText("");
				updateList();

			}
		});
	}

	// volta para a tela anterior
	@FXML
	protected void btNavigateSelecao(ActionEvent event) {

		Main.changeScreen("selecao");

	}

	// direciona para pagina que cadrastra um novo Curso
	@FXML
	protected void btNewCurso(ActionEvent event) {

		Main.changeScreen("cadCurso");

	}

	// direciona para pagina que cadrastra uma nova disciplina
	@FXML
	protected void btNewDisciplina(ActionEvent event) {

		Main.changeScreen("cadDisciplina", cursoAtual.getCodigo());

	}

	// direciona para pagina que edita uma disciplina
	@FXML
	protected void btEditDisciplina(ActionEvent event) {

		Main.changeScreen("cadDisciplina");

	}

	// atualiza as listas na tela
	private void updateList() {
		listCurso.getItems().clear();

		try {
			for (Curso c : Curso.all()) {

				listCurso.getItems().add(c);

			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// deleta dados do curso
	@FXML
	protected void btDeleteCursoAction(ActionEvent event) throws ClassNotFoundException {
		ObservableList<Curso> c = listCurso.getSelectionModel().getSelectedItems();
		if (!c.isEmpty()) {
			Curso curso = c.get(0);
			curso.delete();
			updateList();
		}
	}

	// deleta dados da disciplina
	@FXML
	protected void btDeleteDisciplinaAction(ActionEvent event) throws ClassNotFoundException {
		ObservableList<Disciplina> d = ListDisciplnas.getSelectionModel().getSelectedItems();
		if (!d.isEmpty()) {
			Disciplina disciplina = d.get(0);
			disciplina.delete();
			atualiza(null);
		}
	}

	// redireciona para pagina que edita um curso
	@FXML
	protected void btnEditCUrsoAction(ActionEvent event) {
		ObservableList<Curso> c = listCurso.getSelectionModel().getSelectedItems();
		if (!c.isEmpty()) {
			Curso curso = c.get(0);
			Main.changeScreen("cadCurso", curso);

		}

	}

	// redireciona para pagina que edita uma disciplina
	@FXML
	protected void btnEditDisciplinaAction(ActionEvent event) {
		ObservableList<Disciplina> d = ListDisciplnas.getSelectionModel().getSelectedItems();
		if (!d.isEmpty()) {
			Disciplina disciplina = d.get(0);
			Main.changeScreen("cadDisciplina", disciplina);

		}

	}

	// metodo que verifica um click em determinado componente e atualiza
	@FXML
	void atualiza(MouseEvent event) throws ClassNotFoundException {
		ListDisciplnas.getItems().clear();

		ObservableList<Curso> c = listCurso.getSelectionModel().getSelectedItems();

		if (!c.isEmpty()) {
			cursoAtual = c.get(0);
			lbCHorariaCurso.setText(String.valueOf(cursoAtual.getDuracao()) + " semetres");
			lbCodigoCurso.setText(String.valueOf(cursoAtual.getCodigo()));
			for (Disciplina d : Disciplina.findByCurso(cursoAtual.getCodigo())) {

				ListDisciplnas.getItems().add(d);

			}

		}
	}
}
