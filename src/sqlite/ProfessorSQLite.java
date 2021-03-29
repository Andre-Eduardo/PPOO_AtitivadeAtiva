package sqlite;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Models.Professor;

public class ProfessorSQLite extends SQLiteBase {

	public ProfessorSQLite() {

		try {
			open();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			PreparedStatement stm = conn.prepareStatement("CREATE TABLE IF NOT EXISTS Professor ("
					+ "id INTEGER PRIMARY KEY AUTOINCREMENT," + "nome TEXT," + "cpf TEXT," + "telefone TEXT,"
					+ "salario FLOAT," + "titulacao TEXT," + "areaDePesquisa TEXT," + "endereco TEXT);");
			stm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

	}

	public void create(Professor p) throws ClassNotFoundException {
		open();

		try {
			PreparedStatement stm = conn.prepareStatement("INSERT INTO professor VALUES(?,?,?,?,?,?,?,?);");

			stm.setString(2, p.getNome());
			stm.setString(3, p.getCpf());
			stm.setString(4, p.getTelefone());
			stm.setFloat(5, p.getSalario());
			stm.setString(6, p.getTitulacao());
			stm.setString(7, p.getAreaDePesquisa());
			stm.setString(8, p.getEndereco());

			stm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

	}

	public List<Professor> all() {
		ArrayList<Professor> result = new ArrayList<>();

		try {
			open();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			PreparedStatement stm = conn.prepareStatement("SELECT * FROM Professor ORDER BY id ASC");

			ResultSet rs = stm.executeQuery();

			while (rs.next()) {
				Professor p = new Professor(rs.getInt(1), // id
						rs.getString(2), // nome
						rs.getString(3), // cpf
						rs.getString(4), // telefone
						rs.getFloat(5), // salario
						rs.getString(6), // titulacao
						rs.getString(7), // area de pesquisa
						rs.getString(8)); // endereco

				result.add(p);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}

	public void Update(Professor p) {
		try {
			open();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			PreparedStatement stm = conn.prepareStatement(
					"UPDATE Professor SET " + "Nome = ?," + "cpf = ?, " + "telefone = ?," + "salario = ?,"
							+ "titulacao = ?," + "areaDePesquisa = ?," + "endereco = ?" + "WHERE id = ?;");

			stm.setString(1, p.getNome());
			stm.setString(2, p.getCpf());
			stm.setString(3, p.getTelefone());
			stm.setFloat(4, p.getSalario());
			stm.setString(5, p.getTitulacao());
			stm.setString(6, p.getAreaDePesquisa());
			stm.setString(7, p.getEndereco());
			stm.setInt(8, p.get_id());

			stm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}

	public void delete(Professor p) {
		try {
			open();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			PreparedStatement stm = conn.prepareStatement("DELETE FROM Professor WHERE id = ?;");

			stm.setInt(1, p.get_id());

			stm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}

	public Professor find(int pk) {
		Professor result = null;
		try {
			conn = open();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			PreparedStatement stm = conn.prepareStatement("SELECT * FROM Professor WHERE id = ?;");

			stm.setInt(1, pk);

			ResultSet rs = stm.executeQuery();

			if (rs.next()) {
				Professor P = new Professor(rs.getInt(1), // id
						rs.getString(2), // nome
						rs.getString(3), // cpf
						rs.getString(4), // telefone
						rs.getFloat(5), // salario
						rs.getString(6), // titulacao
						rs.getString(7), // area de pesquisa
						rs.getString(8)); // endereco
				result = P;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}

}
