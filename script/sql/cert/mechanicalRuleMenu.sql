-- 菜单 SQL
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(1929789610913943553, '机械规则', '1927642988125741058', '1', 'mechanicalRule', 'masterData/mechanicalRule/index', 1, 0, 'C', '0', '0', 'masterData:mechanicalRule:list', '#', 103, 1, sysdate(), null, null, '机械规则菜单');

-- 按钮 SQL
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(1929789610913943554, '机械规则查询', 1929789610913943553, '1',  '#', '', 1, 0, 'F', '0', '0', 'masterData:mechanicalRule:query',        '#', 103, 1, sysdate(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(1929789610913943555, '机械规则新增', 1929789610913943553, '2',  '#', '', 1, 0, 'F', '0', '0', 'masterData:mechanicalRule:add',          '#', 103, 1, sysdate(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(1929789610913943556, '机械规则修改', 1929789610913943553, '3',  '#', '', 1, 0, 'F', '0', '0', 'masterData:mechanicalRule:edit',         '#', 103, 1, sysdate(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(1929789610913943557, '机械规则删除', 1929789610913943553, '4',  '#', '', 1, 0, 'F', '0', '0', 'masterData:mechanicalRule:remove',       '#', 103, 1, sysdate(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(1929789610913943558, '机械规则导出', 1929789610913943553, '5',  '#', '', 1, 0, 'F', '0', '0', 'masterData:mechanicalRule:export',       '#', 103, 1, sysdate(), null, null, '');
