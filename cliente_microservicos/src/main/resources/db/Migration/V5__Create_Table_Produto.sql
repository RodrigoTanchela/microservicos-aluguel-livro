-- Table: public.produto

-- DROP TABLE IF EXISTS public.produto;

CREATE TABLE IF NOT EXISTS public.produto
(
    id SERIAL PRIMARY KEY,
    description_long character varying(255) COLLATE pg_catalog."default",
    description_short character varying(50) COLLATE pg_catalog."default" NOT NULL,
    sales_value double precision,
    value_cost double precision NOT NULL,
    categoria_id bigint,
    marca_id bigint,
    date_creation timestamp(6) without time zone,
    date_update timestamp(6) without time zone,
    FOREIGN KEY (categoria_id) REFERENCES categoria(id),
	FOREIGN KEY (marca_id) REFERENCES marca(id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.produto
    OWNER to postgres;