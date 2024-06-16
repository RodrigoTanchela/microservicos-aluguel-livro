CREATE TABLE IF NOT EXISTS usuario_permissao (
  id_usuario bigint NOT NULL,
  id_permissao bigint NOT NULL,
  PRIMARY KEY (id_usuario, id_permissao),
  CONSTRAINT fk_user_permission FOREIGN KEY (id_usuario) REFERENCES usuarios (id),
  CONSTRAINT fk_user_permission_permission FOREIGN KEY (id_permissao) REFERENCES permissao
(id)
);