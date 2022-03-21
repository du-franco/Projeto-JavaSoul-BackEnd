package soulCode.empresa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import soulCode.empresa.model.Cargo;
import soulCode.empresa.model.Departamento;
import soulCode.empresa.model.Funcionario;
import soulCode.empresa.repository.FuncionarioRepository;
import soulCode.empresa.service.exceptions.ObjectNotFoundException;






@Service
public class FuncionarioService {

	// fazendo a injeção de dependências
		@Autowired
		private FuncionarioRepository funcionarioRepository;
		
		@Autowired
		private CargoService cargoService;

		// o primeiro serviço que vamos implementar é a listagem de todos os alunos
		// cadastrados

		public List<Funcionario> mostrarTodosFuncionarios() {
			return funcionarioRepository.findAll();
		}
		
		public Funcionario mostrarUmFuncionario(Integer id_funcionario) {
			Optional<Funcionario> funcionario = funcionarioRepository.findById(id_funcionario);
			return funcionario.orElseThrow();
		}
		
		public List<List> funcionariosComCargo(){
			return funcionarioRepository.funcionariosComCargo();
		}

		// Optional - nos ajuda a evitar os erros (NullPointerException)
		// tira a necessidade da verificação de criarmos codificação (if aluno != null)
		// orElseThrow - se o aluno estiver presente no banco de dados, retorna o aluno.
		// se não lança uma exceção

		public Funcionario buscarUmFuncionario(Integer id_funcionario) {
			Optional<Funcionario> funcionario = funcionarioRepository.findById(id_funcionario);
			return funcionario.orElseThrow(() -> new ObjectNotFoundException(
					"Objeto não cadastrado! O id procurado foi: " + id_funcionario));
		}
		
		public Funcionario buscarFuncionarioPeloNome(String func_nome) {
			Funcionario funcionario = funcionarioRepository.fetchByName(func_nome);
			return funcionario;
		}
		
		public Funcionario inserirFuncionarioNoCargo(Integer id_funcionario, Cargo cargo) {
			Funcionario funcionario = buscarUmFuncionario(id_funcionario);
			funcionario.setCargo(cargo);
			return funcionarioRepository.save(funcionario);
		}
		
		public Funcionario deixarFuncionarioSemCargo(Integer id_funcionario) {
			Funcionario funcionario = buscarUmFuncionario(id_funcionario);
			funcionario.setCargo(null);
			return funcionarioRepository.save(funcionario);
		}
		
		public Funcionario InserirFuncionario(Integer id_cargo, Funcionario funcionario) {
			funcionario.setId_funcionario(null);;
			Cargo cargo = cargoService.buscarUmCargo(id_cargo);
			funcionario.setCargo(cargo);;
			return funcionarioRepository.save(funcionario);
		}
		
//		public Funcionario inserirFuncionario(Funcionario funcionario) {
//			return funcionarioRepository.save(funcionario);
//		}
		
		public void deletarUmFuncionario(Integer id_funcionario) {
			funcionarioRepository.deleteById(id_funcionario);
		}
		
		public Funcionario editarFuncionario(Funcionario funcionario) {
			buscarUmFuncionario(funcionario.getId_funcionario());
			return funcionarioRepository.save(funcionario);
		}
		
		public List<Funcionario> buscarFuncionarioCargo(Integer id_cargo){
			List<Funcionario> funcionario = funcionarioRepository.fetchByTurma(id_cargo);
			return funcionario;
		}
		
		public Funcionario salvarFoto(Integer id_funcionario, String caminhoFoto) {
			Funcionario funcionario = mostrarUmFuncionario(id_funcionario);
			funcionario.setFunc_foto(caminhoFoto);
			return funcionarioRepository.save(funcionario);
		}
		
		public Funcionario atribuirCargo(Integer id_funcionario, Integer id_cargo) {
			Funcionario funcionario = buscarUmFuncionario(id_funcionario);
			Cargo cargo = cargoService.mostrarUmCargo(id_cargo);
			funcionario.setCargo(cargo);
			cargo.setFuncionarios(funcionario);
			return funcionarioRepository.save(funcionario);
		}
	
}
