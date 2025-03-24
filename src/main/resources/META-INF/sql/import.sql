INSERT INTO departments (id, name) VALUES
                                       ('11111111-1111-1111-1111-111111111111', 'Human Resources'),
                                       ('22222222-2222-2222-2222-222222222222', 'Information Technology'),
                                       ('33333333-3333-3333-3333-333333333333', 'Finance');

INSERT INTO admins (id, username, password, address) VALUES
                                                         ('44444444-4444-4444-4444-444444444444', 'admin1', 'adminpass1', '123 Admin St, Adminville'),
                                                         ('55555555-5555-5555-5555-555555555555', 'admin2', 'adminpass2', '456 Admin Ave, Admintown'),
                                                         ('66666666-6666-6666-6666-666666666666', 'admin3', 'adminpass3', '789 Admin Blvd, Admincity');

-- Employees
INSERT INTO employees (id, username, password, address, children_number, leave_balance, performance_rating, hire_date, social_security_number, date_of_birth, salary, department_id) VALUES
                                                                                                                                                                                         ('77777777-7777-7777-7777-777777777777', 'employee1', 'emppass1', '100 Employee Lane, Worktown', 2, 15.0, 4.5, '2018-03-10', '123-45-6789', '1990-05-15', 75000.00, '22222222-2222-2222-2222-222222222222'),
                                                                                                                                                                                         ('88888888-8888-8888-8888-888888888888', 'employee2', 'emppass2', '200 Worker St, Jobcity', 3, 20.0, 4.8, '2015-07-15', '987-65-4321', '1985-08-22', 65000.00, '11111111-1111-1111-1111-111111111111'),
                                                                                                                                                                                         ('99999999-9999-9999-9999-999999999999', 'employee3', 'emppass3', '300 Labor Ave, Employeetown', 1, 10.0, 4.2, '2020-01-05', '456-78-9012', '1988-11-30', 85000.00, '33333333-3333-3333-3333-333333333333');

-- Recruiters
INSERT INTO recruiters (id, username, password, address) VALUES
                                                             ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'recruiter1', 'recpass1', '400 Hiring St, Recruitville'),
                                                             ('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb', 'recruiter2', 'recpass2', '500 Talent Ave, Hiretown'),
                                                             ('cccccccc-cccc-cccc-cccc-cccccccccccc', 'recruiter3', 'recpass3', '600 Candidate Blvd, Staffcity');