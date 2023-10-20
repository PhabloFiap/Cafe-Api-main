package br.com.fiap.cafe.model.repository;

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

}
