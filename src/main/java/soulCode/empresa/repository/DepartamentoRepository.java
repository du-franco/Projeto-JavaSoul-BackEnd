package soulCode.empresa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import soulCode.empresa.model.Departamento;




public interface DepartamentoRepository extends JpaRepository<Departamento, Integer> {
	
	@Query(value = "SELECT * FROM departamento WHERE id_cargo = :id_cargo", nativeQuery = true)
	Departamento buscarDepDoCargo(Integer id_cargo);
	
	@Query(value = "SELECT * FROM departamento WHERE id_cargo is null", nativeQuery = true)
	List<Departamento> departamentoSemCargo();
	
	@Query(value = "SELECT departamento.id_departamento,departamento.dep_nome,departamento.dep_descricao,cargo.id_cargo,cargo.car_nome,cargo.car_descricao FROM cargo right JOIN departamento ON departamento.id_cargo = cargo.id_cargo order by departamento.dep_nome;",nativeQuery = true)
	List<List> departamentoComCargo();
	
	@Query(value = "SELECT * FROM departamento WHERE dep_nome = :dep_nome", nativeQuery = true)
	Departamento fetchByName(String dep_nome);

}
