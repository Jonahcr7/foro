CREATE TABLE respuesta (
    id BIGSERIAL NOT NULL,
    mensaje VARCHAR(100) NOT NULL,
    topico_id BIGSERIAL NOT NULL,
    fecha_de_creacion TIMESTAMP NOT NULL,
    usuario_id BIGSERIAL NOT NULL,
    solucion BOOLEAN NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_topico_id FOREIGN KEY(topico_id) REFERENCES topico(id),
    CONSTRAINT fk_usuario_id FOREIGN KEY(usuario_id) REFERENCES usuarios(id)
);
