package soulCode.empresa.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;




@Entity
public class Cargo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_cargo;
	
	@Column(nullable = false, length = 40)
	private String car_nome;
	
	@Column(nullable = false, length = 40)
	private String car_descricao;
	
	@OneToMany(mappedBy = "cargo")
	private List<Funcionario> funcionario = new ArrayList<>();
	
	@OneToOne
	@JoinColumn(name = "id_departamento", unique = true)
	private Departamento departamento;

	@OneToOne
	@JoinColumn(name = "id_funcionario", unique = true)
	private Funcionario funcionarios;

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public Integer getId_cargo() {
		return id_cargo;
	}

	public void setId_cargo(Integer id_cargo) {
		this.id_cargo = id_cargo;
	}

	public String getCar_nome() {
		return car_nome;
	}

	public void setCar_nome(String car_nome) {
		this.car_nome = car_nome;
	}

	public String getCar_descricao() {
		return car_descricao;
	}

	public void setCar_descricao(String car_descricao) {
		this.car_descricao = car_descricao;
	}

	public List<Funcionario> getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(List<Funcionario> funcionario) {
		this.funcionario = funcionario;
	}

	public Funcionario getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(Funcionario funcionarios) {
		this.funcionarios = funcionarios;
	}
	
	
	
}
