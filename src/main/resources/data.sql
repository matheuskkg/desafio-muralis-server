insert into tipos_contatos(tpc_tipo) values('e-mail') on conflict (tpc_tipo) do nothing;
insert into tipos_contatos(tpc_tipo) values('telefone') on conflict (tpc_tipo) do nothing;
