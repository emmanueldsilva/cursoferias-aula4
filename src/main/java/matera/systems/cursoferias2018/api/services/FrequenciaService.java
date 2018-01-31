package matera.systems.cursoferias2018.api.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import matera.systems.cursoferias2018.api.domain.entity.DisciplinaEntity;
import matera.systems.cursoferias2018.api.domain.entity.FrequenciaEntity;
import matera.systems.cursoferias2018.api.domain.entity.UsuarioEntity;
import matera.systems.cursoferias2018.api.domain.response.DisciplinaResponse;
import matera.systems.cursoferias2018.api.domain.response.FrequenciaResponse;
import matera.systems.cursoferias2018.api.domain.response.UsuarioResponse;
import matera.systems.cursoferias2018.api.repository.DisciplinaRepository;
import matera.systems.cursoferias2018.api.repository.FrequenciaRepository;
import matera.systems.cursoferias2018.api.repository.UsuarioRepository;

@Service
public class FrequenciaService {
	
	@Autowired
	private FrequenciaRepository frequenciaRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private DisciplinaRepository disciplinaRepository;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private DisciplinaService disciplinaService;
	
	public void setIn(UUID disciplinaId, UUID usuarioId, String data) {
		frequenciaRepository.setIn(retrieveFrequenciaEntity(disciplinaId, usuarioId , data));
	}

	public void setOut(UUID disciplinaId, UUID usuarioId, String data) {
		final FrequenciaEntity frequencia = frequenciaRepository.setOut(retrieveFrequenciaEntity(disciplinaId, usuarioId, data));
		if (frequencia == null) {
			throw new IllegalArgumentException("Aluno não presente na Disciplina na data informada");
		}
	}
	
	public FrequenciaResponse list(UUID disciplinaId, UUID usuarioId) {
		final Optional<UsuarioEntity> usuario = usuarioRepository.findByID(usuarioId);
		if (!usuario.isPresent()) {
			throw new IllegalArgumentException("Usuário não encontrado");
		}
		
		final Optional<DisciplinaEntity> disciplina = disciplinaRepository.findByID(disciplinaId);
		if (!disciplina.isPresent()) {
			throw new IllegalArgumentException("Disciplina não encontrada");
		}
		
		final List<FrequenciaEntity> frequencias = frequenciaRepository.list(disciplina.get(), usuario.get());
		
		List<String> datas = frequencias.stream()
									    .map(f -> f.getData())
									    .collect(Collectors.toList());
		
		return toResponse(disciplina.get(), usuario.get(), datas);
	}
	
	private FrequenciaResponse toResponse(DisciplinaEntity disciplina, UsuarioEntity usuario, List<String> datas) {
		final DisciplinaResponse disciplinaResponse = Optional.of(disciplina).map(disciplinaService.toResponse).get();
		final UsuarioResponse usuarioResponse = Optional.of(usuario).map(usuarioService.toResponse).get();
		
		final FrequenciaResponse frequenciaResponse = new FrequenciaResponse();
		frequenciaResponse.setDisciplinaResponse(disciplinaResponse);
		frequenciaResponse.setUsuarioResponse(usuarioResponse);
		frequenciaResponse.setDatas(datas);
		
		return frequenciaResponse;
	}
	
	private FrequenciaEntity retrieveFrequenciaEntity(UUID disciplinaId, UUID usuarioId, String data) {
		final Optional<UsuarioEntity> usuario = usuarioRepository.findByID(usuarioId);
		if (!usuario.isPresent()) {
			throw new IllegalArgumentException("Usuário não encontrado");
		}
		
		final Optional<DisciplinaEntity> disciplina = disciplinaRepository.findByID(disciplinaId);
		if (!disciplina.isPresent()) {
			throw new IllegalArgumentException("Disciplina não encontrada");
		}
		
		if (data == null || data.isEmpty()) {
			data = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		}
		
		return new FrequenciaEntity(usuario.get(), disciplina.get(), data);
	}
	
}
