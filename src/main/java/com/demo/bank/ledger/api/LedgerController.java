package com.demo.bank.ledger.api;

import com.demo.bank.ledger.application.LedgerPosting;
import com.demo.bank.ledger.domain.LedgerEntry;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ledger")
public class LedgerController {

  private final LedgerPosting posting;

  public LedgerController(LedgerPosting posting) {
    this.posting = posting;
  }

  @PostMapping("/post")
  public String post(@RequestParam String paymentId,
                     @RequestParam String debitAccount,
                     @RequestParam String creditAccount,
                     @RequestParam long amountMinor,
                     @RequestParam String currency) {
    return posting.post(paymentId, debitAccount, creditAccount, amountMinor, currency);
  }

  @GetMapping("/entries/{paymentId}")
  public List<LedgerEntry> entries(@PathVariable String paymentId) {
    return posting.entries(paymentId);
  }
}
