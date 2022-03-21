package soulCode.empresa.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import soulCode.empresa.model.Cargo;
import soulCode.empresa.model.Departamento;
import soulCode.empresa.service.CargoService;



@CrossOrigin
@RestController
@RequestMapping("empresa")
public class CargoController {

	@Autowired
	private CargoService cargoService;
	
	@GetMapping("/cargo")
	public List<Cargo> mostrarTodosCargos(){
		List<Cargo> cargo = cargoService.mostrarTodosCargos();
		return cargo;
	}
	
	@GetMapping("/cargo/{id_cargo}")
	public ResponseEntity<Cargo> buscarUmCargo(@PathVariable Integer id_cargo){
		Cargo cargo = cargoService.buscarUmCargo(id_cargo);
		return ResponseEntity.ok().body(cargo);
	}
	
	@GetMapping("/cargoSemDepartamento")
	public List<Cargo> departamentoSemCargo(){
		List<Cargo> cargo = cargoService.cargoSemDepartamento();
		return cargo;
	}
	
	@GetMapping("/cargo/cargo-departamento/{id_departamento}")
	public Cargo cargoDoDepartamento(@PathVariable Integer id_departamento){
		
		return cargoService.cargoDoDepartamento(id_departamento);
	}
	
	@GetMapping("/cargo/todosCargos")
	public List<Cargo> todosOsCargos(){
		List<Cargo> todosCargos = cargoService.todosOsCargos();
		return todosCargos;
	}
	
	@GetMapping("/cargo/cargo-departamento")
	public List<List> cargosComDepartamento(){
		List<List> cargoDepartamento = cargoService.cargoComSeuDepartamento();
		return cargoDepartamento;
	}
	
	@GetMapping("/cargo/cargo-funcionario")
	public List<List> cargosComFuncionario(){
		List<List> cargoFuncionario = cargoService.cargoComFuncionario();
		return cargoFuncionario;
	}
	
	@PostMapping("/cargo")
	public ResponseEntity<Cargo> cadastrarCargo(@RequestBody Cargo cargo){
		cargo = cargoService.cadastrarCargo(cargo);
		
		URI uri =  ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(cargo.getId_cargo()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/cargo/{id_cargo}")
	public ResponseEntity<Cargo> editarCargo(@PathVariable Integer id_cargo, @RequestBody Cargo cargo){
		cargo.setId_cargo(id_cargo);
		cargo = cargoService.editarCargo(cargo);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/cargo/{id_cargo}")
	public ResponseEntity<Void> deletarUmCargo(@PathVariable Integer id_cargo){
		cargoService.deletarUmCargo(id_cargo);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/cargo/definirDepartamento/{id_cargo}/{id_departamento}")
	public ResponseEntity<Departamento> atribuirDepartamento(@PathVariable Integer id_cargo, @PathVariable Integer id_departamento){
		cargoService.atribuirDepartamento(id_cargo, id_departamento);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/cargo/tirarDepartamento/{id_cargo}/{id_departamento}")
	public ResponseEntity<Departamento> deixarCargoSemDepartamento(@PathVariable Integer id_cargo, @PathVariable Integer id_departamento){
		cargoService.deixarCargoSemDepartamento(id_cargo, id_departamento);
		return ResponseEntity.noContent().build();
	}
	
}
	
