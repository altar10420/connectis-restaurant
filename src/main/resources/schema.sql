CREATE TABLE client
(
	id bigint NOT NULL,
	name character varying(50) NOT NULL,
	surname character varying(50) NOT NULL,
	discount double precision NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE employee
(
    id bigint NOT NULL,
    name character varying(50) NOT NULL,
    surname character varying(50) NOT NULL,
	position character varying(50) NOT NULL,
	salary double precision NOT NULL,
	managers_id bigint NOT NULL,
	pesel integer,
	PRIMARY KEY (id),
	FOREIGN KEY (managers_id) REFERENCES employee(id)
);

CREATE TABLE dish
(
	id bigint NOT NULL,
	name character varying(50) NOT NULL,
	description character varying(255) NOT NULL,
	price double precision NOT NULL,
	is_available boolean NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE drink
(
	id bigint NOT NULL,
	name character varying(50) NOT NULL,
	description character varying(255) NOT NULL,
	price double precision NOT NULL,
	is_available boolean NOT NULL,
	portion_ml integer,
	PRIMARY KEY (id)
);

CREATE TABLE product
(
	id bigint NOT NULL,
	name character varying(50) NOT NULL,
	stored_amount integer NOT NULL
);

CREATE TABLE bill
(
	id bigint NOT NULL,
	date timestamp NOT NULL,
	price double precision NOT NULL,
	tip double precision NOT NULL,
    client_id bigint,
    employee_id bigint,
	PRIMARY KEY (id),
	FOREIGN KEY (employee_id) REFERENCES employee(id),
	FOREIGN KEY (client_id) REFERENCES client(id)
);

CREATE TABLE drink_bill
(
	id bigint NOT NULL,
    drink_id bigint NOT NULL,
    drink_amount integer NOT NULL,
    bill_id bigint NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (drink_id) REFERENCES drink(id),
	FOREIGN KEY (bill_id) REFERENCES bill(id)
);

CREATE TABLE dish_bill
(
	id bigint NOT NULL,
    dish_id bigint NOT NULL,
    dish_amount integer NOT NULL,
    bill_id bigint NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (dish_id) REFERENCES dish(id),
	FOREIGN KEY (bill_id) REFERENCES bill(id)
);
