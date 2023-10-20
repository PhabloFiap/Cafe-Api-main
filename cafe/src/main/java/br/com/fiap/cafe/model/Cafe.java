package br.com.fiap.cafe.model;

import java.time.LocalDate;
import java.util.Objects;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;

public class Cafe {
	
	private Long id;
	
	@NotBlank(message="O nome do cafe nao pode estar em branco")
	private String nome;
	
	@NotNull(message="O preco deve ser informado")
	@PositiveOrZero(message="preço igual ou maior que zero")
	private Double preco;
	
	@PastOrPresent(message="data de fabricacao deve ser igual ou menor que hoje")
	private LocalDate dataFabricacao;
	
	@FutureOrPresent(message="data de validade nao deve ser menor que hoje")
	private LocalDate dataValidade;

	
	
	
	public Cafe(Long id,
			@NotBlank(message = "O nome do cafe nao pode estar em branco") String nome,
			@NotNull(message = "O preco deve ser informado") @PositiveOrZero(message = "preço igual ou maior que zero") Double preco,
			@PastOrPresent(message = "data de fabricacao deve ser igual ou menor que hoje") LocalDate dataFabricacao,
			@FutureOrPresent(message = "data de validade nao deve ser menor que hoje") LocalDate dataValidade) {
		super();
		this.id = id;
		this.nome = nome;
		this.preco = preco;
		this.dataFabricacao = dataFabricacao;
		this.dataValidade = dataValidade;
	}
	
	

	public Cafe() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public LocalDate getDataFabricacao() {
		return dataFabricacao;
	}

	public void setDataFabricacao(LocalDate dataFabricacao) {
		this.dataFabricacao = dataFabricacao;
	}

	public LocalDate getDataValidade() {
		return dataValidade;
	}

	public void setDataValidade(LocalDate dataValidade) {
		this.dataValidade = dataValidade;
	}



	@Override
	public int hashCode() {
		return Objects.hash(dataFabricacao, dataValidade, id, nome, preco);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cafe other = (Cafe) obj;
		return Objects.equals(dataFabricacao, other.dataFabricacao) && Objects.equals(dataValidade, other.dataValidade)
				&& Objects.equals(id, other.id) && Objects.equals(nome, other.nome)
				&& Objects.equals(preco, other.preco);
	}



	@Override
	public String toString() {
		return "Cafe [id=" + id + ", nome=" + nome + ", preco=" + preco + ", dataFabricacao=" + dataFabricacao
				+ ", dataValidade=" + dataValidade + "]";
	}
	
	
	

}
