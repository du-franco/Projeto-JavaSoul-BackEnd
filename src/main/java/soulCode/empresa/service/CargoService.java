package soulCode.empresa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import soulCode.empresa.model.Cargo;
import soulCode.empresa.model.Departamento;
import soulCode.empresa.repository.CargoRepository;
import soulCode.empresa.service.exceptions.ObjectNotFoundException;





@Service
public class CargoService {

	@Autowired
	private CargoRepository cargoRepository;
	
	@Lazy
	@Autowired
	private DepartamentoService departamentoService;
	
	public List<Cargo> mostrarTodosCargos(){
		return cargoRepository.findAll();
	}
	
	public Cargo mostrarUmCargo(Integer id_cargo) {
		Optional<Cargo> cargo = cargoRepository.findById(id_cargo);
		return cargo.orElseThrow();
	}
	
	public Cargo buscarUmCargo(Integer id_cargo) {
		Optional<Cargo> cargo = cargoRepository.findById(id_cargo);
		return cargo.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não cadastrado! O id procurado foi: " + id_cargo));
	}
	
	public List<Cargo> cargoSemDepartamento(){
		return cargoRepository.cargoSemDepartamento();
	}
	
	public Cargo cargoDoDepartamento(Integer id_departamento) {
		Cargo cargo = cargoRepository.cargoDoDepartamento(id_departamento);
		return cargo;
	}
	
	public List<Cargo> todosOsCargos(){
		return cargoRepository.todosOsCargos();
	}
	

	public List<List> cargoComSeuDepartamento(){
		return cargoRepository.cargoComSeuDepartamento();
	}
	
	public List<List> cargoComFuncionario(){
		return cargoRepository.cargoComFuncionario();
	}
	
	public Cargo cadastrarCargo(Cargo cargo) {
		//null - é uma forma de segurança para não setarmos o id
		cargo.setId_cargo(null);
		return cargoRepository.save(cargo);
	}
	
	public Cargo editarCargo(Cargo cargo) {
		buscarUmCargo(cargo.getId_cargo());
		return cargoRepository.save(cargo);
	}
	
	public void deletarUmCargo(Integer id_cargo) {
		try {
			cargoRepository.deleteById(id_cargo);
		} catch (org.springframework.dao.DataIntegrityViolationException e) {
			throw new soulCode.empresa.service.exceptions.DataIntegrityViolationException("O cargo não pode ser deletado, porque possui funcionários relacionados");
		}
		
	}
	
	public Cargo atribuirDepartamento(Integer id_cargo, Integer id_departamento) {
		Cargo cargo = buscarUmCargo(id_cargo);
		Departamento departamento = departamentoService.mostrarUmDepartamento(id_departamento);
		cargo.setDepartamento(departamento);
		departamento.setCargo(cargo);
		return cargoRepository.save(cargo);
	}
	
	public Cargo deixarCargoSemDepartamento(Integer id_cargo, Integer id_departamento) {
		Cargo cargo = buscarUmCargo(id_cargo);
		cargo.setDepartamento(null);
		Departamento departamento = departamentoService.mostrarUmDepartamento(id_departamento);
		departamento.setCargo(null);
		return cargoRepository.save(cargo);
	}
	
}
