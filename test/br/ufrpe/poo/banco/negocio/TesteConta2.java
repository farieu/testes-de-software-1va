package br.ufrpe.poo.banco.negocio;

import static org.junit.Assert.*;

import org.junit.Test;

public class TesteConta2 {

	@Test
	public final void testDebitar() {
		Conta c = new Conta("1", 100);
		assertEquals("Numero incorreto", "1", c.getNumero());
	}

	@Test
	public final void testCreditar() {
        Conta c = new Conta("1", 100);
        c.creditar(50);
        assertEquals("Saldo incorreto após creditar", 150, c.getSaldo(), 0.01);
    }

    @Test
    public void testSetNumero() {
        Conta c = new Conta("1", 100);
        c.setNumero("2");
        assertEquals("Número da conta não foi atualizado corretamente", "2", c.getNumero());
    }
}