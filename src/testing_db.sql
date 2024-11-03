DROP TABLE `accounts`;
CREATE TABLE `accounts` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name`varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

-- Insert sample data into Users table
INSERT INTO `accounts` (`name`, `email`)
VALUES 
    ('John Doe', 'john.doe@example.com'),
    ('Jane Smith', 'jane.smith@example.com'),
    ('Robert Brown', 'robert.brown@example.com');

DROP TABLE `petty_cash`;
CREATE TABLE `petty_cash` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `date` DATE NOT NULL,
    `activity_description` VARCHAR(255) NOT NULL,
    `activity_category` VARCHAR(100) NOT NULL,
    `soa_category` VARCHAR(100) NOT NULL,
    #`account` VARCHAR(100) NOT NULL,
    `amount` DECIMAL(10, 2) NOT NULL
);
-- Insert sample data into PettyCash table
INSERT INTO `petty_cash` (`date`, `activity_description`, `activity_category`, `soa_category`, `amount`)
VALUES 
    ('2024-10-01', 'Office Supplies', 'Operations', 'Miscellaneous', 150.00),
    ('2024-10-03', 'Travel Reimbursement', 'Travel', 'Transportation', 250.00),
    ('2024-10-05', 'Team Lunch', 'Employee Engagement', 'Food', 120.00),
    ('2024-10-07', 'Client Meeting Expenses', 'Client Relations', 'Entertainment', 200.00),
    ('2024-10-09', 'Stationery Purchase', 'Operations', 'Supplies', 50.00);

# Junction Table
DROP TABLE `petty_cash_accounts`;
CREATE TABLE `petty_cash_accounts` (
    `petty_cash_id` INT,
    `account_id` INT,
    PRIMARY KEY (`petty_cash_id`,`account_id`),
    FOREIGN KEY (`petty_cash_id`) REFERENCES `petty_cash`(`id`) ON DELETE CASCADE,
    FOREIGN KEY (`account_id`) REFERENCES `accounts`(`id`) ON DELETE CASCADE
);

-- Insert sample data into User_Petty_Cash (junction table)
-- Linking users to petty cash records
INSERT INTO `petty_cash_accounts` (`petty_cash_id`, `account_id`)
VALUES 
    (1, 1), -- Office Supplies & John Doe
    (2, 1), -- Travel Reimbursement & John Doe
    (3, 2), -- Team Lunch & Jane Smith
    (4, 3), -- Client Meeting Expenses & Robert Brown
    (5, 3); -- Stationery Purchase & Robert Brown


# SINGLE ENTRY PETTY CASH W/MULITPLE USERS

INSERT INTO `petty_cash` (`date`, `activity_description`, `activity_category`, `soa_category`, `amount`)
VALUES ('2024-11-01', 'Office Supplies Purchase', 'Office Expenses', 'Supplies', 150.00);

INSERT INTO `petty_cash_accounts` (`petty_cash_id`,`account_id`) 
VALUES 
    (6, 1),
    (6, 2),
    (6, 3);

SELECT 
    a.id AS account_id,
    a.name AS account_name,
    a.email AS account_email,
    pc.id AS petty_cash_id,
    pc.date AS transaction_date,
    pc.activity_description,
    pc.activity_category,
    pc.soa_category,
    pc.amount
FROM 
    petty_cash_accounts pca
JOIN 
    accounts a ON pca.account_id = a.id
JOIN 
    petty_cash pc ON pca.petty_cash_id = pc.id
ORDER BY 
    a.id, pc.date;
    
