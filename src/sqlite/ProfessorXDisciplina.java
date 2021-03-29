package sqlite;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Models.Disciplina;
import Models.Professor;

public class ProfessorXDisciplina extends SQLiteBase {
	public ProfessorXDisciplina() {

		try {
			open();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			PreparedStatement stm = conn.prepareStatement("CREATE TABLE IF NOT EXISTS Professor_disciplina ("
					+ "id INTEGER PRIMARY KEY AUTOINCREMENT," + "id_disciplina INTEGER," + "id_professor INTEGER,"
					+ "FOREIGN KEY (id_disciplina) REFERENCES Disciplina(id),"
					+ "FOREIGN KEY (id_professor) REFERENCES Professor(id));");
			stm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}

	public void create(Professor p, Disciplina d) throws ClassNotFoundException {
		open();

		try {
			PreparedStatement stm = conn.prepareStatement("INSERT INTO  Professor_disciplina VALUES(?,?,?);");

			stm.setString(2, String.valueOf(d.getCodigo()));
			stm.setString(3, String.valueOf(p.get_id()));

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
			PreparedStatement stm = conn.prepareStatement("DELETE FROM Professor_disciplina WHERE id_disciplina = ?;");

			stm.setInt(1, d.getCodigo());

			stm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}

	public List<Disciplina> findByProfessor(Professor p) {
		ArrayList<Disciplina> result = new ArrayList<>();

		try {
			open();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			PreparedStatement stm = conn.prepareStatement("SELECT * FROM Professor_disciplina WHERE id_professor = ?");

			stm.setInt(1, p.get_id());
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
