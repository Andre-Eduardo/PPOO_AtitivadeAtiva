package gestaoAcademica;

import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

	private static Stage stage;
	private static Scene selecaoScene;
	private static Scene gestaoProfessorScene;
	private static Scene gestaoCursoScene;
	private static Scene gestaoAlunoScene;
	private static Scene cadProfessorScene;
	private static Scene cadDisciplinaScene;
	private static Scene cadCursoScene;
	private static Scene cadAlunoScene;
	private static Scene addDisciplinaProfessorScene;
	private static Scene addDisciplinaAlunoScene;
	private static Scene addCursoScene;

	@Override
	public void start(Stage primaryStage) throws IOException, ClassNotFoundException {

		stage = primaryStage;

		// criando cache para as telas :

		Pane fxmlSelecao = FXMLLoader.load(getClass().getResource("/fxml/FXMLSelecao.fxml"));
		selecaoScene = new Scene(fxmlSelecao);

		Pane fxmlGestaoProfessor = FXMLLoader.load(getClass().getResource("/fxml/FXMLGestaoProfessor.fxml"));
		gestaoProfessorScene = new Scene(fxmlGestaoProfessor);

		Pane fxmlGestaoCurso = FXMLLoader.load(getClass().getResource("/fxml/FXMLGestaoCurso.fxml"));
		gestaoCursoScene = new Scene(fxmlGestaoCurso);

		Pane fxmlGestaoAluno = FXMLLoader.load(getClass().getResource("/fxml/FXMLGestaoAluno.fxml"));
		gestaoAlunoScene = new Scene(fxmlGestaoAluno);

		Pane fxmlCadProfessor = FXMLLoader.load(getClass().getResource("/fxml/FXMLCadProfessor.fxml"));
		cadProfessorScene = new Scene(fxmlCadProfessor);

		Pane fxmlCadDisciplina = FXMLLoader.load(getClass().getResource("/fxml/FXMLCadDisciplina.fxml"));
		cadDisciplinaScene = new Scene(fxmlCadDisciplina);

		Pane fxmlCadCurso = FXMLLoader.load(getClass().getResource("/fxml/FXMLCadCurso.fxml"));
		cadCursoScene = new Scene(fxmlCadCurso);

		Pane fxmlCadAluno = FXMLLoader.load(getClass().getResource("/fxml/FXMLCadAluno.fxml"));
		cadAlunoScene = new Scene(fxmlCadAluno);

		Pane fxmlAddDisciplinaProfessor = FXMLLoader
				.load(getClass().getResource("/fxml/FXMLAddDisciplinaProfessor.fxml"));
		addDisciplinaProfessorScene = new Scene(fxmlAddDisciplinaProfessor);

		Pane fxmlAddDisciplinaAluno = FXMLLoader.load(getClass().getResource("/fxml/FXMLAddDisciplinaAluno.fxml"));
		addDisciplinaAlunoScene = new Scene(fxmlAddDisciplinaAluno);

		Pane fxmlAddCurso = FXMLLoader.load(getClass().getResource("/fxml/FXMLAddCurso.fxml"));
		addCursoScene = new Scene(fxmlAddCurso);

		primaryStage.setScene(selecaoScene); // tela inicial
		primaryStage.show(); // montando a tela
	}

	// seleciona qual tela deve ser exibida :
	public static void changeScreen(String src, Object userData) {

		switch (src) {
		case "selecao":
			stage.setScene(selecaoScene);
			notifyAllListers("selecao", userData);
			break;

		case "gestaoProfessor":
			stage.setScene(gestaoProfessorScene);
			notifyAllListers("gestaoProfessor", userData);
			break;
		case "gestaoCurso":
			stage.setScene(gestaoCursoScene);
			notifyAllListers("gestaoCurso", userData);
			break;
		case "gestaoAluno":
			stage.setScene(gestaoAlunoScene);
			notifyAllListers("gestaoAluno", userData);
			break;
		case "cadProfessor":
			stage.setScene(cadProfessorScene);
			notifyAllListers("cadProfessor", userData);
			break;
		case "cadDisciplina":
			stage.setScene(cadDisciplinaScene);
			notifyAllListers("cadDisciplina", userData);
			break;
		case "cadCurso":
			stage.setScene(cadCursoScene);
			notifyAllListers("cadCurso", userData);
			break;
		case "cadAluno":
			stage.setScene(cadAlunoScene);
			notifyAllListers("cadAluno", userData);
			break;
		case "addDisciplinaProfessor":
			stage.setScene(addDisciplinaProfessorScene);
			notifyAllListers("addDisciplinaProfessor", userData);
			break;
		case "addDisciplinaAluno":
			stage.setScene(addDisciplinaAlunoScene);
			notifyAllListers("addDisciplinaAluno", userData);
			break;
		case "addCurso":
			stage.setScene(addCursoScene);
			notifyAllListers("addCurso", userData);
			break;
		}

	}

	// sobrecarga do metodo para aceitar 1 parametro
	public static void changeScreen(String src) {
		changeScreen(src, null);
	}

	public static void main(String[] args) {
		launch(args);
	}

	// observer que irar passar paramentros para as telas
	private static ArrayList<OnChangeScreen> listeners = new ArrayList<>();

	public static interface OnChangeScreen {
		void onScreenChaged(String newScreen, Object userData);
	}

	public static void addOnChangeScreenListener(OnChangeScreen newListener) {
		listeners.add(newListener);
	}

	private static void notifyAllListers(String newScreen, Object userData) {
		for (OnChangeScreen l : listeners) {
			l.onScreenChaged(newScreen, userData);
		}
	}

}
