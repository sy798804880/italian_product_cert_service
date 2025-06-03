-- 菜单 SQL
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(1929789613208227842, '化学规则', '1927642988125741058', '1', 'chemicalRule', 'masterData/chemicalRule/index', 1, 0, 'C', '0', '0', 'masterData:chemicalRule:list', '#', 103, 1, sysdate(), null, null, '化学规则菜单');

-- 按钮 SQL
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(1929789613208227843, '化学规则查询', 1929789613208227842, '1',  '#', '', 1, 0, 'F', '0', '0', 'masterData:chemicalRule:query',        '#', 103, 1, sysdate(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(1929789613208227844, '化学规则新增', 1929789613208227842, '2',  '#', '', 1, 0, 'F', '0', '0', 'masterData:chemicalRule:add',          '#', 103, 1, sysdate(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(1929789613208227845, '化学规则修改', 1929789613208227842, '3',  '#', '', 1, 0, 'F', '0', '0', 'masterData:chemicalRule:edit',         '#', 103, 1, sysdate(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(1929789613208227846, '化学规则删除', 1929789613208227842, '4',  '#', '', 1, 0, 'F', '0', '0', 'masterData:chemicalRule:remove',       '#', 103, 1, sysdate(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(1929789613208227847, '化学规则导出', 1929789613208227842, '5',  '#', '', 1, 0, 'F', '0', '0', 'masterData:chemicalRule:export',       '#', 103, 1, sysdate(), null, null, '');
