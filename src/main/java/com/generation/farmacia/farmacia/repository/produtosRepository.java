package com.generation.farmacia.farmacia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.generation.farmacia.farmacia.model.Produtos;

public interface produtosRepository extends JpaRepository <Produtos, Long>{
	public List <Produtos> findAllByNomeContainingIgnoreCase(@Param("nome") String nome);
	
}
