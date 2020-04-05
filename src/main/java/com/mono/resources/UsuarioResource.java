package com.mono.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mono.domain.Produto;
import com.mono.domain.Usuario;
import com.mono.services.UsuarioService;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioResource {

	@Autowired
	private UsuarioService service;

	@GetMapping
	public ResponseEntity<List<Usuario>> buscarTodos() {
		List<Usuario> list = service.buscarTodos();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Usuario> buscar(@PathVariable Integer id) {
		Usuario obj = service.buscar(id);
		return ResponseEntity.ok().body(obj);
	}

	@GetMapping(value = "/{email}/{senha}")
	public ResponseEntity<Usuario> buscarPorEmailSenha(@PathVariable String email, @PathVariable String senha) {
		Usuario obj = service.buscarEmailSenha(email, senha);
		return ResponseEntity.ok().body(obj);
	}

	@PostMapping
	public ResponseEntity<?> inserir(@RequestBody Usuario obj) {
		obj = service.inserir(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping(value = "/produtos/{idUser}")
	public ResponseEntity<Usuario> inserirProduto(@PathVariable Integer idUser, @RequestBody Produto prod) {
		Usuario obj = service.inserirProduto(idUser, prod);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> atualizar(@RequestBody Usuario obj, @PathVariable Integer id) {
		obj.setId(id);
		obj = service.atualizar(obj);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Integer id) {
		service.deletar(id);
		return ResponseEntity.noContent().build();
	}
}
