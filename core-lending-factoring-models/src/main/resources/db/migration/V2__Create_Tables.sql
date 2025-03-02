-- V2 - CREATE TABLES FOR THE FACTORING (RECEIVABLES FINANCING) MODULE

-- ========================================================================
-- TABLE: factoring_agreement
-- ========================================================================
CREATE TABLE IF NOT EXISTS factoring_agreement (
                                                   factoring_agreement_id  BIGSERIAL PRIMARY KEY,
                                                   contract_id             BIGINT,  -- external reference
                                                   customer_id             BIGINT,  -- factoring client's ID
                                                   agreement_status        agreement_status NOT NULL,
                                                   start_date              DATE,
                                                   end_date                DATE,
                                                   credit_limit            DECIMAL(18,2),
    recourse                BOOLEAN NOT NULL DEFAULT TRUE,
    advance_rate            DECIMAL(5,2) DEFAULT 80.0,
    remarks                 TEXT,
    created_at              TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at              TIMESTAMP NOT NULL DEFAULT NOW()
    );

-- ========================================================================
-- TABLE: factoring_debtor
-- ========================================================================
CREATE TABLE IF NOT EXISTS factoring_debtor (
                                                factoring_debtor_id     BIGSERIAL PRIMARY KEY,
                                                factoring_agreement_id  BIGINT NOT NULL,
                                                debtor_customer_id      BIGINT,       -- external reference for the debtor
                                                debtor_name             VARCHAR(255),
    debtor_credit_limit     DECIMAL(18,2),
    remarks                 TEXT,
    created_at              TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at              TIMESTAMP NOT NULL DEFAULT NOW(),
    CONSTRAINT fk_debtor_agreement
    FOREIGN KEY (factoring_agreement_id)
    REFERENCES factoring_agreement (factoring_agreement_id)
    );

-- ========================================================================
-- TABLE: factoring_invoice
-- ========================================================================
CREATE TABLE IF NOT EXISTS factoring_invoice (
                                                 factoring_invoice_id    BIGSERIAL PRIMARY KEY,
                                                 factoring_agreement_id  BIGINT NOT NULL,
                                                 factoring_debtor_id     BIGINT,
                                                 invoice_number          VARCHAR(100) NOT NULL,
    invoice_date            DATE NOT NULL,
    due_date                DATE,
    invoice_amount          DECIMAL(18,2) NOT NULL,
    assigned_amount         DECIMAL(18,2) NOT NULL,
    currency_code           currency_code NOT NULL,
    invoice_status          invoice_status NOT NULL,
    is_accepted_by_debtor   BOOLEAN DEFAULT FALSE,
    acceptance_date         DATE,
    document_reference      BIGINT,  -- NEW FIELD for referencing Document Manager
    created_at              TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at              TIMESTAMP NOT NULL DEFAULT NOW(),
    CONSTRAINT fk_invoice_agreement
    FOREIGN KEY (factoring_agreement_id)
    REFERENCES factoring_agreement (factoring_agreement_id),
    CONSTRAINT fk_invoice_debtor
    FOREIGN KEY (factoring_debtor_id)
    REFERENCES factoring_debtor (factoring_debtor_id)
    );

-- ========================================================================
-- TABLE: factoring_advance
-- ========================================================================
CREATE TABLE IF NOT EXISTS factoring_advance (
                                                 factoring_advance_id    BIGSERIAL PRIMARY KEY,
                                                 factoring_invoice_id    BIGINT NOT NULL,
                                                 transaction_id          BIGINT,
                                                 advance_amount          DECIMAL(18,2) NOT NULL,
    advance_date            DATE NOT NULL,
    is_final_advance        BOOLEAN DEFAULT FALSE,
    note                    TEXT,
    created_at              TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at              TIMESTAMP NOT NULL DEFAULT NOW(),
    CONSTRAINT fk_advance_invoice
    FOREIGN KEY (factoring_invoice_id)
    REFERENCES factoring_invoice (factoring_invoice_id)
    );

-- ========================================================================
-- TABLE: factoring_fee
-- ========================================================================
CREATE TABLE IF NOT EXISTS factoring_fee (
                                             factoring_fee_id        BIGSERIAL PRIMARY KEY,
                                             factoring_agreement_id  BIGINT NOT NULL,
                                             fee_type                fee_type NOT NULL,
                                             fee_rate                DECIMAL(9,4) DEFAULT 0,
    min_fee                 DECIMAL(18,2),
    max_fee                 DECIMAL(18,2),
    is_active               BOOLEAN DEFAULT TRUE,
    note                    TEXT,
    created_at              TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at              TIMESTAMP NOT NULL DEFAULT NOW(),
    CONSTRAINT fk_fee_agreement
    FOREIGN KEY (factoring_agreement_id)
    REFERENCES factoring_agreement (factoring_agreement_id)
    );

-- ========================================================================
-- TABLE: factoring_invoice_settlement
-- ========================================================================
CREATE TABLE IF NOT EXISTS factoring_invoice_settlement (
                                                            factoring_invoice_settlement_id BIGSERIAL PRIMARY KEY,
                                                            factoring_invoice_id      BIGINT NOT NULL,
                                                            transaction_id            BIGINT,
                                                            settlement_date           DATE NOT NULL,
                                                            settlement_amount         DECIMAL(18,2) NOT NULL,
    fees_deducted             DECIMAL(18,2) DEFAULT 0,
    net_to_client             DECIMAL(18,2) DEFAULT 0,
    is_closed                 BOOLEAN DEFAULT FALSE,
    note                      TEXT,
    created_at                TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at                TIMESTAMP NOT NULL DEFAULT NOW(),
    CONSTRAINT fk_settlement_invoice
    FOREIGN KEY (factoring_invoice_id)
    REFERENCES factoring_invoice (factoring_invoice_id)
    );

-- ========================================================================
-- TABLE: factoring_invoice_status_history
-- ========================================================================
CREATE TABLE IF NOT EXISTS factoring_invoice_status_history (
                                                                factoring_invoice_status_history_id BIGSERIAL PRIMARY KEY,
                                                                factoring_invoice_id                BIGINT NOT NULL,
                                                                old_status                          invoice_status,
                                                                new_status                          invoice_status NOT NULL,
                                                                changed_at                          TIMESTAMP NOT NULL DEFAULT NOW(),
    changed_by                          VARCHAR(100),
    reason                              TEXT,
    created_at                          TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at                          TIMESTAMP NOT NULL DEFAULT NOW(),
    CONSTRAINT fk_inv_status_hist
    FOREIGN KEY (factoring_invoice_id)
    REFERENCES factoring_invoice (factoring_invoice_id)
    );