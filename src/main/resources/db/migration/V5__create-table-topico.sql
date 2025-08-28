CREATE TABLE topico (
    id BIGSERIAL NOT NULL,
    titulo VARCHAR(100) NOT NULL,
    mensaje VARCHAR(100) NOT NULL,
    fecha_de_creacion TIMESTAMP NOT NULL,
    status VARCHAR(50) NOT NULL,
    usuario_id BIGSERIAL NOT NULL,
    curso_id BIGSERIAL NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_usuario_id FOREIGN KEY(usuario_id) REFERENCES usuarios(id),
    CONSTRAINT fk_curso_id FOREIGN KEY(curso_id) REFERENCES curso(id)
    );
