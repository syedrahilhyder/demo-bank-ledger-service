package com.demo.bank.ledger.application;

import com.demo.bank.ledger.domain.LedgerEntry;

import java.util.List;

public interface LedgerPosting {
  String post(String paymentId, String debitAccount, String creditAccount, long amountMinor, String currency);
  List<LedgerEntry> entries(String paymentId);
}
