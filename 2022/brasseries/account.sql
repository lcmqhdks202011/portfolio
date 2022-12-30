CREATE DATABASE kwokhiun_accounts;
use kwokhiun_accounts;


CREATE TABLE `accounts` (
	`user` varchar(7) DEFAULT NULL,
	`password` varchar(9) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO `accounts` (`user`, `password`) VALUES
('ift3225', 'sésame'),
('admin', 'ouvre-toi');
COMMIT;