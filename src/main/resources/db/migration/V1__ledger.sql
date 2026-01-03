create table ledger_entries (
  id uuid primary key,
  payment_id varchar(64) not null,
  debit_account varchar(64) not null,
  credit_account varchar(64) not null,
  amount_minor bigint not null,
  currency varchar(3) not null,
  created_at timestamptz
);

create index idx_ledger_payment on ledger_entries(payment_id);
