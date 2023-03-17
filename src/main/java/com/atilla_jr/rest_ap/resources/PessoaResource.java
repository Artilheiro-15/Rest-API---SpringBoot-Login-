package com.atilla_jr.rest_ap.resources;

import com.atilla_jr.rest_ap.domain.Pessoa;
import com.atilla_jr.rest_ap.dto.PessoaDTO;
import com.atilla_jr.rest_ap.exception.ObjectNotFoundException;
import com.atilla_jr.rest_ap.services.PessoaService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/pessoas")
public class PessoaResource {

  @Autowired
  private PessoaService service;

  @RequestMapping(method = RequestMethod.GET)
  public ResponseEntity<List<PessoaDTO>> findAll() {
    try {
      List<Pessoa> list = (List<Pessoa>) service.findAll();
      List<PessoaDTO> listDto = list
        .stream()
        .map(x -> new PessoaDTO(x))
        .collect(Collectors.toList());
      return ResponseEntity.ok().body(listDto);
    } catch (ObjectNotFoundException e) {
      return ResponseEntity.notFound().build();
    }
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public ResponseEntity<PessoaDTO> findById(@PathVariable String id) {
    try {
      Pessoa obj = service.findById(id);
      return ResponseEntity.ok().body(new PessoaDTO(obj));
    } catch (ObjectNotFoundException e) {
      return ResponseEntity.notFound().build();
    }
  }

  @RequestMapping(method = RequestMethod.POST)
  public ResponseEntity<Pessoa> save(@RequestBody PessoaDTO objDto) {
    try {
      Pessoa obj = service.fromDTO(objDto);
      obj = service.save(obj);

      return ResponseEntity.ok().body(obj);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(null);
    }
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<String> delete(@PathVariable String id) {
    try {
      service.delete(id);
      return ResponseEntity
        .ok()
        .body("Pessoa do Id " + id + " Deletado com sucesso!");
    } catch (Exception e) {
      return ResponseEntity
        .badRequest()
        .body("Aconteceu um erro ao tentar deletar " + e.getMessage());
    }
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
  public ResponseEntity<Pessoa> String(
    @RequestBody PessoaDTO objDto,
    @PathVariable Integer id
  ) {
    try {
      Pessoa obj = service.fromDTO(objDto);
      obj.setId(id);
      obj = service.update(obj, obj);
      return ResponseEntity.ok().body(obj);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(null);
    }
  }
}
