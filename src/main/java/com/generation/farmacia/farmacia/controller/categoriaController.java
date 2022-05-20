package com.generation.farmacia.farmacia.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.generation.farmacia.farmacia.model.Categoria;
import com.generation.farmacia.farmacia.repository.categoriaRepository;

@RestController
@RequestMapping ("/categorias")
@CrossOrigin ("*")
public class categoriaController {

	@Autowired
	private categoriaRepository categoria; 
	
	@GetMapping
	public ResponseEntity<List<Categoria>> GetAll() {
		return ResponseEntity.ok(categoria.findAll()); 
		
	}
		
	@GetMapping ("/{id}")
	public ResponseEntity<Categoria> getById(@PathVariable Long id){
		return categoria.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
		
	@GetMapping("/funcao/{funcao}")
	public ResponseEntity<List<Categoria>> getByTitulo(@PathVariable String funcao){
		return ResponseEntity.ok(categoria.findAllByFuncaoContainingIgnoreCase(funcao));
		
	}
	
	@PostMapping
	public ResponseEntity<Categoria> post(@Valid @RequestBody Categoria categorias){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(categoria.save(categorias));
	}
	
	@PutMapping
	public ResponseEntity<Categoria> put(@RequestBody Categoria categorias){
		return categoria.findById(categorias.getId())
				.map(resposta -> ResponseEntity.status(HttpStatus.OK)
					.body(categoria.save(categorias)))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		java.util.Optional<Categoria> categoriaObjeto = categoria.findById(id);
		
		if (categoriaObjeto.isEmpty())
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
		categoria.deleteById(id);
		
	}
}
