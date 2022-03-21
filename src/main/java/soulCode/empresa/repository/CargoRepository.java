package soulCode.empresa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import soulCode.empresa.model.Cargo;


public interface CargoRepository extends JpaRepository<Cargo, Integer>{
	
	@Query(value="SELECT * FROM cargo WHERE id_departamento is null", nativeQuery = true)
	List<Cargo> cargoSemDepartamento();
	
	@Query(value="SELECT * FROM cargo where id_cargo = id_cargo",nativeQuery = true)
	List<Cargo> todosOsCargos();
	
	@Query(value="SELECT * FROM cargo where id_departamento =:id_departamento",nativeQuery = true)
	Cargo cargoDoDepartamento(Integer id_departamento);
	
	@Query(value="SELECT cargo.id_cargo,cargo.car_nome,cargo.car_descricao,departamento.id_departamento,departamento.dep_nome,departamento.dep_descricao FROM cargo left JOIN departamento ON departamento.id_cargo = cargo.id_cargo order by cargo.car_nome;",nativeQuery = true)
	List<List> cargoComSeuDepartamento();
	
	@Query(value="SELECT cargo.id_cargo,cargo.car_nome,cargo.car_descricao,funcionario.id_funcionario,funcionario.func_nome,funcionario.func_cidade,funcionario.func_foto FROM cargo left JOIN funcionario ON funcionario.id_cargo = cargo.id_cargo order by cargo.car_nome;",nativeQuery = true)
	List<List> cargoComFuncionario();

}
