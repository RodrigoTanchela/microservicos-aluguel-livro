package com.rodrigo.book.domain.adaptadores.services;

import java.util.List;
import java.util.stream.Collectors;

import com.rodrigo.book.domain.Book;
import com.rodrigo.book.domain.dto.BookDto;
import com.rodrigo.book.domain.porta.interfaces.BookServicePort;
import com.rodrigo.book.domain.porta.repositories.BookRepositoryPort;
import com.rodrigo.book.exceptions.ResourceNotFoundExcpetion;
import com.rodrigo.book.mapper.BookMapper;

public class BookServiceImp implements BookServicePort {

	private final BookRepositoryPort bookRepository;

	public BookServiceImp(BookRepositoryPort bookRepository) {
		super();
		this.bookRepository = bookRepository;
	}

	@Override
	public BookDto buscaId(Long id) {
		if (id == null) {
			throw new ResourceNotFoundExcpetion("Livro não encontrado");
		}
		var entity = bookRepository.buscaId(id);
		var Dto = BookMapper.convertToDto(entity);
		return Dto;
	}

	@Override
	public List<BookDto> buscarTodos() {
		return bookRepository.buscarTodos().stream().map(BookMapper::convertToDto).collect(Collectors.toList());
	}

	@Override
	public BookDto inserir(BookDto bookDto) {
		return BookMapper.convertToDto(bookRepository.save(BookMapper.convertToModel(bookDto)));
	}

	@Override
	public BookDto atualizar(BookDto bookDto) {
		Book bookData = bookRepository.buscaId(bookDto.id());
		bookData.setId(bookDto.id());
		bookData.setTitulo(bookDto.titulo());
		bookData.setDescricao(bookDto.descricao());
		bookData.setPreco(bookDto.preco());
		bookData.setDisponivel(bookDto.disponivel());
		bookData.setDigital(bookDto.digital());
		bookData.setDataDisponivel(bookDto.dataDisponivel());

		bookRepository.save(bookData);
		var Dto = BookMapper.convertToDto(bookRepository.save(bookData));
		return Dto;
	}

	@Override
	public void deletar(Long id) {
		var entity = bookRepository.buscaId(id);
		bookRepository.deletar(entity);
	}

	@Override
	public BookDto disponibilizar(Long id) {
		if (id == null) {
			throw new ResourceNotFoundExcpetion("Livro não encontrado");
		}
		var entity = bookRepository.buscaId(id);
		bookRepository.disponibilizar(id, !entity.getDisponivel());
		return BookMapper.convertToDto(entity);
	}

}
