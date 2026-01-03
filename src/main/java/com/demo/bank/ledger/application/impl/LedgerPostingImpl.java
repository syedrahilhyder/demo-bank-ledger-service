package com.demo.bank.ledger.application.impl;

import com.demo.bank.events.EventTopics;
import com.demo.bank.events.LedgerPostedEvent;
import com.demo.bank.ledger.application.LedgerPosting;
import com.demo.bank.ledger.domain.LedgerEntry;
import com.demo.bank.ledger.domain.LedgerEntryRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LedgerPostingImpl implements LedgerPosting {

  private final LedgerEntryRepository repo;
  private final LedgerRules rules;
  private final KafkaTemplate<String, Object> kafka;

  public LedgerPostingImpl(LedgerEntryRepository repo, LedgerRules rules, KafkaTemplate<String, Object> kafka) {
    this.repo = repo;
    this.rules = rules;
    this.kafka = kafka;
  }

  @Override
  public String post(String paymentId, String debitAccount, String creditAccount, long amountMinor, String currency) {
    rules.validateAccounts(debitAccount, creditAccount);
    rules.validateAmount(amountMinor);
    rules.validateCurrency(currency);

    UUID id = UUID.randomUUID();
    LedgerEntry entry = new LedgerEntry(id, paymentId, debitAccount, creditAccount, amountMinor, currency);
    repo.save(entry);

    kafka.send(EventTopics.LEDGER_EVENTS, paymentId, new LedgerPostedEvent(
        id.toString(), paymentId, debitAccount, creditAccount, amountMinor, currency
    ));

    return id.toString();
  }

  @Override
  public List<LedgerEntry> entries(String paymentId) {
    return repo.findByPaymentId(paymentId);
  }
}
