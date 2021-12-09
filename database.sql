CREATE TABLE currency (
	name	 varchar(512),
	to_euro float(8) NOT NULL,
	PRIMARY KEY(name)
);

CREATE TABLE client (
	id	 bigint AUTO_INCREMENT,
	name	 varchar(512) NOT NULL,
	balance	 float(8) NOT NULL,
	manager_id bigint NOT NULL,
	PRIMARY KEY(id)
);

CREATE TABLE manager (
	id	 bigint AUTO_INCREMENT,
	name	 varchar(512) NOT NULL,
	revenue float(8) NOT NULL,
	PRIMARY KEY(id)
);

CREATE TABLE payment (
	id		 bigint AUTO_INCREMENT,
	amount	 float(8) NOT NULL,
	pay_date	 date NOT NULL,
	currency_name varchar(512) NOT NULL,
	client_id	 bigint NOT NULL,
	PRIMARY KEY(id)
);

CREATE TABLE credit (
	id		 bigint AUTO_INCREMENT,
	amount	 float(8) NOT NULL,
	dead_line	 date NOT NULL,
	currency_name varchar(512) NOT NULL,
	client_id	 bigint NOT NULL,
	PRIMARY KEY(id)
);

ALTER TABLE client ADD CONSTRAINT client_fk1 FOREIGN KEY (manager_id) REFERENCES manager(id);
ALTER TABLE payment ADD CONSTRAINT payment_fk1 FOREIGN KEY (currency_name) REFERENCES currency(name);
ALTER TABLE payment ADD CONSTRAINT payment_fk2 FOREIGN KEY (client_id) REFERENCES client(id);
ALTER TABLE credit ADD CONSTRAINT credit_fk1 FOREIGN KEY (currency_name) REFERENCES currency(name);
ALTER TABLE credit ADD CONSTRAINT credit_fk2 FOREIGN KEY (client_id) REFERENCES client(id);
