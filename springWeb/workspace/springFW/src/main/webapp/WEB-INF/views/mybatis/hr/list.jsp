<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CoderBy</title>
</head>
<body>
<h1>사원 목록</h1>
${message}
<a href="<c:url value='/hr/insert'/>">신규 사원 정보 입력</a>
<table border="1">
<tr>
	<th>EMPLOYEE_ID</th>
	<th>FIRST_NAME</th>
	<th>LAST_NAME</th>
	<th>EMAIL</th>
	<th>PHONE_NUMBER</th>
	<th>HIRE_DATE</th>
	<th>JOB_ID</th>
	<th>SALARY</th>
	<th>COMMISSION_PCT</th>
	<th>MANAGER_ID</th>
	<th>DEPARTMENT_ID</th>
</tr>
<c:forEach var="emp" items="${empList}">
<tr>
	<c:url var="empDetailsURI" value="/hr/${emp.employeeId}"/>
	<td><a href="${empDetailsURI}">${emp.employeeId}</a></td>
	<td>${emp.firstName}</td>
	<td>${emp.lastName}</td>
	<td>${emp.email}</td>
	<td>${emp.phoneNumber}</td>
	<td>${emp.hireDate}</td>
	<td>${emp.jobId}</td>
	<td>${emp.salary}</td>
	<td>${emp.commissionPct}</td>
	<td>${emp.managerId}</td>
	<td>${emp.departmentId}</td>
</tr>
</c:forEach>
</table>

<table>
	<thead>
		<tr>
			<th>사원번호</th><th>First Name</th><th>Last Name</th>
		</tr>
	</thead>
	<tbody id="emp_list">
	</tbody>
</table>
	<button type="button" id="btnList">리스트</button>
	<button type="button" id="btnEmp">100번 사원</button>

	<script type="text/javascript" src="<c:url value='/js/jquery-3.6.3.min.js'/>"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			/*
			$.post('./json', function() {
				console.log("요청 처리중");
			}).done(function(data) {
				//console.log(data);
				//data.forEach((item) => console.log(item));
				data.forEach((item) => {
					$('#emp_list').append('<tr><td>'+ item.employeeId +'</td><td>'+ item.firstName +'</td><td>'+ item.lastName +'</td></tr>');
				});
			});
			*/
		});
		
		$('#btnList').on('click', function() {
			$.post('./json',{}, function() {
				console.log("요청 처리중");
			}).done(function(data) {
				data.forEach((item) => {
					$('#emp_list').append('<tr><td>'+ item.employeeId +'</td><td>'+ item.firstName +'</td><td>'+ item.lastName +'</td></tr>');
				});
			});			
		});

		$('#btnEmp').on('click', function() {
			$.post('./json/100', function() {
				console.log("요청 처리중");
			}).done(function(item) {
				$('#emp_list').empty().append('<tr><td>'+ item.employeeId +'</td><td>'+ item.firstName +'</td><td>'+ item.lastName +'</td></tr>');
			});			
		});
	</script>
</body>
</html>