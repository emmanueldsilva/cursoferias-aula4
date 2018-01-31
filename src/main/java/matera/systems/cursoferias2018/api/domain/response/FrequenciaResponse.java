package matera.systems.cursoferias2018.api.domain.response;

import java.util.ArrayList;
import java.util.List;

public class FrequenciaResponse {

	private UsuarioResponse usuarioResponse;
	
	private DisciplinaResponse disciplinaResponse;
	
	private List<String> datas = new ArrayList<>();

	public UsuarioResponse getUsuarioResponse() {
		return usuarioResponse;
	}

	public void setUsuarioResponse(UsuarioResponse usuarioResponse) {
		this.usuarioResponse = usuarioResponse;
	}

	public DisciplinaResponse getDisciplinaResponse() {
		return disciplinaResponse;
	}

	public void setDisciplinaResponse(DisciplinaResponse disciplinaResponse) {
		this.disciplinaResponse = disciplinaResponse;
	}

	public List<String> getDatas() {
		return datas;
	}

	public void setDatas(List<String> datas) {
		this.datas = datas;
	}
	
}
