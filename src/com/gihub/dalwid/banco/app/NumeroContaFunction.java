package com.gihub.dalwid.banco.app;

import com.gihub.dalwid.banco.modelo.Conta;

import java.util.function.ToIntFunction;

public class NumeroContaFunction implements ToIntFunction<Conta> {
    @Override
    public int applyAsInt(Conta conta) {
        return conta.getNumero();
    }
}
