package com.mono.services;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mono.domain.Carrinho;
import com.mono.domain.Produto;
import com.mono.domain.Usuario;
import com.mono.repositories.CarrinhoRepository;
import com.mono.repositories.UsuarioRepository;
import com.mono.services.exceptions.ObjectNotFoundException;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repo;

	@Autowired
	private ProdutoService prodService;

	@Autowired
	private CarrinhoService carrinhoService;

	public List<Usuario> buscarTodos() {
		return repo.findAll();
	}

	public Usuario buscar(Integer id) {
		Optional<Usuario> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Produto.class.getName()));
	}

	public Usuario buscarEmailSenha(String email, String senha) {
		Usuario obj = repo.findUser(email, senha);
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado");
		}
		return obj;
	}

	public Usuario inserir(Usuario obj) {
		obj.setId(null);
		Carrinho c = new Carrinho(null, obj);
		obj.setCarrinho(c);
		carrinhoService.inserir(obj.getCarrinho());
		return repo.save(obj);
	}

	public Usuario inserirProduto(Integer idUser, Produto prod) {
		Usuario obj = buscar(idUser);
		Produto p = prodService.buscar(prod.getId());
		Carrinho c = carrinhoService.buscar(obj.getCarrinho().getId());
		p.setCarrinho(c);
		obj.getCarrinho().setProdutos(p);
		
		return repo.save(obj);
	}

	public Usuario atualizar(Usuario obj) {
		buscar(obj.getId());
		return repo.save(obj);
	}

	public void deletar(Integer id) {
		buscar(id);
		repo.deleteById(id);
	}

}
