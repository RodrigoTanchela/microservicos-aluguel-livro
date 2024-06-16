package com.rodrigo.multa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rodrigo.multa.exceptions.ResourceNotFoundExcpetion;
import com.rodrigo.multa.model.Multa;
import com.rodrigo.multa.repository.MultaRepository;
import com.rodrigo.multa.utils.MultaMapper;

@Service
public class MultaService {
	
	@Autowired
	private MultaRepository MultaRepository;
	
	private final CalculadoraMultaService calculadoraMultaService;

    MultaService(CalculadoraMultaService calculadoraMultaService) {
        this.calculadoraMultaService = calculadoraMultaService;
    }

	public Multa buscaId(Long id) {
		return MultaRepository.findById(id).orElseThrow(()-> new ResourceNotFoundExcpetion("Nenhum registro encontrado para este ID"));
	}
	
	public List<Multa> buscarTodos() {
		return MultaRepository.findAll();
	}
	
	
	public Multa inserir(Multa multa) {
		 return MultaRepository.save(multa);
	}
	
	public Multa atualizar(Multa multa) {
		var multaData = MultaRepository.findById(multa.getId()).orElseThrow(()-> new ResourceNotFoundExcpetion("Nenhum registro encontrado para este ID"));
		multaData.setId(multa.getId());
		multaData.setTipo(multa.getTipo());
		multaData.setValorDiario(multa.getValorDiario());
		
		return MultaRepository.save(multaData);
	}

	public void deletar(Long id) {
		var entity = MultaRepository.findById(id).orElseThrow(()-> new ResourceNotFoundExcpetion("Nenhum registro encontrado para este ID"));
		MultaRepository.delete(entity);
	}
	
	public double calcularMultaPorAtraso(Long id, int diasAtraso) {
		Multa multa = MultaRepository.findById(id).orElseThrow(()-> new ResourceNotFoundExcpetion("Nenhum registro encontrado para este ID"));
		CalculadoraMultaService calculo =  MultaMapper.getCalculadoraService(multa.getTipo());
		return multa.multar(diasAtraso, calculo);
    }
}
