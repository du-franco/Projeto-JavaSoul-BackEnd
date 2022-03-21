package soulCode.empresa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import soulCode.empresa.model.Cargo;
import soulCode.empresa.model.Departamento;
import soulCode.empresa.repository.DepartamentoRepository;
import soulCode.empresa.service.exceptions.ObjectNotFoundException;



@Service
public class DepartamentoService {
	
	@Autowired
	private DepartamentoRepository departamentoRepository;
	
	@Lazy
	@Autowired
	private CargoService cargoService;
	

	
	public List<Departamento> mostrarTodosDepartamentos(){
		return departamentoRepository.findAll();
	}
	
	public Departamento mostrarUmDepartamento(Integer id_departamento) {
		Optional<Departamento> departamento = departamentoRepository.findById(id_departamento);
		return departamento.orElseThrow();
	}
	
	public Departamento buscarDepDoCargo(Integer id_turma) {
		Departamento professor = departamentoRepository.buscarDepDoCargo(id_turma);
		return professor;
	}
	
	public Departamento buscarDepartamentoPeloNome(String dep_nome) {
		Departamento departamento = departamentoRepository.fetchByName(dep_nome);
		return departamento;
	}
	
	public List<Departamento> departamentoSemCargo(){
		return departamentoRepository.departamentoSemCargo();
	}
	
	public List<List> DepartamentoComCargo(){
		return departamentoRepository.departamentoComCargo();
	}
	
	
	public Departamento InserirDepartamento(Integer id_cargo, Departamento departamento) {
		departamento.setId_departamento(null);
		if (id_cargo != null) {
			Cargo cargo = cargoService.buscarUmCargo(id_cargo);
			departamento.setCargo(cargo);
		}
		return departamentoRepository.save(departamento);
	}
	
//	public Departamento editarDepartamento(Integer id_cargo, Departamento departamento) {
//
//		if(id_cargo != null) {
//			Departamento dadosDepartamento = mostrarUmDepartamento(departamento.getId_departamento());
//			Cargo cargoAnterior = dadosDepartamento.getCargo();
//		if(cargoAnterior != null) {
//			cargoAnterior.setDepartamento(null);
//			cargoRepository.save(cargoAnterior);
//		}
//			Cargo cargo = cargoService.buscarUmCargo(id_cargo);
//			departamento.setCargo(cargo);
//			cargo.setDepartamento(departamento);
//		}
//		
//		return departamentoRepository.save(departamento);
//	}
	
	public Departamento editarDepartamento(Departamento departamento) {
		mostrarUmDepartamento(departamento.getId_departamento());
		return departamentoRepository.save(departamento);
	}
	
	public void deletarUmDepartamento(Integer id_departamento) {
		mostrarUmDepartamento(id_departamento);
		try {
			departamentoRepository.deleteById(id_departamento);
		}catch(org.springframework.dao.DataIntegrityViolationException e) {
			throw new soulCode.empresa.service.exceptions.DataIntegrityViolationException(
					"O departamento não pode ser deletado! Possui cargos relacionados!");
		}
		
	}

}
