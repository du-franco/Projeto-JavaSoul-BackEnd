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
import soulCode.empresa.model.Funcionario;
import soulCode.empresa.service.FuncionarioService;







@CrossOrigin
@RestController
@RequestMapping("empresa")
public class FuncionarioController {
	// precisamos da injeção de dependências


		@Autowired
		private FuncionarioService funcionarioService;

		@GetMapping("/funcionario")
		public List<Funcionario> mostrarTodosFuncionarios() {
			List<Funcionario> funcionario = funcionarioService.mostrarTodosFuncionarios();
			return funcionario;
		}
		
		@GetMapping("/funcionario-cargo")
		public List<List> funcionariosComCargo(){
			List<List> funcionarioCargo = funcionarioService.funcionariosComCargo();
			return funcionarioCargo;
		}

		// procurando um valor único, específico com dados reais, trazendo tudo
		// referente ao dado que está sendo capturado no banco de dados.
		// retorna uma resposta inteira, incluindo cabeçaho, corpo e status

		// PathVariable - especifica o id procurado vai ser passado através da URL

		@GetMapping("/funcionario/{id_funcionario}")
		public ResponseEntity<?> buscarUmFuncionario(@PathVariable Integer id_funcionario) {
			Funcionario funcionario = funcionarioService.buscarUmFuncionario(id_funcionario);
			return ResponseEntity.ok().body(funcionario);
		}
		
		@GetMapping("/funcionario/busca-cargo/{id_cargo}")
		public List<Funcionario> buscarFuncionarioCargo(@PathVariable Integer id_cargo){
			List<Funcionario> funcionario = funcionarioService.buscarFuncionarioCargo(id_cargo);
			return funcionario;
		}
		
		@GetMapping("/funcionario-nome/{func_nome}")
		public ResponseEntity<Funcionario> buscarFuncionarioPeloNome(@PathVariable String func_nome){
			Funcionario funcionario = funcionarioService.buscarFuncionarioPeloNome(func_nome);
			return ResponseEntity.ok().body(funcionario);
		}
		
		@PutMapping("/funcionario/inserirCargo/{id_funcionario}")
		public ResponseEntity<Funcionario> inserirFuncionarioNoCargo(@PathVariable Integer id_funcionario, @RequestBody Cargo cargo){
			Funcionario funcionario = funcionarioService.inserirFuncionarioNoCargo(id_funcionario, cargo);
			return ResponseEntity.noContent().build();
		}
		
		@PutMapping("/funcionario/deixarSemCargo/{id_funcionario}")
		public ResponseEntity<Funcionario> deixarFuncionarioSemCargo(@PathVariable Integer id_funcionario){
			Funcionario funcionario = funcionarioService.deixarFuncionarioSemCargo(id_funcionario);
			return ResponseEntity.noContent().build();
		}
		
		@PutMapping("/funcionario/definirCargo/{id_funcionario}/{id_cargo}")
		public ResponseEntity<Cargo> atribuirCargo(@PathVariable Integer id_funcionario, @PathVariable Integer id_cargo){
			funcionarioService.atribuirCargo(id_funcionario, id_cargo);
			return ResponseEntity.noContent().build();
		}
		
//		@PostMapping("/funcionario")
//		public ResponseEntity<Void> inserirAluno(@RequestBody Funcionario funcionario){
//			
//			funcionario = funcionarioService.inserirFuncionario(funcionario);
			
			//URI - Identificador Uniforme de Recurso - Serve para identificar ou denominar um recurso vindo do servidor. URI une o protocolo HTTP + Localização do Recurso (URL) + O nome do recurso (URN)
			
			//ServletUriComponentsBuilder - é uma calsse que possui métodos para criar ligações com o servidor
			
			//fromCurrentRequest - vai pegar qual é o servidor do momento
			
			//buildAndExpand - cria uma nova instância para o novo aluno que está sendo adicionado.
			
//			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
//					.buildAndExpand(funcionario.getId_funcionario()).toUri();
//			return ResponseEntity.created(uri).build();
//		}
		
		@PostMapping("/funcionario")
		public ResponseEntity<Funcionario> InserirFuncionario(@RequestParam(value = "cargo") Integer id_cargo, @RequestBody Funcionario funcionario) {
			funcionario = funcionarioService.InserirFuncionario(id_cargo, funcionario);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/funcionario/{id}").buildAndExpand(funcionario.getId_funcionario()).toUri();
			return ResponseEntity.created(uri).build();
		}
		
		@DeleteMapping("/funcionario/{id_funcionario}")
		public ResponseEntity<Void> deletarUmFuncionario(@PathVariable Integer id_funcionario){
			funcionarioService.deletarUmFuncionario(id_funcionario);
			return ResponseEntity.noContent().build();
		}
		
		
		//novo método put
		
		@PutMapping("/funcionario/{id_funcionario}")
		public ResponseEntity<Void> editarFuncionario(@RequestParam(value = "cargo")Cargo cargo, @PathVariable Integer id_funcionario, @RequestBody Funcionario funcionario){
			funcionario.setId_funcionario(id_funcionario);
			funcionario.setCargo(cargo);
			funcionario = funcionarioService.editarFuncionario(funcionario);
			return ResponseEntity.noContent().build();
		}
	}
