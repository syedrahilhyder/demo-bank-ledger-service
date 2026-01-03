package com.demo.bank.ledger.application;

import com.demo.bank.events.*;
import com.demo.bank.ledger.domain.*;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class LedgerPostingService {

  private final LedgerRepository repo;
  private final KafkaTemplate<String, Object> kafka;

  public LedgerPostingService(LedgerRepository repo, KafkaTemplate<String, Object> kafka) {
    this.repo = repo;
    this.kafka = kafka;
  }

  public void post(String paymentId, String debit, String credit, long amount, String currency) {
    LedgerEntry entry = new LedgerEntry(
        UUID.randomUUID(), paymentId, debit, credit, amount, currency
    );
    repo.save(entry);

    LedgerPostedEvent event = new LedgerPostedEvent(
        entry.getId().toString(), paymentId, debit, credit, amount, currency
    );

    kafka.send(EventTopics.LEDGER_EVENTS, paymentId, event);
  }
}
