package com.demo.bank.ledger.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface LedgerRepository extends JpaRepository<LedgerEntry, UUID> {}
