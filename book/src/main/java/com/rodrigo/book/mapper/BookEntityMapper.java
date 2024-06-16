package com.rodrigo.book.mapper;

import com.rodrigo.book.domain.dto.BookDto;
import com.rodrigo.book.infrastructure.entity.BookEntity;

public class BookEntityMapper {
	
	public static BookDto convertToDto(BookEntity Book) {
		BookDto BookDto = new BookDto(Book.getId(), Book.getTitulo(), Book.getDescricao(), Book.getPreco(), Book.getDigital(), Book.getDisponivel(), Book.getDataDisponivel());
		return BookDto;
	}
	
	public static BookEntity convertToModel(BookDto BookDto) {
		 if (BookDto == null) {
	            return null;
	        }

		 	BookEntity book = new BookEntity();
	        book.setId(BookDto.id());
	        book.setTitulo(BookDto.titulo());
	        book.setDescricao(BookDto.descricao());
	        book.setPreco(BookDto.preco());
	        book.setDisponivel(BookDto.disponivel());
	        book.setDigital(BookDto.digital());
	        book.setDataDisponivel(BookDto.dataDisponivel());
			
	        return book;
	    
	}
}
