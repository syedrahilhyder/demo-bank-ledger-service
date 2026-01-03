package com.demo.bank.ledger.application.impl;

import org.springframework.stereotype.Component;

@Component
public class LedgerRules {

  public void validateAccounts(String debit, String credit) {
    if (debit == null || debit.isBlank()) throw new IllegalArgumentException("debitAccount required");
    if (credit == null || credit.isBlank()) throw new IllegalArgumentException("creditAccount required");
    if (debit.equals(credit)) throw new IllegalArgumentException("debit/credit must differ");
  }

  public void validateAmount(long amountMinor) {
    if (amountMinor <= 0) throw new IllegalArgumentException("amountMinor must be > 0");
  }

  public void validateCurrency(String currency) {
    if (currency == null || currency.length() != 3) throw new IllegalArgumentException("currency must be ISO3");
  }
}
