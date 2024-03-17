package com.gihub.dalwid.banco.app;

import com.gihub.dalwid.banco.modelo.Conta;
import com.gihub.dalwid.banco.modelo.pagamento.Banco;

import java.util.Comparator;
import java.util.function.ToIntFunction;

public class Principal3 {

    public static void main(String[] args) {
        Banco banco = new Banco();

//        ToIntFunction<Conta> function = (Conta conta) -> {
//            return conta.getNumero();
//        };

//        banco.getContas().sort(Comparator.comparingInt(new NumeroContaFunction()));
//        banco.getContas().sort(Comparator.comparingInt( conta ->  conta.getNumero() ));
        banco.getContas().sort(Comparator.comparingInt(Conta::getNumero));

        for(Conta conta: banco.getContas()){
            System.out.println(conta.getAgencia() + "/" + conta.getNumero());
        }
    }

}
