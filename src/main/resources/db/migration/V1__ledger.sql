create table ledger_entries (
  id uuid primary key,
  payment_id varchar(64),
  debit_account varchar(64),
  credit_account varchar(64),
  amount_minor bigint,
  currency varchar(3),
  created_at timestamptz
);
