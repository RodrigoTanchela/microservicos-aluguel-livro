-- Table: public.estado

-- DROP TABLE IF EXISTS public.estado;

CREATE TABLE IF NOT EXISTS public.estado
(
    id SERIAL PRIMARY KEY,
    name character varying(50) COLLATE pg_catalog."default" NOT NULL,
    sigla character varying(2) COLLATE pg_catalog."default",
    date_creation timestamp(6) without time zone,
    date_update timestamp(6) without time zone
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.estado
    OWNER to postgres;