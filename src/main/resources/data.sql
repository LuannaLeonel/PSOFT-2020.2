insert into usuario (ID,  PASSWORD, TIPO, USERNAME)
values(1, '123', 'HOSPITAL', 'hospital');

insert into usuario (ID,  PASSWORD, TIPO, USERNAME)
values(2, '123', 'ADMINISTRADOR', 'admin');

insert into usuario (ID,  PASSWORD, TIPO, USERNAME)
values(3, '123', 'LABORATORIO', 'laboratorio');

insert into usuario (ID,  PASSWORD, TIPO, USERNAME)
values(4, '123', 'FUNCIONARIO', 'funcionario');

insert into hospital (ID,  CASOS_RECUPERADOS, TOTAL_CASOS_CONFIRMADOS ,NOME, QTD_OBITOS, OCUPACAO_LEITOS, QTD_LEITOS_ENFERMARIA, QTD_LEITOSUTI, USUARIO_ID, CNPJ, CEP)
values(1, 0, 0, 'hospital', 0, 0, 0, 0, 1, 118119120121, 15013789);

insert into laboratorio_testes (ID,  CEP, CNPJ, NOME, USUARIO_ID)
values(1, 12345678, 12345678000112, 'Lab 1', 3);

insert into funcionario (ID,  CPF, NOME, SEXO, USUARIO_ID)
values(1, 12345678, 'nando', 'MASCULINO', 4);




