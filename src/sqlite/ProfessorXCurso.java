package sqlite;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Models.Curso;
import Models.Professor;

public class ProfessorXCurso extends SQLiteBase {
	public ProfessorXCurso() {

		try {
			open();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			PreparedStatement stm = conn.prepareStatement("CREATE TABLE IF NOT EXISTS Professor_curso ("
					+ "id INTEGER PRIMARY KEY AUTOINCREMENT," + "id_curso INTEGER," + "id_professor INTEGER,"
					+ "FOREIGN KEY (id_curso) REFERENCES Curso(id),"
					+ "FOREIGN KEY (id_professor) REFERENCES Professor(id));");
			stm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}

	public void create(Professor p, Curso c) throws ClassNotFoundException {
		open();

		try {
			PreparedStatement stm = conn.prepareStatement("INSERT INTO  Professor_curso VALUES(?,?,?);");

			stm.setString(2, String.valueOf(c.getCodigo()));
			stm.setString(3, String.valueOf(p.get_id()));

			stm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

	}

	public void delete(Curso c) {
		try {
			open();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			PreparedStatement stm = conn.prepareStatement("DELETE FROM Professor_curso WHERE id_curso = ?;");

			stm.setInt(1, c.getCodigo());

			stm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}

	public List<Curso> findByProfessor(Professor p) {
		ArrayList<Curso> result = new ArrayList<>();

		try {
			open();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			PreparedStatement stm = conn.prepareStatement("SELECT * FROM Professor_curso WHERE id_professor = ?");

			stm.setInt(1, p.get_id());
			ResultSet rs = stm.executeQuery();

			while (rs.next()) {
				Curso c = Curso.find(rs.getInt(2));

				result.add(c);

			}

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}

}
