-- Create account table if not exists
CREATE TABLE IF NOT EXISTS account (
    account_no VARCHAR(20) PRIMARY KEY,
    name VARCHAR(100),
    policy_type VARCHAR(50),
    sum_insured DECIMAL(15,2),
    start_date DATE
);

-- Insert sample data only if not already present
INSERT IGNORE INTO account (account_no, name, policy_type, sum_insured, start_date)
VALUES ('ACC1001', 'John Doe', 'TERM', 500000, '2025-09-01');

INSERT IGNORE INTO account (account_no, name, policy_type, sum_insured, start_date)
VALUES ('ACC1002', 'Alice Smith', 'LIFE', 300000, '2025-08-15');

INSERT IGNORE INTO account (account_no, name, policy_type, sum_insured, start_date)
VALUES ('ACC1003', 'Bob Johnson', 'HEALTH', 200000, '2025-07-20');
