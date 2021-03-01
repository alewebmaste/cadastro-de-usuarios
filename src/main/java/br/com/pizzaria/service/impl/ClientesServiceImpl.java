package br.com.pizzaria.service.impl;

import java.time.LocalDate;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pizzaria.domain.dto.ClientesDto;
import br.com.pizzaria.domain.entity.Cliente;
import br.com.pizzaria.domain.exception.ClienteNaoEncontradoException;
import br.com.pizzaria.repository.ClientesRepository;
import br.com.pizzaria.service.ClientesService;
import br.com.pizzaria.util.ClientesConverter;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClientesServiceImpl implements ClientesService {
	
	@Autowired
	ServletContext context;

	private final ClientesRepository repository;
	
	int total = 0;	
	
	@Override
	public ClientesDto buscarPorNome(String nome) {

		Cliente c = repository.findByNome(nome);

		if (c == null) {
			throw new ClienteNaoEncontradoException("O cliente " + nome + " não existe");
		}	

		return ClientesConverter.clienteDtoBuilder(c);
	}

	@Override
	public ClientesDto buscaPorDataNascimento(LocalDate data) {

		Cliente c = repository.findByDataNascimento(data);

		if (c == null) {
			throw new ClienteNaoEncontradoException("Não existem clientes nascidos em " + data);
		}

		return ClientesConverter.clienteDtoBuilder(c);

	}

	@Override
	public void inserir(ClientesDto dto) {

		try {
			repository.save(ClientesConverter.clienteBuilder(dto));
		} catch (Exception ex) {
			throw new IllegalArgumentException(ex.getCause().getCause().getMessage());
		}

	}

	@Override
	public void deletar(int id) {

		try {
			repository.deleteById(id);
		}catch (Exception e) {
			throw new ClienteNaoEncontradoException("O cliente número " + id + " não existe");
		}

	}

}
