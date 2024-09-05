package br.ufrpe.poo.banco.negocio;


import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import br.ufrpe.poo.banco.dados.IRepositorioClientes;
import br.ufrpe.poo.banco.exceptions.AtualizacaoNaoRealizadaException;
import br.ufrpe.poo.banco.exceptions.RepositorioException;

/**
 * Teste para public void atualizarCliente
 * 
 */

public class BancoTest {

    private Banco banco;
    private IRepositorioClientes repositorioClientesMock;
    private Cliente cliente;

    @Before
    public void setUp() throws RepositorioException {
        repositorioClientesMock = Mockito.mock(IRepositorioClientes.class);
        banco = new Banco(repositorioClientesMock, null);
        cliente = new Cliente("João", "12345678900");
    }

    @Test
    public void testAtualizarClienteComSucesso() throws RepositorioException, AtualizacaoNaoRealizadaException {
        when(repositorioClientesMock.atualizar(cliente)).thenReturn(true);
        banco.atualizarCliente(cliente);
        Mockito.verify(repositorioClientesMock).atualizar(cliente);
        System.out.println("Cliente atualizado com sucesso.");
    }

    @Test
    public void testAtualizarClienteComErro() throws RepositorioException {
        when(repositorioClientesMock.atualizar(cliente)).thenReturn(false);
        assertThrows(AtualizacaoNaoRealizadaException.class, () -> {
            banco.atualizarCliente(cliente);
        });
    }
}
