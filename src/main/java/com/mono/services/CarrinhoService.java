package com.mono.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mono.domain.Carrinho;
import com.mono.domain.Produto;
import com.mono.repositories.CarrinhoRepository;
import com.mono.services.exceptions.ObjectNotFoundException;

@Service
public class CarrinhoService {

	@Autowired
	private CarrinhoRepository repo;
	
	public List<Carrinho> buscarTodos() {
		return repo.findAll();
	}

	public Carrinho buscar(Integer id) {
		Optional<Carrinho> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Produto.class.getName()));
	}

	public Carrinho inserir(Carrinho obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	public Carrinho atualizar(Carrinho obj) {
		buscar(obj.getId());
		return repo.save(obj);
	}
	
	public void deletar(Integer id) {
		buscar(id);
		repo.deleteById(id);
	}

}
