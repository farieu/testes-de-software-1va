package br.ufrpe.poo.banco.negocio;

import br.ufrpe.poo.banco.dados.IRepositorioClientes;
import br.ufrpe.poo.banco.dados.IRepositorioContas;
import br.ufrpe.poo.banco.exceptions.ClienteJaPossuiContaException;
import br.ufrpe.poo.banco.exceptions.ClienteNaoCadastradoException;
import br.ufrpe.poo.banco.exceptions.ContaJaAssociadaException;
import br.ufrpe.poo.banco.exceptions.RepositorioException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;

//Testes para o método associar conta.
public class BancoTest3 {

    private Banco banco;
    private IRepositorioClientes repositorioClientesMock;
    private IRepositorioContas repositorioContasMock;
    private Cliente clienteMock;
    private ContaAbstrata contaMock;

    @Before
    public void setUp() throws RepositorioException {
        repositorioClientesMock = Mockito.mock(IRepositorioClientes.class);
        repositorioContasMock = Mockito.mock(IRepositorioContas.class);
        banco = new Banco(repositorioClientesMock, repositorioContasMock);
        clienteMock = Mockito.mock(Cliente.class);
        contaMock = Mockito.mock(ContaAbstrata.class);

        // Configurações comuns para os testes
        when(clienteMock.getCpf()).thenReturn("12345678900");
        when(contaMock.getNumero()).thenReturn("5");
    }

    @Test
    public void testAssociarContaClienteNaoCadastrado() throws RepositorioException {
        when(repositorioClientesMock.procurar("12345678900")).thenReturn(null);

        assertThrows(ClienteNaoCadastradoException.class, () -> {
            banco.associarConta("12345678900", "5");
        });

        System.out.println("Exceção ClienteNaoCadastradoException lançada corretamente.");
    }

    @Test
    public void testAssociarContaJaAssociada() throws RepositorioException {
        when(repositorioClientesMock.procurar("12345678900")).thenReturn(clienteMock);
        when(repositorioContasMock.procurar("5")).thenReturn(contaMock);

        assertThrows(ContaJaAssociadaException.class, () -> {
            banco.associarConta("12345678900", "5");
        });

        System.out.println("Exceção ContaJaAssociadaException lançada corretamente.");
    }

    @Test
    public void testAssociarContaSucesso() throws RepositorioException, ClienteNaoCadastradoException, ContaJaAssociadaException, ClienteJaPossuiContaException {
        when(repositorioClientesMock.procurar("12345678900")).thenReturn(clienteMock);
        when(repositorioContasMock.procurar("5")).thenReturn(null);

        banco.associarConta("12345678900", "5");

        verify(clienteMock).adicionarConta("5");
        verify(repositorioClientesMock).atualizar(clienteMock);

        System.out.println("Conta associada com sucesso.");
    }
}
