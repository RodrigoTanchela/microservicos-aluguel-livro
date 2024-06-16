package com.rodrigo.book.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rodrigo.book.infrastructure.entity.BookEntity;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {
    
    @Modifying
    @Query("UPDATE BookEntity b SET b.disponivel = :disponivel WHERE b.id = :id")
    void disponibilizar(@Param("id") Long id, @Param("disponivel") Boolean disponivel);
}
