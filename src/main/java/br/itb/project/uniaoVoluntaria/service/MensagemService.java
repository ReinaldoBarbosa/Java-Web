package br.itb.project.uniaoVoluntaria.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.itb.project.uniaoVoluntaria.model.entity.Mensagem;
import br.itb.project.uniaoVoluntaria.model.repository.MensagemRepository;
import jakarta.transaction.Transactional;

@Service
public class MensagemService {
	private MensagemRepository mensagemRepository;
	
	@Autowired
	private EmailService emailService;

	public MensagemService(MensagemRepository mensagemRepository) {
		super();
		this.mensagemRepository = mensagemRepository;
	}

	public List<Mensagem> findAll() {
		List<Mensagem> mensagens = mensagemRepository.findAll();
		
		return mensagens;
	}
	
	@Transactional
	public Mensagem create(Mensagem mensagem) {
		
		mensagem.setDataMensagem(LocalDateTime.now());
		mensagem.setStatusMensagem("ATIVO");
		
		emailService.faleconosco(mensagem.getEmail(), mensagem.getEmissorMensagem(), mensagem.getTexto() );
		
		return mensagemRepository.save(mensagem);
	}
	
	@Transactional
	public Mensagem update(long id) {
		Optional<Mensagem> _mensagem = mensagemRepository.findById(id);
		if (_mensagem.isPresent()) {
			Mensagem mensagemAtualizada = _mensagem.get();
			mensagemAtualizada.setStatusMensagem("LIDA");
			
			return mensagemRepository.save(mensagemAtualizada);
		}
		return null;
	}
	
	// Cópia do update com alteração apenas em inativar e "INATIVO"
	@Transactional
	public Mensagem inativar(long id) {
		Optional<Mensagem> _mensagem = mensagemRepository.findById(id);
		if (_mensagem.isPresent()) {
			Mensagem mensagemAtualizada = _mensagem.get();
			mensagemAtualizada.setStatusMensagem("INATIVO");
			
			return mensagemRepository.save(mensagemAtualizada);
		}
		return null;
	}
}
