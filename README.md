# demo-bank-ledger-service (v2)

Ledger service (double-entry posting) with:
- POST /ledger/post
- GET /ledger/entries/{paymentId}
- Postgres + Flyway
- Kafka producer: LedgerPostedEvent
