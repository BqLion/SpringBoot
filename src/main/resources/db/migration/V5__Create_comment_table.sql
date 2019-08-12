create table comment
(
	id BIGINT,
	parent_id BIGINT,
	type INT,
	commentator int,
	gmt_create BIGINT,
	gmt_modified BIGINT
);

comment on column comment.parent_id is '父类ID';

comment on column comment.type is '父类类型';

comment on column comment.commentator is '评论人id';

comment on column comment.gmt_create is '创建时间';

comment on column comment.gmt_modified is '更新时间';
