/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  luiz
 * Created: 22 de jun de 2020
 */
create table cliente (
	id bigint not null auto_increment,
    nome varchar(60) not null,
    email varchar(255) not null,
    telefone varchar(20) not null,
    
    primary key (id)
);

