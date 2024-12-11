-- V2__alter-medicos-add-telefono.sql
-- Usar IF NOT EXISTS para MySQL
ALTER TABLE medicos
    ADD COLUMN IF NOT EXISTS telefono VARCHAR(20) NULL;