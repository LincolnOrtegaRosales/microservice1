create table pizza(
                      id numeric,
                      nombre varchar(200),
                      tipo varchar(50),
                      precio numeric(5,2),
                      autor varchar(50)
);

CREATE SEQUENCE PIZZA_SEQ
    INCREMENT 1
START 1;