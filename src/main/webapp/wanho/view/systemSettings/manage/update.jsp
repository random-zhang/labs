<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<jsp:include page="/common.jsp"></jsp:include>
</head>
j
<body class="navbar-top">
	<div class="modal-shiftfix">
		<jsp:include page="/menu.jsp"></jsp:include>
		<div class="container-fluid main-content">
			<div class="widget-container fluid-height clearfix mwi1200">
				<div class="heading clearfix">
					<i class="icon-reorder"></i> 修改员工信息 <a href="javascript:;"
						class="pull-right" onclick="history.go(-1);"><i
						class="icon-reply"></i> </a>
				</div>
				<div class="widget-content padded clearfix">
					<form id="updateEmployeeFrom" class="form-horizontal"
						action="EmployeeServlet?method=updateEmployee" method="post">
						<!--   <input type="hidden" name="_csrf" value="eFlReDNyY0IaLgcTCgsgOik3YSh.SgwJJzMgHV8iChoPDR8wQTQZdQ=="> -->

						<div class="form-group">
							<label class="control-label col-md-2">工号</label>
							<div class="col-md-8">
								<input type="text" class="form-control" id="employeeId"
									name="employeeId" value="2" readonly="readonly">
							</div>
						</div>

						<div class="form-group field-manage-name required">
							<label class="control-label col-sm-2" for="manage-name">姓名</label>
							<div class="col-sm-8">
								<input type="text" id="manage-name" class="form-control"
									name="manage-name" value="小红">
							</div>
							<div class="help-block help-block-error"></div>
						</div>

						<div class="form-group field-manage-department_id required">
							<label class="control-label col-sm-2" for="manage-department_id">部门</label>

							<div class="col-sm-8">
								<select id="manage-department_id" class="form-control"
									name="manage-department_id" >
									<option value="0">选择部门</option>
									<option value="1">产品一部</option>
									<option value="2">产品二部</option>
									<option>事业一部</option>
								</select>
							</div>
							<div class="help-block help-block-error"></div>
						</div>

						<div class="form-group field-manage-position_id required">
							<label class="control-label col-sm-2" for="manage-position_id">职位</label>

							<div class="col-sm-8">
								<select id="manage-position_id" class="form-control"
									name="manage-position_id">
									<option value="0">选择职位</option>
									<option>总监</option>
									<option>经理</option>
									<option>普通员工</option>
								</select>
							</div>
							<div class="help-block help-block-error"></div>
						</div>
						<div class="form-group field-manage-mobile required">
							<label class="control-label col-sm-2" for="manage-mobile">手机</label>

							<div class="col-sm-8">
								<input type="text" id="manage-mobile" class="form-control"
									name="manage-mobile" value="18988888888">
							</div>
							<div class="help-block help-block-error"></div>
						</div>
						<div class="form-group field-manage-email">
							<label class="control-label col-sm-2" for="manage-email">电子邮箱</label>
							<div class="col-sm-8">
								<input type="text" id="manage-email" class="form-control"
									name="manage-email" value="7633@qq.com">
							</div>
							<div class="help-block help-block-error"></div>
						</div>

						<div class="form-group field-manage-parentemployeeId">
							<label class="control-label col-sm-2" for="manage-address">上级员工工号</label>
							<div class="col-sm-8">
								<input type="text" id="parentemployeeId" class="form-control"
									name="parentemployeeId" value="10000">
							</div>
							<div class="help-block help-block-error"></div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label"></label>

							<div class="col-lg-10">
								<button id="mysubmit" type="button" class="btn btn-success"
									onclick="commitCheck()">修改</button>
								<button type="button" class="btn btn-default"
									onClick="history.go(-1);">返回</button>
								<input type="hidden" name="reback">
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		
	</script>
</body>

</html>
