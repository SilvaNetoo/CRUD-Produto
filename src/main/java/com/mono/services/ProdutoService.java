package com.mono.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mono.domain.Produto;
import com.mono.repositories.ProdutoRepository;
import com.mono.services.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repo;

	public Produto buscar(Integer id) {
		Optional<Produto> prod = repo.findById(id);
		return prod.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Produto.class.getName()));
	}

	public Produto inserir(Produto prod) {
		prod.setId(null);
		return repo.save(prod);
	}

	public Produto atualizar(Produto prod) {
		buscar(prod.getId());
		return repo.save(prod);
	}
	
	public void deletar(Integer id) {
		buscar(id);
		repo.deleteById(id);
	}

}
