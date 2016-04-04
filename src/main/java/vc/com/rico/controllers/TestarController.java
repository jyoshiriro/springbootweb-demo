package vc.com.rico.controllers;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import vc.com.rico.domains.Escola;
import vc.com.rico.repository.EscolaRepository;
import vc.com.rico.repository.EscolaCorpRepository;

@Controller
public class TestarController {

	private final Log log = LogFactory.getLog(this.getClass());
	
	@Autowired
	EscolaRepository escolaRepository;
	
	@Autowired
	EscolaCorpRepository escolaRepositoryI;
	
//	@Autowired
//	HttpSession sessao;
	
	@RequestMapping("/ola")
	@ResponseBody
	public String ola() {
		log.warn("kkkkkkkkk");
		return "olá!";
	}
	
	@RequestMapping(path="/novaEscola")
	public String novaEscola(@ModelAttribute final Escola nova, final Map model) {
		escolaRepository.save(nova);
		model.put("nova",nova);
		return "nova-escola";
	}
	
	@RequestMapping(path="/listar")
	public String listar(final HttpSession sessao, final Map model) {
		if (sessao.getAttribute("escolas")==null) {
			sessao.setAttribute("escolas", escolaRepository.findAll());
		}
		model.put("escolas", sessao.getAttribute("escolas"));
		return "lista";
	}
	
	@RequestMapping(path="/listar2")
	public String listar2(final Map model) {
		model.put("escolas", escolaRepository.findAll());
		return "lista";
	}
	
	@RequestMapping(path="/listar3/{nome}/{tipo}")
	public String listar3(@PathVariable final String nome, @PathVariable final String tipo, final Map model) {
		model.put("escolas", escolaRepository.findByNomeAndTipo(nome, tipo));
		return "lista";
	}
	
	@RequestMapping(path="/listarPequenasMedias")
	public String listar4(final Map model) {
		model.put("escolas", escolaRepository.listPequenasMedias());
		return "lista";
	}
	
	@RequestMapping(path="/listarMediasGrandes")
	public String listar5(final Map model) {
		model.put("escolas", escolaRepository.listMediasGrandes());
		return "lista";
	}
	
	@RequestMapping(path="/listarPorCapacidade/{i}/{f}")
	public String listar6(@PathVariable final int i, @PathVariable final int f, final Map model) {
		model.put("escolas", escolaRepository.findByCapacidadeBetween(i,f));
		return "lista";
	}
	
	@RequestMapping(path="/listarCorp")
	public String listarCorporativas(Map model) {
		model.put("escolas", escolaRepositoryI.listCorporativasIntegrais());
		return "lista";
	}
	
	@RequestMapping(path="/excluir/{id}")
	public String excluir(@PathVariable final Long id, final HttpSession sessao) {
		escolaRepository.excluir(id);
		sessao.removeAttribute("escolas");
		return "redirect:/listar";
	}
}
