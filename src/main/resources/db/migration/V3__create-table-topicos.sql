CREATE TABLE topicos (
    id bigint not null,
    titulo varchar(100) not null,
    mensaje varchar(500) not null,
    fecha_de_creacion datetime not null,
    estado varchar(100) not null,
    usuario_id bigint not null,
    curso_id bigint not null,

    primary key(id),
    constraint fk_topicos_usuario_id foreign key(usuario_id) references usuarios(id),
    constraint fk_topicos_curso_id foreign key(curso_id) references cursos(id)
)