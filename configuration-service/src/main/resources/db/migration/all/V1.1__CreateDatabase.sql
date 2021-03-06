CREATE TABLE IF NOT EXISTS config.iconnector_info
(
    id bigserial NOT NULL,
    connector_id character varying(255) COLLATE pg_catalog."default" NOT NULL UNIQUE,
    status integer,
    collect_status integer,
    version character varying(255) COLLATE pg_catalog."default",
    date_time bigint,
    enable boolean,
    CONSTRAINT iconnector_info_pkey PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS config.iconnector_info
    OWNER to sigreen;

CREATE TABLE IF NOT EXISTS config.iconnector_config
(
    id bigserial NOT NULL,
    name character varying(255) COLLATE pg_catalog."default",
    info_id bigint,
    CONSTRAINT iconnector_config_pkey PRIMARY KEY (id),
    CONSTRAINT iconnector_info_config FOREIGN KEY (info_id)
        REFERENCES config.iconnector_info (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

ALTER TABLE IF EXISTS config.iconnector_config
    OWNER to sigreen;

CREATE TABLE IF NOT EXISTS config.iconnector_module
(
    id bigserial NOT NULL,
    name character varying(255) COLLATE pg_catalog."default",
    version character varying(255) COLLATE pg_catalog."default",
    status integer,
    info_id bigint,
    CONSTRAINT iconnector_module_pkey PRIMARY KEY (id),
    CONSTRAINT iconnector_info_module FOREIGN KEY (info_id)
        REFERENCES config.iconnector_info (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

ALTER TABLE IF EXISTS config.iconnector_module
    OWNER to sigreen;