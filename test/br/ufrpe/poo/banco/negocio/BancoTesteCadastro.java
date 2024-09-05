package br.ufrpe.poo.banco.negocio;

import static org.mockito.Mockito.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import br.ufrpe.poo.banco.dados.IRepositorioClientes;
import br.ufrpe.poo.banco.dados.IRepositorioContas;
import br.ufrpe.poo.banco.exceptions.ClienteJaCadastradoException;
import br.ufrpe.poo.banco.exceptions.RepositorioException;

public class BancoTesteCadastro {

    private IRepositorioClientes repositorioClientesMock;
    private IRepositorioContas repositorioContasMock;
    private Banco banco;
    private Cliente cliente;

    @Before
    public void setUp() {
        repositorioClientesMock = Mockito.mock(IRepositorioClientes.class);
        banco = new Banco(repositorioClientesMock, repositorioContasMock);
        cliente = new Cliente("Caio", "12345678900");
    }

    @Test
    public void testCadastrarClienteComSucesso() throws RepositorioException, ClienteJaCadastradoException {
        when(repositorioClientesMock.inserir(cliente)).thenReturn(true);
        banco.cadastrarCliente(cliente);
        verify(repositorioClientesMock).inserir(cliente);
    }

    @Test(expected = ClienteJaCadastradoException.class)
    public void testCadastrarClienteClienteJaCadastrado() throws RepositorioException, ClienteJaCadastradoException {
        when(repositorioClientesMock.inserir(cliente)).thenReturn(false);
        banco.cadastrarCliente(cliente);
    }
}
