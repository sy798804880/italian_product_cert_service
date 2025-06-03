-- 菜单 SQL
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(1929789612356784129, '合金信息', '1927642988125741058', '1', 'alloyInfo', 'masterData/alloyInfo/index', 1, 0, 'C', '0', '0', 'masterData:alloyInfo:list', '#', 103, 1, sysdate(), null, null, '合金信息菜单');

-- 按钮 SQL
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(1929789612356784130, '合金信息查询', 1929789612356784129, '1',  '#', '', 1, 0, 'F', '0', '0', 'masterData:alloyInfo:query',        '#', 103, 1, sysdate(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(1929789612356784131, '合金信息新增', 1929789612356784129, '2',  '#', '', 1, 0, 'F', '0', '0', 'masterData:alloyInfo:add',          '#', 103, 1, sysdate(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(1929789612356784132, '合金信息修改', 1929789612356784129, '3',  '#', '', 1, 0, 'F', '0', '0', 'masterData:alloyInfo:edit',         '#', 103, 1, sysdate(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(1929789612356784133, '合金信息删除', 1929789612356784129, '4',  '#', '', 1, 0, 'F', '0', '0', 'masterData:alloyInfo:remove',       '#', 103, 1, sysdate(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(1929789612356784134, '合金信息导出', 1929789612356784129, '5',  '#', '', 1, 0, 'F', '0', '0', 'masterData:alloyInfo:export',       '#', 103, 1, sysdate(), null, null, '');
