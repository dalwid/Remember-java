package com.gihub.dalwid.banco.app;

import com.gihub.dalwid.banco.modelo.*;
import com.gihub.dalwid.banco.modelo.atm.CaixaEletronico;
import com.gihub.dalwid.banco.modelo.excecao.SaldoInsuficienteException;
import com.gihub.dalwid.banco.modelo.pagamento.Boleto;
import com.gihub.dalwid.banco.modelo.pagamento.DocumentoPagavel;
import com.gihub.dalwid.banco.modelo.pagamento.Holerite;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {

        Pessoa titular1 = new Pessoa();
        titular1.setNome("João da Silva");
        titular1.setDocumento("12332112311");
        titular1.setRendimentoAnual(new BigDecimal("15000"));
        titular1.setTipo(TipoPessoa.JURIDICA);

        titular1.setDataUltimaAtualizacao(LocalDateTime.parse("2024-03-17T13:20:00"));
        System.out.println(titular1.getDataUltimaAtualizacao());

        Pessoa titular2 = new Pessoa();
        titular2.setNome("Maria Abadia");
        titular2.setDocumento("22233344455");

        CaixaEletronico caixaEletronico = new CaixaEletronico();

        ContaIvestimento minhaConta = new ContaIvestimento(titular1, 123, 987);
        ContaEspecial suaConta      = new ContaEspecial(titular2, 222, 333, new BigDecimal("1000"));

        try {
            minhaConta.depositar(new BigDecimal("30000"));
            minhaConta.sacar(new BigDecimal("1000"));
            minhaConta.creditarRendimentos(new BigDecimal("0.8"));
            minhaConta.debitarTarifaMensal();

            suaConta.depositar(new BigDecimal("15000"));
            suaConta.sacar(new BigDecimal("15500"));
            suaConta.debitarTarifaMensal();

            Boleto boletoEscola = new Boleto(titular2, new BigDecimal("35000"));
            Holerite salarioFuncioaraio = new Holerite(titular2, new BigDecimal("100"), 160);

            caixaEletronico.pagar(boletoEscola, minhaConta);
            caixaEletronico.pagar(salarioFuncioaraio, minhaConta);

            caixaEletronico.stornarpagamento(boletoEscola, minhaConta);

            boletoEscola.imprimirRecibo();
            salarioFuncioaraio.imprimirRecibo();
        }
        catch (SaldoInsuficienteException e){
            System.out.println("Error ao executar operação na conta: " + e.getMessage());
        }


//        System.out.println("Boleto pago: " + boletoEscola.estaPago());
//        System.out.println("Salário pago: " + salarioFuncioaraio.estaPago());

        caixaEletronico.imprimirSaldo(minhaConta);
        System.out.println();
        caixaEletronico.imprimirSaldo(suaConta);
    }
}