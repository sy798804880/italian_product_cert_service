-- 菜单 SQL
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(1929789612679745538, '物料信息', '1927642988125741058', '1', 'articleInfo', 'masterData/articleInfo/index', 1, 0, 'C', '0', '0', 'masterData:articleInfo:list', '#', 103, 1, sysdate(), null, null, '物料信息菜单');

-- 按钮 SQL
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(1929789612679745539, '物料信息查询', 1929789612679745538, '1',  '#', '', 1, 0, 'F', '0', '0', 'masterData:articleInfo:query',        '#', 103, 1, sysdate(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(1929789612679745540, '物料信息新增', 1929789612679745538, '2',  '#', '', 1, 0, 'F', '0', '0', 'masterData:articleInfo:add',          '#', 103, 1, sysdate(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(1929789612679745541, '物料信息修改', 1929789612679745538, '3',  '#', '', 1, 0, 'F', '0', '0', 'masterData:articleInfo:edit',         '#', 103, 1, sysdate(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(1929789612679745542, '物料信息删除', 1929789612679745538, '4',  '#', '', 1, 0, 'F', '0', '0', 'masterData:articleInfo:remove',       '#', 103, 1, sysdate(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(1929789612679745543, '物料信息导出', 1929789612679745538, '5',  '#', '', 1, 0, 'F', '0', '0', 'masterData:articleInfo:export',       '#', 103, 1, sysdate(), null, null, '');
