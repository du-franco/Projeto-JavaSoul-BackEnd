package soulCode.empresa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import soulCode.empresa.model.Bonificacao;
import soulCode.empresa.model.Funcionario;
import soulCode.empresa.model.StatusBonificacao;
import soulCode.empresa.repository.BonificacaoRepository;



@Service
public class BonificacaoService {
	
	@Autowired
	private BonificacaoRepository bonificacaoRepository;
	
	@Autowired
	private FuncionarioService funcionarioService;
	
	public List<Bonificacao> buscarTodasBonificacoes(){
		return bonificacaoRepository.findAll();
	}
	
	public Bonificacao buscarUmaBonificacao(Integer codigo) {
		Optional<Bonificacao> bonificacao = bonificacaoRepository.findById(codigo);
		return bonificacao.orElseThrow();
	}
	
	public List<Bonificacao> buscarBonificacoesDoFuncionario(Integer id_funcionario) {
		List<Bonificacao> bonificacao = bonificacaoRepository.buscarBonificacoesDoFuncionario(id_funcionario);
		return bonificacao;
	}
	
	public Bonificacao adicionarBonificacao(Bonificacao bonificacao, Integer id_funcionario) {
		bonificacao.setCodigo(null);
		Funcionario funcionario = funcionarioService.buscarUmFuncionario(id_funcionario);
		bonificacao.setFuncionario(funcionario);
		return bonificacaoRepository.save(bonificacao);
	}
	
	public Bonificacao receberBonificacao(Integer codigo) {
		Bonificacao bonificacao = buscarUmaBonificacao(codigo);
		StatusBonificacao stl = StatusBonificacao.RECEBIDO;
		bonificacao.setBo_status(stl);
		return bonificacaoRepository.save(bonificacao);
	}
	
	public Bonificacao cancelarBonificacao(Integer codigo) {
		Bonificacao bonificacao = buscarUmaBonificacao(codigo);
		StatusBonificacao stl = StatusBonificacao.CANCELADO;
		bonificacao.setBo_status(stl);
		return bonificacaoRepository.save(bonificacao);
	}
	
	public Bonificacao editarBonificacao(Bonificacao bonificacao, Integer codigo, Integer id_funcionario) {
		buscarUmaBonificacao(codigo);
		Funcionario funcionario = funcionarioService.buscarUmFuncionario(id_funcionario);
		bonificacao.setFuncionario(funcionario);
		return bonificacaoRepository.save(bonificacao);
	}
	
	public void deletarUmaBonificacao(Integer codigo) {
		bonificacaoRepository.deleteById(codigo);
	}

}
