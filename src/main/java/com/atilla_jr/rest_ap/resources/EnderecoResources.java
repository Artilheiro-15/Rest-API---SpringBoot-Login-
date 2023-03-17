package com.atilla_jr.rest_ap.resources;

import com.atilla_jr.rest_ap.domain.Endereco;
import com.atilla_jr.rest_ap.dto.EnderecoDTO;
import com.atilla_jr.rest_ap.exception.ObjectNotFoundException;
import com.atilla_jr.rest_ap.services.EnderecoServices;
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
@RequestMapping(value = "/endereco")
public class EnderecoResources {

  @Autowired
  private EnderecoServices service;

  @RequestMapping(method = RequestMethod.GET)
  public ResponseEntity<List<EnderecoDTO>> findAll() {
    try {
      List<Endereco> list = (List<Endereco>) service.findAll();
      List<EnderecoDTO> listDto = list
        .stream()
        .map(x -> new EnderecoDTO(x))
        .collect(Collectors.toList());
      return ResponseEntity.ok().body(listDto);
    } catch (ObjectNotFoundException e) {
      return ResponseEntity.notFound().build();
    }
  }

  //-----------------------------------------------------------------

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public ResponseEntity<EnderecoDTO> findById(@PathVariable String id) {
    try {
      Endereco obj = service.findById(id);
      EnderecoDTO dto = new EnderecoDTO(obj);
      return ResponseEntity.ok().body(dto);
    } catch (ObjectNotFoundException e) {
      return ResponseEntity.notFound().build();
    }
  }

  @RequestMapping(method = RequestMethod.POST)
  public ResponseEntity<Endereco> save(@RequestBody EnderecoDTO objDto) {
    try {
      Endereco obj = service.fromDTO(objDto);
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
      return ResponseEntity.ok().body("Id: " + id + " Do Endereço Deletado!");
    } catch (Exception e) {
      return ResponseEntity
        .badRequest()
        .body(
          "Aconteceu um erro ao tentar deletar o endereço " + e.getMessage()
        );
    }
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
  public ResponseEntity<Endereco> update(
    @RequestBody EnderecoDTO objDto,
    @PathVariable Integer id
  ) {
    try {
      Endereco obj = service.fromDTO(objDto);
      obj.setId(id);
      obj = service.update(obj, obj);
      return ResponseEntity.ok().body(obj);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(null);
    }
  }
}
