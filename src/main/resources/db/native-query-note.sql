# UserService.findAll
select t1.u_id,
       t1.u_username,
       t1.u_password,
       t1.u_name,
       t1.u_nickname,
       t1.u_email,
       t1.u_phone,
       t1.u_access_ip,
       t1.u_login_fail_ip,
       t1.u_login_fail_count,
       t1.u_status_message,
       t1.u_background_image,
       t1.u_profile_image,
       t1.u_login_at,
       t1.u_login_failed_at,
       t1.u_password_changed_at,
       t1.a_id,
       t1.a_name,
       t1.a_description,
       t2.d_id,
       t2.d_name,
       t2.d_description,
       t2.d_department_id
from (
         select u.id                  as u_id,
                u.username            as u_username,
                u.password            as u_password,
                u.name                as u_name,
                u.nickname            as u_nickname,
                u.email               as u_email,
                u.phone               as u_phone,
                u.access_ip           as u_access_ip,
                u.login_fail_ip       as u_login_fail_ip,
                u.login_fail_count    as u_login_fail_count,
                u.status_message      as u_status_message,
                u.background_image    as u_background_image,
                u.profile_image       as u_profile_image,
                u.login_at            as u_login_at,
                u.login_failed_at     as u_login_failed_at,
                u.password_changed_at as u_password_changed_at,
                a.id                  as a_id,
                a.name                as a_name,
                a.description         as a_description
         from user u
                  inner join user_authority ua
                             on u.id = ua.user_id
                  inner join authority a
                             on ua.authority_id = a.id
     ) t1
         inner join
     (
         select u.id            as u_id,
                d.id            as d_id,
                d.name          as d_name,
                d.description   as d_description,
                d.department_id as d_department_id
         from user u
                  left outer join user_department ud
                                  on u.id = ud.user_id
                  left outer join department d
                                  on ud.department_id = d.id
     ) t2
     on t1.u_id = t2.u_id

order by t1.u_id, t1.a_id, t2.d_id;