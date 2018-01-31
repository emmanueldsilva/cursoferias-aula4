package matera.systems.cursoferias2018.api.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import matera.systems.cursoferias2018.api.domain.entity.DisciplinaEntity;
import matera.systems.cursoferias2018.api.domain.entity.FrequenciaEntity;
import matera.systems.cursoferias2018.api.domain.entity.UsuarioEntity;

@Repository
@Profile("stub")
public class FrequenciaRepositoryStub implements FrequenciaRepository {
	
	private Map<Integer, FrequenciaEntity> frequencias = new HashMap<>();
	
	@Override
	public void setIn(FrequenciaEntity frequencia) {
		frequencias.put(frequencia.hashCode(), frequencia);
	}
	
	@Override
	public FrequenciaEntity setOut(FrequenciaEntity frequencia) {
		return frequencias.remove(frequencia.hashCode());
	}

	@Override
	public List<FrequenciaEntity> list(DisciplinaEntity disciplina, UsuarioEntity usuario) {
		return frequencias.values()
				   		  .stream()
				   		  .filter(f-> f.getDisciplina().equals(disciplina) && f.getUsuario().equals(usuario))
				   		  .collect(Collectors.toList());
	}
	
}
