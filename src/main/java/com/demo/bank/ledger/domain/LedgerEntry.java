package com.demo.bank.ledger.domain;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "ledger_entries")
public class LedgerEntry {

  @Id
  private UUID id;

  private String paymentId;
  private String debitAccount;
  private String creditAccount;
  private long amountMinor;
  private String currency;
  private Instant createdAt;

  protected LedgerEntry() {}

  public LedgerEntry(UUID id, String paymentId, String debitAccount,
                     String creditAccount, long amountMinor, String currency) {
    this.id = id;
    this.paymentId = paymentId;
    this.debitAccount = debitAccount;
    this.creditAccount = creditAccount;
    this.amountMinor = amountMinor;
    this.currency = currency;
    this.createdAt = Instant.now();
  }

  public UUID getId() { return id; }
}
