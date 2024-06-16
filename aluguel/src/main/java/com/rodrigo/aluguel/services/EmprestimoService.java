package com.rodrigo.aluguel.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rodrigo.aluguel.exceptions.ResourceNotFoundExcpetion;
import com.rodrigo.aluguel.model.Emprestimo;
import com.rodrigo.aluguel.repository.EmprestimoRepository;
import com.rodrigo.aluguel.template.EmprestimoServiceTemplate;

import jakarta.transaction.Transactional;

@Service
public class EmprestimoService extends EmprestimoServiceTemplate {
    
    @Autowired
    private EmprestimoRepository emprestimoRepository;
    
    public Emprestimo buscaId(Long id) {
        return emprestimoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundExcpetion("Nenhum registro encontrado para este ID"));
    }
    
    public List<Emprestimo> buscarTodos() {
        return emprestimoRepository.findAll();
    }
    
    
    public Emprestimo inserir(Emprestimo emprestimo) {
         return emprestimoRepository.save(emprestimo);
    }
    
    public void deletar(Long id) {
        var entity = emprestimoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundExcpetion("Nenhum registro encontrado para este ID"));
        emprestimoRepository.delete(entity);
    }

    @Override
    protected void configurarInformacoes(Emprestimo emprestimo) {
        Emprestimo emprestimoData = emprestimoRepository.findById(emprestimo.getId())
                .orElseThrow(() -> new ResourceNotFoundExcpetion("Nenhum registro encontrado para este ID"));
        emprestimoData.setId(emprestimo.getId());
        emprestimoData.setTitulo(emprestimo.getTitulo());
        emprestimoData.setInformacoes(emprestimo.getInformacoes());
        emprestimoData.setIdusuario(emprestimo.getIdusuario());
        emprestimoData.setDiaInicio(emprestimo.getDiaInicio());
        emprestimoData.setDiaTermino(emprestimo.getDiaTermino());
        emprestimoData.setDiaDevolucao(emprestimo.getDiaDevolucao());   
    }

    @Override
    protected Emprestimo salvar(Emprestimo emprestimo) {
        return emprestimoRepository.save(emprestimo);
    }
    
    @Transactional
    public Emprestimo devolucaoEmprestimo(Long id) {    
        emprestimoRepository.devolucaoEmprestimo(id, new Date());
        var entity = emprestimoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundExcpetion("Nenhum registro encontrado para este ID!"));
        return entity;
    }
}
