CREATE TABLE person_info(
	  id VARCHAR (10) NOT NULL,
		name VARCHAR(12) NOT NULL,
		age int2 NULL,
		sex CHAR(2) NOT NULL DEFAULT '1',
		join_time TIMESTAMP NULL DEFAULT now(),
		CONSTRAINT person_info_pkey PRIMARY KEY (id)
) PARTITION BY HASH(id)

-- 表HASH分区
CREATE TABLE person_info_0 PARTITION OF person_info FOR VALUES WITH(MODULUS 10,REMAINDER 0);
CREATE TABLE person_info_1 PARTITION OF person_info FOR VALUES WITH(MODULUS 10,REMAINDER 1);
CREATE TABLE person_info_2 PARTITION OF person_info FOR VALUES WITH(MODULUS 10,REMAINDER 2);
CREATE TABLE person_info_3 PARTITION OF person_info FOR VALUES WITH(MODULUS 10,REMAINDER 3);
CREATE TABLE person_info_4 PARTITION OF person_info FOR VALUES WITH(MODULUS 10,REMAINDER 4);
CREATE TABLE person_info_5 PARTITION OF person_info FOR VALUES WITH(MODULUS 10,REMAINDER 5);
CREATE TABLE person_info_6 PARTITION OF person_info FOR VALUES WITH(MODULUS 10,REMAINDER 6);

-- 表结构
select a.attname                                                       as 列名,
       format_type(a.atttypid, a.atttypmod)                            as 类型,
       (case when a.attlen > 0 then a.attlen else a.atttypmod - 4 end) as 长度,
       a.attnotnull                                                    as 是否可为空,
       col_description(a.attrelid, a.attnum)                           as 备注
from pg_class c,
     pg_attribute a
       left join (select a.attname
                  from pg_class c,
                       pg_attribute a,
                       pg_attrdef ad
                  where relname = 'person_info'
                    and ad.adrelid = c.oid
                    and adnum = a.attnum
                    and attrelid = c.oid) as d on a.attname = d.attname
where c.relname = 'person_info'
  and a.attrelid = c.oid
  and a.attnum > 0;

