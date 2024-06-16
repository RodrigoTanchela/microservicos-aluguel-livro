-- Table: public.categoria

-- DROP TABLE IF EXISTS public.categoria;

CREATE TABLE IF NOT EXISTS categoria
(
    id SERIAL PRIMARY KEY,
    name character varying(50) COLLATE pg_catalog."default" NOT NULL,
    date_creation timestamp(6) without time zone,
    date_update timestamp(6) without time zone
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.categoria
    OWNER to postgres;