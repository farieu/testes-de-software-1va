package br.ufrpe.poo.banco.negocio;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import br.ufrpe.poo.banco.dados.IRepositorioClientes;
import br.ufrpe.poo.banco.dados.IRepositorioContas;
import br.ufrpe.poo.banco.exceptions.RepositorioException;
import br.ufrpe.poo.banco.exceptions.SaldoInsuficienteException;
import br.ufrpe.poo.banco.exceptions.ValorInvalidoException;

public class BancoBranches {
//Testes extras que cobrem as Branches missed do banco.
    private Banco banco;
    private IRepositorioContas repositorioContas;
    private ContaAbstrata conta;

    @Before
    public void setUp() throws Exception {
        repositorioContas = mock(IRepositorioContas.class);
        banco = new Banco(mock(IRepositorioClientes.class), repositorioContas);
        conta = mock(ContaAbstrata.class);
    }

    @Test(expected = ValorInvalidoException.class)
    public void testDebitarValorNegativo() throws RepositorioException, SaldoInsuficienteException, ValorInvalidoException {
        banco.debitar(conta, -100.0);
    }

    @Test
    public void testDebitarValorValido() throws RepositorioException, SaldoInsuficienteException, ValorInvalidoException {
        when(conta.getNumero()).thenReturn("123");
        when(repositorioContas.existe("123")).thenReturn(true);

        banco.debitar(conta, 100.0);

        verify(conta).debitar(100.0);
        verify(repositorioContas).atualizar(conta);
    }
    
    @Test
    public void testDebitarContaNaoExistente() throws RepositorioException, SaldoInsuficienteException, ValorInvalidoException {
        when(conta.getNumero()).thenReturn("123");
        when(repositorioContas.existe("123")).thenReturn(false);

        banco.debitar(conta, 100.0);

        // Verifica se o método debitar da conta não foi chamado
        verify(conta, never()).debitar(100.0);
        // Verifica se o método atualizar do repositório não foi chamado
        verify(repositorioContas, never()).atualizar(conta);
    }

}
