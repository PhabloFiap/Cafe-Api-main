package br.com.fiap.cafe.model.repository;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.cafe.model.Cafe;

public class CafeRepository extends Repository {

	public static List<Cafe> findAll() {
		String sql = "SELECT * FROM CAFE";
		PreparedStatement ps = null;

		ResultSet rs = null;
		List<Cafe> cafes = new ArrayList<>();

		try {
			ps = getConnection().prepareStatement(sql);

			rs = ps.executeQuery();

			if (rs.isBeforeFirst()) {
				while (rs.next()) {
					Cafe cafe = new Cafe();
					cafe.setId(rs.getLong("ID"));
					cafe.setNome(rs.getString("nome"));
					cafe.setPreco(rs.getDouble("preco"));
					cafe.setDataFabricacao(rs.getDate("dataFabricacao").toLocalDate());
					cafe.setDataValidade(rs.getDate("dataValidade").toLocalDate());

					cafes.add(cafe);

				}

			} else {
				System.out.println("Não foram encontrados registros na tabela do banco de dados.");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return cafes;
	}

	public static Cafe salva(Cafe cafe) {

		String sql = "begin insert into cafe (id, nome, preco, dataFabricacao, dataValidade ) values(SQ_CAFE.nextval,?,?,?,?)"
				+ "return id into ?; end;";

		CallableStatement cs = null;

		try {
			cs = getConnection().prepareCall(sql);
			cs.setString(1, cafe.getNome());
			cs.setDouble(2, cafe.getPreco());
			cs.setDate(3, Date.valueOf(cafe.getDataFabricacao()));
			cs.setDate(4, Date.valueOf(cafe.getDataValidade()));

			cs.registerOutParameter(5, java.sql.Types.INTEGER);
			cs.executeUpdate();
			cafe.setId(Long.valueOf(cs.getInt(5)));

			return cafe;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("erro ao salvar " + e.getMessage());
		} finally {
			if (cs != null)
				try {
					cs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}

		return null;
	}

}
