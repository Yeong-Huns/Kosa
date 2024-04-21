create or replace procedure proc1(p_empno emp.empno%type)
  is
    v_sal emp.sal%type;
  begin
    select sal into v_sal
    from emp
    where empno = p_empno;

    dbms_output.put_line(v_sal);
  end;
  /

  exec proc1(7788);
  
  
  SELECT * FROM TAB;