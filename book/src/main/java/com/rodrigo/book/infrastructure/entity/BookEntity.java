package com.rodrigo.book.infrastructure.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "livro")
public class BookEntity implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "titulo", nullable = false, length = 50)
    private String titulo;

    @Column(name = "descricao", nullable = true, length = 255)
    private String descricao;

    @Column(name = "digital")
    private Boolean digital;

    @Column(name = "disponivel", columnDefinition = "BOOLEAN DEFAULT true")
    private Boolean disponivel;

    @Column(name = "dataDisponivel", nullable = true)
    private Date dataDisponivel;

    @Column(name = "preco", nullable = true, precision = 2)
    private Double preco;

    public BookEntity() {
        super();
    }
    
    public BookEntity(Long id, String titulo, String descricao, Boolean digital, Boolean disponivel, Date dataDisponivel, Double preco) {
        super();
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.digital = digital;
        this.disponivel = disponivel;
        this.dataDisponivel = dataDisponivel;
        this.preco = preco;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
    
    public Boolean getDigital() {
        return digital;
    }

    public void setDigital(Boolean digital) {
        this.digital = digital;
    }

    public Boolean getDisponivel() {
        return disponivel;
    }

    public void setDisponivel(Boolean disponivel) {
        this.disponivel = disponivel;
    }

    public Date getDataDisponivel() {
        return dataDisponivel;
    }

    public void setDataDisponivel(Date dataDisponivel) {
        this.dataDisponivel = dataDisponivel;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dataDisponivel, descricao, disponivel, digital, id, preco, titulo);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        BookEntity other = (BookEntity) obj;
        return Objects.equals(dataDisponivel, other.dataDisponivel) && Objects.equals(descricao, other.descricao)
                && Objects.equals(disponivel, other.disponivel) && Objects.equals(digital, other.digital)
                && Objects.equals(id, other.id) && Objects.equals(preco, other.preco)
                && Objects.equals(titulo, other.titulo);
    }
}
