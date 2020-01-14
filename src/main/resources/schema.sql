CREATE TABLE IF NOT EXISTS client
(
	id bigint NOT NULL,
	name character varying(50) NOT NULL,
	surname character varying(50) NOT NULL,
	discount double precision NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS employee
(
    id bigint NOT NULL,
    name character varying(50) NOT NULL,
    surname character varying(50) NOT NULL,
	position character varying(50) NOT NULL,
	salary double precision NOT NULL,
	managers_id bigint NOT NULL,
	pesel integer NOT NULL UNIQUE,
	PRIMARY KEY (id),
	FOREIGN KEY (managers_id) REFERENCES employee(id)
);

CREATE TABLE IF NOT EXISTS dish
(
	id bigint NOT NULL UNIQUE,
	name character varying(50) NOT NULL UNIQUE,
	description character varying(255) NOT NULL,
	price double precision NOT NULL,
	is_available boolean NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS drink
(
	id bigint NOT NULL,
	name character varying(50) NOT NULL UNIQUE,
	description character varying(255) NOT NULL,
	price double precision NOT NULL,
	is_available boolean NOT NULL,
	portion_ml integer NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS product
(
	id bigint NOT NULL,
	name character varying(50) NOT NULL UNIQUE,
	unit character varying(50) NOT NULL,
	stored_amount integer NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS bill
(
	id bigint NOT NULL,
	date timestamp NOT NULL,
	price double precision NOT NULL,
	tip double precision NOT NULL,
    client_id bigint NOT NULL,
    employee_id bigint NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (employee_id) REFERENCES employee(id),
	FOREIGN KEY (client_id) REFERENCES client(id)
);

CREATE TABLE IF NOT EXISTS drink_bill
(
	id bigint NOT NULL,
    drink_id bigint NOT NULL,
    drink_amount integer NOT NULL,
    bill_id bigint NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (drink_id) REFERENCES drink(id),
	FOREIGN KEY (bill_id) REFERENCES bill(id)
);

CREATE TABLE IF NOT EXISTS dish_bill
(
	id bigint NOT NULL,
    dish_id bigint NOT NULL,
    dish_amount integer NOT NULL,
    bill_id bigint NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (dish_id) REFERENCES dish(id),
	FOREIGN KEY (bill_id) REFERENCES bill(id)
);
