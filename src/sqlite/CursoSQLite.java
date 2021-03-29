package sqlite;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Models.Curso;

public class CursoSQLite extends SQLiteBase {

	public CursoSQLite() {

		try {
			open();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			PreparedStatement stm = conn.prepareStatement("CREATE TABLE IF NOT EXISTS Curso ("
					+ "id INTEGER PRIMARY KEY AUTOINCREMENT," + "nome TEXT," + "duracao INTEGER);");
			stm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

	}

	public void create(Curso c) throws ClassNotFoundException {
		open();

		try {
			PreparedStatement stm = conn.prepareStatement("INSERT INTO Curso VALUES(?,?,?);");

			stm.setString(2, c.getNome());
			stm.setInt(3, c.getDuracao());

			stm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

	}

	public List<Curso> all() {
		ArrayList<Curso> result = new ArrayList<>();

		try {
			open();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			PreparedStatement stm = conn.prepareStatement("SELECT * FROM Curso ORDER BY id ASC");

			ResultSet rs = stm.executeQuery();

			while (rs.next()) {
				Curso c = new Curso(rs.getInt(1), // codigo
						rs.getString(2), // nome
						rs.getInt(3));// duracao
				result.add(c);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}

	public void Update(Curso c) {
		try {
			open();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			PreparedStatement stm = conn
					.prepareStatement("UPDATE Curso SET " + "Nome = ?," + "duracao = ? " + "WHERE id = ?;");

			stm.setString(1, c.getNome());
			stm.setInt(2, c.getDuracao());
			stm.setInt(3, c.getCodigo());

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
			PreparedStatement stm = conn.prepareStatement("DELETE FROM Curso WHERE id = ?;");

			stm.setInt(1, c.getCodigo());

			stm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}

	public Curso find(int pk) {
		Curso result = null;
		try {
			conn = open();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			PreparedStatement stm = conn.prepareStatement("SELECT * FROM Curso WHERE id = ?;");

			stm.setInt(1, pk);

			ResultSet rs = stm.executeQuery();

			if (rs.next()) {
				Curso c = new Curso(rs.getInt(1), // codigo
						rs.getString(2), // nome
						rs.getInt(3));// duracao
				result = c;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}

}
