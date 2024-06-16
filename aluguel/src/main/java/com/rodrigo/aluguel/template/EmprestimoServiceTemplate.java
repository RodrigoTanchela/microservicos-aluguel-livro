package com.rodrigo.aluguel.template;

import com.rodrigo.aluguel.model.Emprestimo;

public abstract class EmprestimoServiceTemplate {
	
	public final Emprestimo atualizar(Emprestimo emprestimo) {
        validar(emprestimo);
        configurarInformacoes(emprestimo);
        return salvar(emprestimo);
    }

	protected void validar(Emprestimo emprestimo) {
	}

    protected abstract void configurarInformacoes(Emprestimo emprestimo);

    protected abstract Emprestimo salvar(Emprestimo emprestimo);

}
