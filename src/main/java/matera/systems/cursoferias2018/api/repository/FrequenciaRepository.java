package matera.systems.cursoferias2018.api.repository;

import java.util.List;

import matera.systems.cursoferias2018.api.domain.entity.DisciplinaEntity;
import matera.systems.cursoferias2018.api.domain.entity.FrequenciaEntity;
import matera.systems.cursoferias2018.api.domain.entity.UsuarioEntity;

public interface FrequenciaRepository {
	
	void setIn(FrequenciaEntity frequencia);
	
	FrequenciaEntity setOut(FrequenciaEntity frequencia);
	
	List<FrequenciaEntity> list(DisciplinaEntity disciplina, UsuarioEntity usuario);
	
}
