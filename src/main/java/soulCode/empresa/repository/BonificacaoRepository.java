package soulCode.empresa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import soulCode.empresa.model.Bonificacao;


public interface BonificacaoRepository extends JpaRepository<Bonificacao, Integer> {
	
	@Query(value = "SELECT * FROM bd_empresa.bonificacao WHERE id_funcionario = :id_funcionario", nativeQuery = true)
	List<Bonificacao> buscarBonificacoesDoFuncionario(Integer id_funcionario);

}
