package com.rodrigo.book.mapper;

import com.rodrigo.book.domain.Book;
import com.rodrigo.book.domain.dto.BookDto;

public class BookMapper {
	
	public static BookDto convertToDto(Book Book) {
		BookDto BookDto = new BookDto(Book.getId(), Book.getTitulo(), Book.getDescricao(), Book.getPreco(), Book.getDigital(), Book.getDisponivel(), Book.getDataDisponivel());
		return BookDto;
	}
	
	public static Book convertToModel(BookDto BookDto) {
		 if (BookDto == null) {
	            return null;
	        }

	        Book book = new Book();
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
