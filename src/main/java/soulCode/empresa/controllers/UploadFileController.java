package soulCode.empresa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import soulCode.empresa.service.FuncionarioService;
import soulCode.empresa.utils.UploadFileUtil;




@RestController
@RequestMapping("empresa")
@CrossOrigin
public class UploadFileController {
	
	@Autowired
	private FuncionarioService funcionarioService;
	
	@PostMapping("/envio/{id_funcionario}")
	public ResponseEntity<String> enviarDados(@PathVariable Integer id_funcionario, MultipartFile foto, @RequestParam("nome") String nome){
		String fileName = nome;
		String uploadDir = "C:/Users/Dalila/Desktop/extensaoJavaProjetoEmpresa/empresaFront/src/assets/img";
		String nomeMaisCaminho = "assets/img/" + nome;
		
		funcionarioService.salvarFoto(id_funcionario, nomeMaisCaminho);
		
		try {
			UploadFileUtil.salvarArquivo(uploadDir, fileName, foto);
		}catch(Exception e){
			System.out.println("O arquivo não foi enviado" + e);
		}
		
		System.out.println("Deu certo:" + nomeMaisCaminho);
		return ResponseEntity.ok("Arquivo enviado");
	}

}

