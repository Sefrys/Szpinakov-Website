CREATE TABLE public.users
(
  id serial PRIMARY KEY,
  login text NOT NULL,
  password text NOT NULL,
  email text NOT NULL
);
CREATE UNIQUE INDEX users_login_uindex ON public.users (login);
