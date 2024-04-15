SELECT * FROM TAB;

SET SERVEROUTPUT ON;

DECLARE
  PROCEDURE p (
    sales  NUMBER,
    quota  NUMBER,
    emp_id NUMBER
  )
  IS
    bonus    NUMBER := 0;
    updated  VARCHAR2(3) := 'No';
  BEGIN
    IF sales > (quota + 200) THEN
      bonus := (sales - quota)/4;
 
      UPDATE employees
      SET salary = salary + bonus 
      WHERE employee_id = emp_id;
 
      updated := 'Yes';
    END IF;
 
    DBMS_OUTPUT.PUT_LINE (
      'Table updated?  ' || updated || ', ' || 
      'bonus = ' || bonus || '.'
    );
  END p;
BEGIN
  p(10100, 10000, 120);
  p(10500, 10000, 121);
END;
/
 
 
 
DECLARE
  PROCEDURE p (
    sales  NUMBER,
    quota  NUMBER,
    emp_id NUMBER
  )
  IS
    bonus  NUMBER := 0;
  BEGIN
    IF sales > (quota + 200) THEN
      bonus := (sales - quota)/4;
    ELSE
      bonus := 50;
    END IF;
    DBMS_OUTPUT.PUT_LINE('bonus = ' || bonus);
    UPDATE employees
    SET salary = salary + bonus 
    WHERE employee_id = emp_id;
  END p;
BEGIN
  p(10100, 10000, 120);
  p(10500, 10000, 121);
END;
/


