package br.com.pizzaria.service;

import java.time.LocalDate;

import br.com.pizzaria.domain.dto.ClientesDto;

public interface ClientesService {


	ClientesDto buscarPorNome(String nome);

	ClientesDto buscaPorDataNascimento(LocalDate data);

	void inserir(ClientesDto dto);

	void deletar(int id);

}
