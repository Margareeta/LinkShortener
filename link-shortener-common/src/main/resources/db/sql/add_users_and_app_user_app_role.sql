INSERT into app_user (username, first_name, last_name, password, is_account_non_expired,
                      is_account_non_locked, is_credentials_non_expired, is_enabled)
VALUES ('johndoe', 'John', 'Doe', '12345A', true, true, true, true),
       ('janedoe', 'Jane', 'Doe', '54321G', true, true,true, true);

INSERT into app_user_app_role (username, role_id)
VALUES ('johndoe', 'ROLE_ADMIN'),
       ('janedoe', 'ROLE_GUEST');