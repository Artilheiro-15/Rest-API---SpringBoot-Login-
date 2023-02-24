package com.atilla_jr.rest_ap.resorces;

import com.atilla_jr.rest_ap.domain.Pessoa;
import com.atilla_jr.rest_ap.dto.PessoaDTO;
import com.atilla_jr.services.PessoaService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/pessoa")
public class PessoaResource {

  @Autowired
  private PessoaService service;

  @RequestMapping(method = RequestMethod.GET)
  public ResponseEntity<List<PessoaDTO>> findAll() {
    List<Pessoa> list = (List<Pessoa>) service.findAll();
    List<PessoaDTO> listDto = list
      .stream()
      .map(x -> new PessoaDTO(x))
      .collect(Collectors.toList());
    return ResponseEntity.ok().body(listDto);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public ResponseEntity<PessoaDTO> findById(@PathVariable String id) {
    Pessoa obj = service.findById(id);
    return ResponseEntity.ok().body(new PessoaDTO(obj));
  }
}
