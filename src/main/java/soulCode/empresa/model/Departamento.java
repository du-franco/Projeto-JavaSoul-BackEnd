package soulCode.empresa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Departamento {
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Integer id_departamento;
		
		@Column(nullable = false, length = 40)
		private String dep_nome;
		
		@Column(nullable = true, length = 40)
		private String dep_equipe;
		
		@Column(nullable = true)
		private String dep_descricao;
		
		@JsonIgnore
		@OneToOne
		@JoinColumn(name = "id_cargo", unique = true)
		private Cargo cargo;

		public Integer getId_departamento() {
			return id_departamento;
		}

		public void setId_departamento(Integer id_departamento) {
			this.id_departamento = id_departamento;
		}

		public String getDep_nome() {
			return dep_nome;
		}

		public void setDep_nome(String dep_nome) {
			this.dep_nome = dep_nome;
		}

		public String getDep_equipe() {
			return dep_equipe;
		}

		public void setDep_equipe(String dep_equipe) {
			this.dep_equipe = dep_equipe;
		}

		public String getDep_descricao() {
			return dep_descricao;
		}

		public void setDep_descricao(String dep_descricao) {
			this.dep_descricao = dep_descricao;
		}

		public Cargo getCargo() {
			return cargo;
		}

		public void setCargo(Cargo cargo) {
			this.cargo = cargo;
		}



	}

