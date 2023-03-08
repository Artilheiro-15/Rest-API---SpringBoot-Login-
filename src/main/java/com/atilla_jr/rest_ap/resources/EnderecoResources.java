package com.atilla_jr.rest_ap.resources;

import com.atilla_jr.rest_ap.domain.Endereco;
import com.atilla_jr.rest_ap.dto.EnderecoDTO;
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
    List<Endereco> list = (List<Endereco>) service.findAll();
    List<EnderecoDTO> listDto = list
      .stream()
      .map(x -> new EnderecoDTO(x))
      .collect(Collectors.toList());
    return ResponseEntity.ok().body(listDto);
  }

  @RequestMapping(method = RequestMethod.POST)
  public ResponseEntity<Endereco> save(@RequestBody EnderecoDTO objDto) {
    Endereco obj = service.fromDTO(objDto);
    obj = service.save(obj);
    return ResponseEntity.ok().body(obj);
  }

  //==================================================================================
  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<String> delete(@PathVariable String id) {
    service.delete(id);
    return ResponseEntity
      .ok()
      .body("Endereço " + id + " Deletado com sucesso!");
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
  public ResponseEntity<Endereco> update(
    @RequestBody EnderecoDTO objDto,
    @PathVariable Integer id
  ) {
    Endereco obj = service.fromDTO(objDto);
    obj.setId(id);
    obj = service.update(obj, obj);
    return ResponseEntity.ok().body(obj);
  }
}
