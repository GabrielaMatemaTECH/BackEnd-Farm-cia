package com.generation.farmacia.farmacia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.generation.farmacia.farmacia.model.Categoria;

public interface categoriaRepository extends JpaRepository <Categoria, Long>{

	public List <Categoria> findAllByFuncaoContainingIgnoreCase(@Param("funcao") String funcao);
}
