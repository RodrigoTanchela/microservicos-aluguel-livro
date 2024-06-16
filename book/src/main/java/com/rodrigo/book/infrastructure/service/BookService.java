package com.rodrigo.book.infrastructure.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rodrigo.book.domain.dto.BookDto;
import com.rodrigo.book.domain.porta.interfaces.BookServicePort;
import com.rodrigo.book.exceptions.ResourceNotFoundExcpetion;
import com.rodrigo.book.infrastructure.entity.BookEntity;
import com.rodrigo.book.infrastructure.repository.BookRepository;
import com.rodrigo.book.mapper.BookEntityMapper;

@Service
public class BookService implements BookServicePort {
    
    @Autowired
    private BookRepository bookRepository;
    
    public BookDto buscaId(Long id) {
        var entity = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundExcpetion("Nenhum registro encontrado para este ID"));
        return BookEntityMapper.convertToDto(entity);
    }
    
    public List<BookDto> buscarTodos() {
        return bookRepository.findAll()
                .stream()
                .map(BookEntityMapper::convertToDto)
                .collect(Collectors.toList());
    }
    
    public BookDto inserir(BookDto bookDto) {
        return BookEntityMapper.convertToDto(bookRepository.save(BookEntityMapper.convertToModel(bookDto)));
    }
    
    public BookDto atualizar(BookDto book) {
        var bookData = bookRepository.findById(book.id()).orElseThrow(() -> new ResourceNotFoundExcpetion("Nenhum registro encontrado para este ID"));
        bookData.setId(book.id());
        bookData.setTitulo(book.titulo());
        bookData.setDescricao(book.descricao());
        bookData.setPreco(book.preco());
        bookData.setDisponivel(book.disponivel());
        bookData.setDigital(book.digital());
        bookData.setDataDisponivel(book.dataDisponivel());
        
        return BookEntityMapper.convertToDto(bookRepository.save(bookData));
    }

    public void deletar(Long id) {
        var entity = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundExcpetion("Nenhum registro encontrado para este ID"));
        bookRepository.delete(entity);
    }
    
    @Transactional
    @Modifying
    @Override
    public BookDto disponibilizar(Long id) {
        var entity = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundExcpetion("Nenhum registro encontrado para este ID"));
        entity.setDisponivel(!entity.getDisponivel());
        bookRepository.save(entity);
        return BookEntityMapper.convertToDto(entity);
    }
}
