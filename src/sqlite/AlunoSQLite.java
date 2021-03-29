package sqlite;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Models.Aluno;
import Models.Curso;

public class AlunoSQLite extends SQLiteBase {
	public AlunoSQLite() {

		try {
			open();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			PreparedStatement stm = conn.prepareStatement(
					"CREATE TABLE IF NOT EXISTS Aluno (" + "id INTEGER PRIMARY KEY AUTOINCREMENT," + "nome TEXT,"
							+ "cpf TEXT," + "curso_id INTEGER," + "FOREIGN KEY (curso_id) REFERENCES Curso(id));");
			stm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

	}

	public void create(Aluno a) throws ClassNotFoundException {
		open();

		try {
			PreparedStatement stm = conn.prepareStatement("INSERT INTO Aluno VALUES(?,?,?,?);");

			stm.setString(2, a.getNome());
			stm.setString(3, a.getCpf());
			stm.setInt(4, (a.getCurso()).getCodigo());

			stm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

	}

	public List<Aluno> all() {
		ArrayList<Aluno> result = new ArrayList<>();

		try {
			open();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			PreparedStatement stm = conn.prepareStatement("SELECT * FROM Aluno ORDER BY id ASC");

			ResultSet rs = stm.executeQuery();

			while (rs.next()) {
				Aluno p = new Aluno(rs.getInt(1), // matricula
						rs.getString(2), // nome
						rs.getString(3), // cpf
						Curso.find(rs.getInt(4))); // Curso

				result.add(p);
			}

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}

	public void Update(Aluno a) {
		try {
			open();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			PreparedStatement stm = conn.prepareStatement(
					"UPDATE Aluno SET " + "Nome = ?," + "cpf = ?," + "curso_id = ?" + "WHERE id = ?;");

			stm.setString(1, a.getNome());
			stm.setString(2, a.getCpf());
			stm.setInt(3, (a.getCurso()).getCodigo());
			stm.setInt(4, a.getMatricula());

			stm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}

	public void delete(Aluno a) {
		try {
			open();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			PreparedStatement stm = conn.prepareStatement("DELETE FROM Aluno WHERE id = ?;");

			stm.setInt(1, a.getMatricula());

			stm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}

	public Aluno find(int pk) {
		Aluno result = null;
		try {
			conn = open();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			PreparedStatement stm = conn.prepareStatement("SELECT * FROM Aluno WHERE id = ?;");

			stm.setInt(1, pk);

			ResultSet rs = stm.executeQuery();

			if (rs.next()) {
				Aluno a = new Aluno(rs.getInt(1), // matricula
						rs.getString(2), // nome
						rs.getString(3), // cpf
						Curso.find(rs.getInt(4))); // Curso
				result = a;
			}

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}
}
