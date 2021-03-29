package sqlite;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Models.Aluno;
import Models.Disciplina;

public class Disciplina_AlunoSQLite extends SQLiteBase {
	public Disciplina_AlunoSQLite() {

		try {
			open();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			PreparedStatement stm = conn.prepareStatement("CREATE TABLE IF NOT EXISTS Curso_Aluno ("
					+ "id INTEGER PRIMARY KEY AUTOINCREMENT," + "id_curso INTEGER," + "id_aluno INTEGER,"
					+ "FOREIGN KEY (id_aluno) REFERENCES Aluno(id)," + "FOREIGN KEY (id_curso) REFERENCES Curso(id));");
			stm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}

	public void create(Aluno a, Disciplina d) throws ClassNotFoundException {
		open();

		try {
			PreparedStatement stm = conn.prepareStatement("INSERT INTO Curso_Aluno VALUES(?,?,?);");

			stm.setString(2, String.valueOf(d.getCodigo()));
			stm.setString(3, String.valueOf(a.getMatricula()));

			stm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

	}

	public void delete(Disciplina d) {
		try {
			open();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			PreparedStatement stm = conn.prepareStatement("DELETE FROM Curso_Aluno WHERE id_curso = ?;");

			stm.setInt(1, d.getCodigo());

			stm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}

	public List<Disciplina> findByAluno(Aluno a) {
		ArrayList<Disciplina> result = new ArrayList<>();

		try {
			open();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			PreparedStatement stm = conn.prepareStatement("SELECT * FROM Curso_Aluno WHERE id_aluno = ?");

			stm.setInt(1, a.getMatricula());
			ResultSet rs = stm.executeQuery();

			while (rs.next()) {
				Disciplina d = Disciplina.find(rs.getInt(2));

				result.add(d);

			}

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}

}
