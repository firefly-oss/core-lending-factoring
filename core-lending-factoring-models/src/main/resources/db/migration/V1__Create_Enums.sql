-- V1 - CREATE ENUMS FOR FACTORING

-- factoring_agreement -> agreement_status
CREATE TYPE agreement_status AS ENUM (
    'ACTIVE',
    'CLOSED',
    'SUSPENDED',
    'TERMINATED'
);

-- factoring_invoice -> currency_code
CREATE TYPE currency_code AS ENUM (
    'EUR',
    'USD',
    'GBP',
    'CHF'
);

-- factoring_invoice -> invoice_status
CREATE TYPE invoice_status AS ENUM (
    'ASSIGNED',
    'PAID',
    'CLOSED',
    'DEFAULT',
    'DISPUTED',
    'CANCELLED'
);

-- factoring_fee -> fee_type
CREATE TYPE fee_type AS ENUM (
    'DISCOUNT_FEE',
    'ADMIN_FEE',
    'COLLECTION_FEE',
    'SERVICE_FEE',
    'LATE_FEE'
);