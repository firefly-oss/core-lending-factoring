-- V4 - CONVERT LONG ID FIELDS TO UUID
-- This migration converts all BIGSERIAL primary keys and BIGINT foreign keys to UUID

-- Enable uuid extension if not already enabled
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- ========================================================================
-- STEP 1: Add temporary UUID columns to all tables
-- ========================================================================

-- factoring_agreement
ALTER TABLE factoring_agreement 
ADD COLUMN factoring_agreement_uuid UUID DEFAULT gen_random_uuid(),
ADD COLUMN contract_uuid UUID,
ADD COLUMN customer_uuid UUID;

-- factoring_debtor
ALTER TABLE factoring_debtor 
ADD COLUMN factoring_debtor_uuid UUID DEFAULT gen_random_uuid(),
ADD COLUMN factoring_agreement_uuid UUID,
ADD COLUMN debtor_customer_uuid UUID;

-- factoring_invoice
ALTER TABLE factoring_invoice 
ADD COLUMN factoring_invoice_uuid UUID DEFAULT gen_random_uuid(),
ADD COLUMN factoring_agreement_uuid UUID,
ADD COLUMN factoring_debtor_uuid UUID,
ADD COLUMN document_reference_uuid UUID;

-- factoring_advance
ALTER TABLE factoring_advance 
ADD COLUMN factoring_advance_uuid UUID DEFAULT gen_random_uuid(),
ADD COLUMN factoring_invoice_uuid UUID,
ADD COLUMN transaction_uuid UUID;

-- factoring_fee
ALTER TABLE factoring_fee 
ADD COLUMN factoring_fee_uuid UUID DEFAULT gen_random_uuid(),
ADD COLUMN factoring_agreement_uuid UUID;

-- factoring_invoice_settlement
ALTER TABLE factoring_invoice_settlement 
ADD COLUMN factoring_invoice_settlement_uuid UUID DEFAULT gen_random_uuid(),
ADD COLUMN factoring_invoice_uuid UUID,
ADD COLUMN transaction_uuid UUID;

-- factoring_invoice_status_history
ALTER TABLE factoring_invoice_status_history 
ADD COLUMN factoring_invoice_status_history_uuid UUID DEFAULT gen_random_uuid(),
ADD COLUMN factoring_invoice_uuid UUID;

-- ========================================================================
-- STEP 2: Populate UUID foreign keys with corresponding primary key UUIDs
-- ========================================================================

-- Update factoring_debtor foreign key references
UPDATE factoring_debtor 
SET factoring_agreement_uuid = fa.factoring_agreement_uuid
FROM factoring_agreement fa
WHERE factoring_debtor.factoring_agreement_id = fa.factoring_agreement_id;

-- Update factoring_invoice foreign key references
UPDATE factoring_invoice 
SET factoring_agreement_uuid = fa.factoring_agreement_uuid
FROM factoring_agreement fa
WHERE factoring_invoice.factoring_agreement_id = fa.factoring_agreement_id;

UPDATE factoring_invoice 
SET factoring_debtor_uuid = fd.factoring_debtor_uuid
FROM factoring_debtor fd
WHERE factoring_invoice.factoring_debtor_id = fd.factoring_debtor_id;

-- Update factoring_advance foreign key references
UPDATE factoring_advance 
SET factoring_invoice_uuid = fi.factoring_invoice_uuid
FROM factoring_invoice fi
WHERE factoring_advance.factoring_invoice_id = fi.factoring_invoice_id;

-- Update factoring_fee foreign key references
UPDATE factoring_fee 
SET factoring_agreement_uuid = fa.factoring_agreement_uuid
FROM factoring_agreement fa
WHERE factoring_fee.factoring_agreement_id = fa.factoring_agreement_id;

-- Update factoring_invoice_settlement foreign key references
UPDATE factoring_invoice_settlement 
SET factoring_invoice_uuid = fi.factoring_invoice_uuid
FROM factoring_invoice fi
WHERE factoring_invoice_settlement.factoring_invoice_id = fi.factoring_invoice_id;

-- Update factoring_invoice_status_history foreign key references
UPDATE factoring_invoice_status_history 
SET factoring_invoice_uuid = fi.factoring_invoice_uuid
FROM factoring_invoice fi
WHERE factoring_invoice_status_history.factoring_invoice_id = fi.factoring_invoice_id;

-- ========================================================================
-- STEP 3: Drop existing foreign key constraints
-- ========================================================================

ALTER TABLE factoring_debtor DROP CONSTRAINT IF EXISTS fk_debtor_agreement;
ALTER TABLE factoring_invoice DROP CONSTRAINT IF EXISTS fk_invoice_agreement;
ALTER TABLE factoring_invoice DROP CONSTRAINT IF EXISTS fk_invoice_debtor;
ALTER TABLE factoring_advance DROP CONSTRAINT IF EXISTS fk_advance_invoice;
ALTER TABLE factoring_fee DROP CONSTRAINT IF EXISTS fk_fee_agreement;
ALTER TABLE factoring_invoice_settlement DROP CONSTRAINT IF EXISTS fk_settlement_invoice;
ALTER TABLE factoring_invoice_status_history DROP CONSTRAINT IF EXISTS fk_inv_status_hist;

-- ========================================================================
-- STEP 4: Drop old Long columns and rename UUID columns
-- ========================================================================

-- factoring_agreement
ALTER TABLE factoring_agreement 
DROP COLUMN factoring_agreement_id,
DROP COLUMN contract_id,
DROP COLUMN customer_id;

ALTER TABLE factoring_agreement 
RENAME COLUMN factoring_agreement_uuid TO factoring_agreement_id;
ALTER TABLE factoring_agreement 
RENAME COLUMN contract_uuid TO contract_id;
ALTER TABLE factoring_agreement 
RENAME COLUMN customer_uuid TO customer_id;

-- factoring_debtor
ALTER TABLE factoring_debtor 
DROP COLUMN factoring_debtor_id,
DROP COLUMN factoring_agreement_id,
DROP COLUMN debtor_customer_id;

ALTER TABLE factoring_debtor 
RENAME COLUMN factoring_debtor_uuid TO factoring_debtor_id;
ALTER TABLE factoring_debtor 
RENAME COLUMN factoring_agreement_uuid TO factoring_agreement_id;
ALTER TABLE factoring_debtor 
RENAME COLUMN debtor_customer_uuid TO debtor_customer_id;

-- factoring_invoice
ALTER TABLE factoring_invoice 
DROP COLUMN factoring_invoice_id,
DROP COLUMN factoring_agreement_id,
DROP COLUMN factoring_debtor_id,
DROP COLUMN document_reference;

ALTER TABLE factoring_invoice 
RENAME COLUMN factoring_invoice_uuid TO factoring_invoice_id;
ALTER TABLE factoring_invoice 
RENAME COLUMN factoring_agreement_uuid TO factoring_agreement_id;
ALTER TABLE factoring_invoice 
RENAME COLUMN factoring_debtor_uuid TO factoring_debtor_id;
ALTER TABLE factoring_invoice 
RENAME COLUMN document_reference_uuid TO document_reference;

-- factoring_advance
ALTER TABLE factoring_advance 
DROP COLUMN factoring_advance_id,
DROP COLUMN factoring_invoice_id,
DROP COLUMN transaction_id;

ALTER TABLE factoring_advance 
RENAME COLUMN factoring_advance_uuid TO factoring_advance_id;
ALTER TABLE factoring_advance 
RENAME COLUMN factoring_invoice_uuid TO factoring_invoice_id;
ALTER TABLE factoring_advance 
RENAME COLUMN transaction_uuid TO transaction_id;

-- factoring_fee
ALTER TABLE factoring_fee 
DROP COLUMN factoring_fee_id,
DROP COLUMN factoring_agreement_id;

ALTER TABLE factoring_fee 
RENAME COLUMN factoring_fee_uuid TO factoring_fee_id;
ALTER TABLE factoring_fee 
RENAME COLUMN factoring_agreement_uuid TO factoring_agreement_id;

-- factoring_invoice_settlement
ALTER TABLE factoring_invoice_settlement 
DROP COLUMN factoring_invoice_settlement_id,
DROP COLUMN factoring_invoice_id,
DROP COLUMN transaction_id;

ALTER TABLE factoring_invoice_settlement 
RENAME COLUMN factoring_invoice_settlement_uuid TO factoring_invoice_settlement_id;
ALTER TABLE factoring_invoice_settlement 
RENAME COLUMN factoring_invoice_uuid TO factoring_invoice_id;
ALTER TABLE factoring_invoice_settlement 
RENAME COLUMN transaction_uuid TO transaction_id;

-- factoring_invoice_status_history
ALTER TABLE factoring_invoice_status_history 
DROP COLUMN factoring_invoice_status_history_id,
DROP COLUMN factoring_invoice_id;

ALTER TABLE factoring_invoice_status_history 
RENAME COLUMN factoring_invoice_status_history_uuid TO factoring_invoice_status_history_id;
ALTER TABLE factoring_invoice_status_history 
RENAME COLUMN factoring_invoice_uuid TO factoring_invoice_id;

-- ========================================================================
-- STEP 5: Add primary key constraints
-- ========================================================================

ALTER TABLE factoring_agreement ADD PRIMARY KEY (factoring_agreement_id);
ALTER TABLE factoring_debtor ADD PRIMARY KEY (factoring_debtor_id);
ALTER TABLE factoring_invoice ADD PRIMARY KEY (factoring_invoice_id);
ALTER TABLE factoring_advance ADD PRIMARY KEY (factoring_advance_id);
ALTER TABLE factoring_fee ADD PRIMARY KEY (factoring_fee_id);
ALTER TABLE factoring_invoice_settlement ADD PRIMARY KEY (factoring_invoice_settlement_id);
ALTER TABLE factoring_invoice_status_history ADD PRIMARY KEY (factoring_invoice_status_history_id);

-- ========================================================================
-- STEP 6: Add foreign key constraints
-- ========================================================================

ALTER TABLE factoring_debtor 
ADD CONSTRAINT fk_debtor_agreement 
FOREIGN KEY (factoring_agreement_id) 
REFERENCES factoring_agreement (factoring_agreement_id);

ALTER TABLE factoring_invoice 
ADD CONSTRAINT fk_invoice_agreement 
FOREIGN KEY (factoring_agreement_id) 
REFERENCES factoring_agreement (factoring_agreement_id);

ALTER TABLE factoring_invoice 
ADD CONSTRAINT fk_invoice_debtor 
FOREIGN KEY (factoring_debtor_id) 
REFERENCES factoring_debtor (factoring_debtor_id);

ALTER TABLE factoring_advance 
ADD CONSTRAINT fk_advance_invoice 
FOREIGN KEY (factoring_invoice_id) 
REFERENCES factoring_invoice (factoring_invoice_id);

ALTER TABLE factoring_fee 
ADD CONSTRAINT fk_fee_agreement 
FOREIGN KEY (factoring_agreement_id) 
REFERENCES factoring_agreement (factoring_agreement_id);

ALTER TABLE factoring_invoice_settlement 
ADD CONSTRAINT fk_settlement_invoice 
FOREIGN KEY (factoring_invoice_id) 
REFERENCES factoring_invoice (factoring_invoice_id);

ALTER TABLE factoring_invoice_status_history 
ADD CONSTRAINT fk_inv_status_hist 
FOREIGN KEY (factoring_invoice_id) 
REFERENCES factoring_invoice (factoring_invoice_id);

-- ========================================================================
-- STEP 7: Add default UUID generation for new records
-- ========================================================================

ALTER TABLE factoring_agreement ALTER COLUMN factoring_agreement_id SET DEFAULT gen_random_uuid();
ALTER TABLE factoring_debtor ALTER COLUMN factoring_debtor_id SET DEFAULT gen_random_uuid();
ALTER TABLE factoring_invoice ALTER COLUMN factoring_invoice_id SET DEFAULT gen_random_uuid();
ALTER TABLE factoring_advance ALTER COLUMN factoring_advance_id SET DEFAULT gen_random_uuid();
ALTER TABLE factoring_fee ALTER COLUMN factoring_fee_id SET DEFAULT gen_random_uuid();
ALTER TABLE factoring_invoice_settlement ALTER COLUMN factoring_invoice_settlement_id SET DEFAULT gen_random_uuid();
ALTER TABLE factoring_invoice_status_history ALTER COLUMN factoring_invoice_status_history_id SET DEFAULT gen_random_uuid();