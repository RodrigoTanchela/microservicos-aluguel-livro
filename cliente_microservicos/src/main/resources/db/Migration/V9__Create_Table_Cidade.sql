-- Table: public.estado

-- DROP TABLE IF EXISTS public.estado;

CREATE TABLE IF NOT EXISTS public.cidade
(
    id SERIAL PRIMARY KEY,
    name character varying(50) COLLATE pg_catalog."default" NOT NULL,
    date_creation timestamp(6) without time zone,
    date_update timestamp(6) without time zone,
	estado_id bigint,
	FOREIGN KEY (estado_id) REFERENCES estado(id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.estado
    OWNER to postgres;