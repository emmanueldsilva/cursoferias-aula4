package matera.systems.cursoferias2018.api.domain.entity;

public class FrequenciaEntity {
	
	private final UsuarioEntity usuario;
	
	private final DisciplinaEntity disciplina;
	
	private final String data;
	
	public FrequenciaEntity(UsuarioEntity usuario, DisciplinaEntity disciplina, String data) {
		this.usuario = usuario;
		this.disciplina = disciplina;
		this.data = data;
	}

	public UsuarioEntity getUsuario() {
		return usuario;
	}

	public DisciplinaEntity getDisciplina() {
		return disciplina;
	}

	public String getData() {
		return data;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((disciplina == null) ? 0 : disciplina.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FrequenciaEntity other = (FrequenciaEntity) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (disciplina == null) {
			if (other.disciplina != null)
				return false;
		} else if (!disciplina.equals(other.disciplina))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}
	
}
