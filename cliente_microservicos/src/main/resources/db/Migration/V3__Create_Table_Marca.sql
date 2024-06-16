-- Table: public.categoria

-- DROP TABLE IF EXISTS public.marca;

CREATE TABLE IF NOT EXISTS marca
(
    id SERIAL PRIMARY KEY,
    name character varying(50) COLLATE pg_catalog."default" NOT NULL,
    date_creation timestamp(6) without time zone,
    date_update timestamp(6) without time zone
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.marca
    OWNER to postgres;