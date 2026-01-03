package com.demo.bank.ledger.api;

import com.demo.bank.ledger.application.LedgerPostingService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ledger")
public class LedgerController {

  private final LedgerPostingService service;

  public LedgerController(LedgerPostingService service) {
    this.service = service;
  }

  @PostMapping("/post")
  public void post(@RequestParam String paymentId,
                   @RequestParam String debitAccount,
                   @RequestParam String creditAccount,
                   @RequestParam long amountMinor,
                   @RequestParam String currency) {
    service.post(paymentId, debitAccount, creditAccount, amountMinor, currency);
  }
}
