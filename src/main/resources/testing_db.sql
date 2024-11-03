CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name`varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

INSERT INTO `users` (`name`, `email`)
VALUES ('John Doe', 'john@mail.com'),
('Jane Smith', 'jane@mai.com'), ('Robert Brown', 'robert@mail.com'); 

CREATE TABLE `petty_cash` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `date` DATE NOT NULL,
    `activity_description` VARCHAR(255) NOT NULL,
    `activity_category` VARCHAR(100) NOT NULL,
    `soa_category` VARCHAR(100) NOT NULL,
    #`account` VARCHAR(100) NOT NULL,
    `amount` DECIMAL(10, 2) NOT NULL
);

# Junction Table
CREATE TABLE `user_petty_cash` (
    `user_id` INT,
    `petty_cash_id` INT,
    PRIMARY KEY (user_id, petty_cash_id),
    FOREIGN KEY (user_id) REFERENCES Users(id) ON DELETE CASCADE,
    FOREIGN KEY (petty_cash_id) REFERENCES PettyCash(id) ON DELETE CASCADE
);

