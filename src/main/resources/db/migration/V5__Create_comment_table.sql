create table comment
(
	id bigint,
	parent_id bigint,
	type int,
	commentator int,
	gmt_create bigint,
	gmt_modified bigint
);

comment on column comment.parent_id is '父类id';

comment on column comment.type is '父类类型';

comment on column comment.commentator is '评论人id';

comment on column comment.gmt_create is '创建时间';

comment on column comment.gmt_modified is '更新时间';
