drop table spring_member;

create table spring_member(
    member_id varchar2(30)primary key
    ,member_pw varchar2(30) not null
);

drop table spring_board;
drop sequence board_seq;

create table spring_board(
    board_num number primary key
    ,board_title varchar2(100) not null
    ,board_content varchar2(3000)
    ,member_id varchar2(30) references spring_member not null
    ,board_indate date default sysdate
    ,board_count number default 0
);

create sequence board_seq nocache;

insert into spring_board
values(
    board_seq.nextval
    ,'2등'
    ,null
    ,'ohminsu'
    , sysdate
);
commit;

create table spring_reply(
    board_num number references spring_board not null
    ,member_id varchar2(30) references spring_member not null
    ,reply_content varchar2(1000) not null
    ,reply_indate date default sysdate
);
drop sequence reply_seq;
create sequence reply_seq start with 7 nocache;

drop table spring_file;

create table spring_file(
    board_num number references spring_board
    ,saved_file_name varchar2(1000) not null
    ,original_file_name varchar2(1000) not null
);
            

