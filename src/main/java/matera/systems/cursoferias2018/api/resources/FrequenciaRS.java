package matera.systems.cursoferias2018.api.resources;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import matera.systems.cursoferias2018.api.services.FrequenciaService;

@RestController
@RequestMapping(path = "/frequencia")
public class FrequenciaRS {
	
	@Autowired
	private FrequenciaService frequenciaService;
	
	@PutMapping(path = "/{disciplinaId}/{alunoId}")
	public ResponseEntity<Void> marcarPresenca(@PathVariable(required = true) String disciplinaId, 
											   @PathVariable(required = true) String alunoId, 
											   @RequestParam(required = false) String data) {
		try {
			frequenciaService.setIn(UUID.fromString(disciplinaId), UUID.fromString(alunoId), data);
			return ResponseEntity.noContent().build();
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@DeleteMapping(path = "/{disciplinaId}/{alunoId}")
	public ResponseEntity<Void> removerPresenca(@PathVariable(required = true) String disciplinaId, 
											    @PathVariable(required = true) String alunoId, 
											    @RequestParam(required = false) String data) {
		try {
			frequenciaService.setOut(UUID.fromString(disciplinaId), UUID.fromString(alunoId), data);
			return ResponseEntity.noContent().build();
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
}

