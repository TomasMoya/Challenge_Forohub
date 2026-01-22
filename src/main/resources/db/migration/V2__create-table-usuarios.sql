CREATE TABLE usuarios (
    id bigint not null,
    nombre varchar(100) not null,
    email varchar(200) not null,
    estado varchar(100) not null,
    curso_id bigint not null,

    primary key(id),
    constraint fk_usuarios_curso_id foreign key(curso_id) references cursos(id)
)