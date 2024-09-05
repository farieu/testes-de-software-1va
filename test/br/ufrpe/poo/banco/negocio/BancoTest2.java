package br.ufrpe.poo.banco.negocio;

import br.ufrpe.poo.banco.dados.IRepositorioContas;
import br.ufrpe.poo.banco.exceptions.ContaNaoEncontradaException;
import br.ufrpe.poo.banco.exceptions.RenderBonusContaEspecialException;
import br.ufrpe.poo.banco.exceptions.RenderJurosPoupancaException;
import br.ufrpe.poo.banco.exceptions.RepositorioException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;

public class BancoTest2 {

    private Banco banco;
    private IRepositorioContas repositorioContasMock;
    private ContaEspecial contaEspecial;
    private Poupanca poupanca;

    @Before
    public void setUp() throws RepositorioException {
        repositorioContasMock = Mockito.mock(IRepositorioContas.class);
        banco = new Banco(null, repositorioContasMock);
        contaEspecial = new ContaEspecial("123", 1000.0);
        poupanca = new Poupanca("456", 1000.0);
    }

    @Test
    public void testRenderBonusContaEspecial() throws RepositorioException, ContaNaoEncontradaException, RenderBonusContaEspecialException {
        when(repositorioContasMock.existe(contaEspecial.getNumero())).thenReturn(true);

        banco.renderBonus(contaEspecial);

        verify(repositorioContasMock).atualizar(contaEspecial);
        System.out.println("Renderização de bônus aplicada com sucesso.");
    }

    @Test
    public void testRenderBonusContaEspecialException() {
        assertThrows(RenderBonusContaEspecialException.class, () -> {
            banco.renderBonus(poupanca);
        });

        System.out.println("Exceção RenderBonusContaEspecialException lançada corretamente.");
    }

    @Test
    public void testRenderJurosPoupanca() throws RepositorioException, ContaNaoEncontradaException, RenderJurosPoupancaException {
        when(repositorioContasMock.existe(poupanca.getNumero())).thenReturn(true);

        banco.renderJuros(poupanca);

        verify(repositorioContasMock).atualizar(poupanca);
        System.out.println("Renderização de juros aplicada com sucesso.");
    }

    @Test
    public void testRenderJurosPoupancaException() {
        assertThrows(RenderJurosPoupancaException.class, () -> {
            banco.renderJuros(contaEspecial);
        });

        System.out.println("Exceção RenderJurosPoupancaException lançada corretamente.");
    }

    @Test
    public void testRenderBonusContaNaoEncontradaException() {
        when(repositorioContasMock.existe(contaEspecial.getNumero())).thenReturn(false);

        assertThrows(ContaNaoEncontradaException.class, () -> {
            banco.renderBonus(contaEspecial);
        });

        System.out.println("Exceção ContaNaoEncontradaException lançada corretamente no método renderBonus.");
    }

    @Test
    public void testRenderJurosContaNaoEncontradaException() {
        when(repositorioContasMock.existe(poupanca.getNumero())).thenReturn(false);

        assertThrows(ContaNaoEncontradaException.class, () -> {
            banco.renderJuros(poupanca);
        });

        System.out.println("Exceção ContaNaoEncontradaException lançada corretamente no método renderJuros.");
    }
    
}
