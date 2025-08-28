CREATE TABLE usuario_perfil (
    usuario_id BIGSERIAL NOT NULL,
    perfil_id BIGSERIAL NOT NULL,
    PRIMARY KEY (usuario_id, perfil_id),
    CONSTRAINT fk_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
    CONSTRAINT fk_perfil FOREIGN KEY (perfil_id) REFERENCES perfil(id)
    );
