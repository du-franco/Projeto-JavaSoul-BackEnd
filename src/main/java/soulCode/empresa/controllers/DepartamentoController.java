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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import soulCode.empresa.model.Cargo;
import soulCode.empresa.model.Departamento;
import soulCode.empresa.service.DepartamentoService;







@CrossOrigin
@RestController
@RequestMapping("empresa")
public class DepartamentoController {
	
	@Autowired
	private DepartamentoService departamentoService;
	
	@GetMapping("/departamento")
	public List<Departamento> mostrarTodosDepartamentos(){
		List<Departamento> departamento = departamentoService.mostrarTodosDepartamentos();
		return departamento;
	}
	
	@GetMapping("/departamento/{id_departamento}")
	public ResponseEntity<Departamento> mostrarUmDepartamento(@PathVariable Integer id_departamento) {
		Departamento departamento = departamentoService.mostrarUmDepartamento(id_departamento);
		return ResponseEntity.ok().body(departamento);
	}
	
	@GetMapping("/departamento-cargo/{id_cargo}")
	public ResponseEntity<Departamento> buscarDepDoCargo(@PathVariable Integer id_cargo){
		Departamento departamento = departamentoService.buscarDepDoCargo(id_cargo);
		return ResponseEntity.ok().body(departamento);
	}
	
	@GetMapping("/departamento-nome/{dep_nome}")
	public ResponseEntity<Departamento> buscarDepartamentoPeloNome(@PathVariable String dep_nome){
		Departamento departamento = departamentoService.buscarDepartamentoPeloNome(dep_nome);
		return ResponseEntity.ok().body(departamento);
	}
	
	@GetMapping("/departamentoSemCargo")
	public List<Departamento> departamentoSemCargo(){
		List<Departamento> departamento = departamentoService.departamentoSemCargo();
		return departamento;
	}
	
	@GetMapping("/departamento/departamento-cargo")
	public List<List> funcionariosComCargo(){
		List<List> departamentoCargo = departamentoService.DepartamentoComCargo();
		return departamentoCargo;
	}
	
	
	@PostMapping("/departamento")
	public ResponseEntity<Departamento> InserirDepartamento(@RequestParam(value = "cargo", required = false)Integer id_cargo, @RequestBody Departamento departamento){
		departamento = departamentoService.InserirDepartamento(id_cargo, departamento);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(departamento.getId_departamento()).toUri();
		return ResponseEntity.created(uri).build();
		
	}
	
//	@PutMapping("/departamento/{id_departamento}")
//	public ResponseEntity<Departamento> editarDepartamento(@RequestParam(value = "cargo", required = false)Integer id_cargo, @PathVariable Integer id_departamento, @RequestBody Departamento departamento){
//		departamento.setId_departamento(id_departamento);
//		departamento = departamentoService.editarDepartamento(id_cargo, departamento);
//		return ResponseEntity.noContent().build();
//	}
	
	@PutMapping("/departamento/{id_departamento}")
	public ResponseEntity<Departamento> editarDepartamento(@RequestParam(value="cargo")Cargo cargo, @PathVariable Integer id_departamento, @RequestBody Departamento departamento){
		departamento.setId_departamento(id_departamento);
		departamento.setCargo(cargo);
		cargo.setDepartamento(departamento);
		departamento = departamentoService.editarDepartamento(departamento);
		
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/departamento/{id_departamento}")
	public ResponseEntity<Departamento> deletarUmDepartamento(@PathVariable Integer id_departamento){
		departamentoService.deletarUmDepartamento(id_departamento);
		return ResponseEntity.noContent().build();
	}

}
