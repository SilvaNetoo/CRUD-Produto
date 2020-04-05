package com.mono.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mono.domain.Carrinho;

@Repository
public interface CarrinhoRepository extends JpaRepository<Carrinho, Integer> {

}
