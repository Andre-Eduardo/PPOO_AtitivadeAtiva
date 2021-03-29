package sqlite;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Models.Curso;
import Models.Disciplina;

public class DisciplinaSQLite extends SQLiteBase {
	public DisciplinaSQLite() {

		try {
			open();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			PreparedStatement stm = conn.prepareStatement("CREATE TABLE IF NOT EXISTS Disciplina ("
					+ "id INTEGER PRIMARY KEY AUTOINCREMENT," + "nome TEXT," + "cHoraria INTEGER," + "curso_id INTEGER,"
					+ "FOREIGN KEY (curso_id) REFERENCES Curso(id));"

			);
			stm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

	}

	public void create(Disciplina d) throws ClassNotFoundException {
		open();

		try {
			PreparedStatement stm = conn.prepareStatement("INSERT INTO Disciplina VALUES(?,?,?,?);");

			stm.setString(2, d.getNome());
			stm.setInt(3, d.getcHoraria());
			stm.setInt(4, d.getCurso().getCodigo());

			stm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

	}

	public List<Disciplina> all() {
		ArrayList<Disciplina> result = new ArrayList<>();

		try {
			open();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			PreparedStatement stm = conn.prepareStatement("SELECT * FROM Disciplina ORDER BY id ASC");

			ResultSet rs = stm.executeQuery();

			while (rs.next()) {
				Disciplina d = new Disciplina(rs.getInt(1), // codigo
						rs.getString(2), // nome
						rs.getInt(3), // carga horaria
						Curso.find(rs.getInt(4))); // Curso

				result.add(d);

			}

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}

	public void Update(Disciplina d) {
		try {
			open();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			PreparedStatement stm = conn.prepareStatement(
					"UPDATE Disciplina SET " + "Nome = ?," + "cHoraria = ?," + "curso_id = ?" + "WHERE id = ?;");

			stm.setString(1, d.getNome());
			stm.setInt(2, d.getcHoraria());
			stm.setInt(3, (d.getCurso()).getCodigo());
			stm.setInt(4, d.getCodigo());

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
			PreparedStatement stm = conn.prepareStatement("DELETE FROM Disciplina WHERE id = ?;");

			stm.setInt(1, d.getCodigo());

			stm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}

	public Disciplina find(int pk) {
		Disciplina result = null;
		try {
			conn = open();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			PreparedStatement stm = conn.prepareStatement("SELECT * FROM Disciplina WHERE id = ?;");

			stm.setInt(1, pk);

			ResultSet rs = stm.executeQuery();

			if (rs.next()) {
				Disciplina d = new Disciplina(rs.getInt(1), // codigo
						rs.getString(2), // nome
						rs.getInt(3), // carga horaria
						Curso.find(rs.getInt(4))); // Curso
				result = d;
			}

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}

	public List<Disciplina> findByCurso(int pk) {
		ArrayList<Disciplina> result = new ArrayList<>();

		try {
			open();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			PreparedStatement stm = conn.prepareStatement("SELECT * FROM Disciplina WHERE curso_id = ?");

			stm.setInt(1, pk);
			ResultSet rs = stm.executeQuery();

			while (rs.next()) {
				Disciplina d = new Disciplina(rs.getInt(1), // codigo
						rs.getString(2), // nome
						rs.getInt(3), // carga horaria
						Curso.find(rs.getInt(4))); // Curso

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
