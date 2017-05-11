create sequence hibernate_sequence
;

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

create table images
(
  id integer not null
    constraint images_pkey
    primary key,
  url varchar(255),
  recipe_id integer
)
;

create table ingredients
(
  id integer not null
    constraint ingredients_pkey
    primary key,
  name varchar(255),
  quantity integer,
  unit varchar(255),
  recipe_id integer
)
;

create table recipes
(
  id integer not null
    constraint recipes_pkey
    primary key,
  name varchar(255),
  difficulty varchar(255),
  description varchar(255),
  duration integer,
  icon_image varchar(255),
  uuid varchar(255)
)
;

create index recipes_name_index
  on recipes (name)
;

alter table images
  add constraint fkjjw47inkeo3eewwl4dbsnd1f0
foreign key (recipe_id) references recipes
;

alter table ingredients
  add constraint fkqjmq3leho52vlop18r1ml4juy
foreign key (recipe_id) references recipes
;

create table steps
(
  id integer not null
    constraint steps_pkey
    primary key,
  name varchar(255),
  description varchar(255),
  recipe_id integer
    constraint fkl0ribpe0br3dtjb3gpai3hx8t
    references recipes
)
;

CREATE SEQUENCE public.recipes_id_seq NO MINVALUE NO MAXVALUE NO CYCLE;
ALTER TABLE public.recipes ALTER COLUMN id SET DEFAULT nextval('public.recipes_id_seq');
CREATE SEQUENCE public.steps_id_seq NO MINVALUE NO MAXVALUE NO CYCLE;
ALTER TABLE public.steps ALTER COLUMN id SET DEFAULT nextval('public.steps_id_seq');
CREATE SEQUENCE public.ingredients_id_seq NO MINVALUE NO MAXVALUE NO CYCLE;
ALTER TABLE public.ingredients ALTER COLUMN id SET DEFAULT nextval('public.ingredients_id_seq');
ALTER SEQUENCE public.ingredients_id_seq OWNED BY public.ingredients.id;
CREATE SEQUENCE public.images_id_seq NO MINVALUE NO MAXVALUE NO CYCLE;
ALTER TABLE public.images ALTER COLUMN id SET DEFAULT nextval('public.images_id_seq');
ALTER SEQUENCE public.images_id_seq OWNED BY public.images.id;
ALTER TABLE public.recipes ALTER COLUMN uuid SET DEFAULT uuid_generate_v4();
ALTER TABLE public.recipes ALTER COLUMN uuid SET NOT NULL;
CREATE UNIQUE INDEX recipes_uuid_uindex ON public.recipes (uuid);

ALTER TABLE public.images ALTER COLUMN recipe_id SET NOT NULL;
ALTER TABLE public.ingredients ALTER COLUMN recipe_id SET NOT NULL;
ALTER TABLE public.steps ALTER COLUMN recipe_id SET NOT NULL;

ALTER TABLE public.steps DROP CONSTRAINT fkl0ribpe0br3dtjb3gpai3hx8t;
ALTER TABLE public.steps
  ADD CONSTRAINT fkl0ribpe0br3dtjb3gpai3hx8t
FOREIGN KEY (recipe_id) REFERENCES recipes (id) ON DELETE CASCADE;

ALTER TABLE public.ingredients DROP CONSTRAINT fkqjmq3leho52vlop18r1ml4juy;
ALTER TABLE public.ingredients
  ADD CONSTRAINT fkqjmq3leho52vlop18r1ml4juy
FOREIGN KEY (recipe_id) REFERENCES recipes (id) ON DELETE CASCADE;

ALTER TABLE public.images DROP CONSTRAINT fkjjw47inkeo3eewwl4dbsnd1f0;
ALTER TABLE public.images
  ADD CONSTRAINT fkjjw47inkeo3eewwl4dbsnd1f0
FOREIGN KEY (recipe_id) REFERENCES recipes (id) ON DELETE CASCADE;
ALTER TABLE public.images ALTER COLUMN url TYPE TEXT USING url::TEXT;
ALTER TABLE public.images ALTER COLUMN url SET NOT NULL;
ALTER TABLE public.ingredients ALTER COLUMN name TYPE TEXT USING name::TEXT;
ALTER TABLE public.ingredients ALTER COLUMN name SET NOT NULL;
ALTER TABLE public.ingredients ALTER COLUMN recipe_id DROP NOT NULL;
ALTER TABLE public.steps ALTER COLUMN name TYPE TEXT USING name::TEXT;
ALTER TABLE public.steps ALTER COLUMN description TYPE TEXT USING description::TEXT;
ALTER TABLE public.steps ALTER COLUMN description SET NOT NULL;
ALTER TABLE public.steps ALTER COLUMN recipe_id DROP NOT NULL;
ALTER TABLE public.recipes ALTER COLUMN description TYPE TEXT USING description::TEXT;
ALTER TABLE public.images ALTER COLUMN recipe_id DROP NOT NULL;
