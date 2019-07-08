<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
	<jsp:include page="/common.jsp"></jsp:include>
</head>

<body class="navbar-top">
	<div class="modal-shiftfix">
		<jsp:include page="/menu.jsp"></jsp:include>
		<div class="container-fluid main-content">
			<div class="widget-container fluid-height clearfix mwi1200">
				<div class="heading clearfix">
					<i class="icon-reorder"></i> 更新客户 <a href="javascript:;"
						class="pull-right" onclick="history.go(-1);"><i
						class="icon-reply"></i> </a>
				</div>
				<div class="widget-content padded clearfix">
					<form id="w0" class="form-horizontal"
						action="view/customerManage/customer/index.jsp" method="get">
						<input type="hidden" name="_csrf"
							value="WE5pUXNvMmQ6OT86ShZxHAkgWQE.V10vByQYNB8/WzwvGicZASlIUw==">
						<input type="hidden" id="customer-owner_id" class="owner_id"
							name="Customer[owner_id]" value="2"> <input type="hidden"
							id="customer-department_id" class="department_id"
							name="Customer[department_id]" value="1"> <input
							type="hidden" id="customer-position_id" class="position_id"
							name="Customer[position_id]" value="1">
						<div class="form-group field-customer-owner_name">
							<label class="control-label col-sm-2" for="customer-owner_name">负责人</label>
							<div class="col-sm-8">
								<input type="hidden" id="customerId" value="1" /> <input
									type="text" id="PRINPICAL"
									class="owner_name form-control weiBoxs-dialog"
									readonly="readonly" name="Customer[owner_name]" value="黄秋"
									dialog-size="large" onclick="selectLinkMan()"
									dialog-title="选择所属人" dialog-url="/manage/select-user.html"
									placeholder="负责人"> <input type="hidden" id="linkManId"
									value="1" />
							</div>
							<div class="help-block help-block-error"></div>
						</div>
						<div class="form-group field-customer-customer_name required">
							<label class="control-label col-sm-2"
								for="customer-customer_name">客户名称</label>

							<div class="col-sm-8">
								<input type="text" id="CUSTOMERNAME" class="form-control"
									name="Customer[customer_name]" value="百度"
									onfocus="$(this).onlypressnum();" placeholder="客户名称">
							</div>
							<div class="help-block help-block-error"></div>
						</div>
						<div class="form-group field-customer-customer_zipcode required">
							<label class="control-label col-sm-2"
								for="customer-customer_zipcode">邮编</label>

							<div class="col-sm-8">
								<input type="text" id="POST_CODE" class="form-control"
									name="Customer[customer_zipcode]" value="210000"
									onfocus="$(this).onlypressnum();" placeholder="邮编">
							</div>
							<div class="help-block help-block-error"></div>
						</div>
						<div class="form-group field-customer-customer_industry required">
							<label class="control-label col-sm-2"
								for="customer-customer_industry">所属行业</label>

							<div class="col-sm-8">
								<input type="hidden" id="fieldId" value="12" /> <select
									id="FIELD_ID" class="form-control"
									name="Customer[customer_industry]">
									<option value="">请选择...</option>
									<option value="0001">集团/上市公司</option>
									<option value="0002">大型企业</option>
									<option value="0003">设计院/研究所</option>
									<option value="0004">政府/学校/社团</option>
									<option value="0005">成长性企业</option>
									<option value="0006">其他</option>
								</select>
							</div>
							<div class="help-block help-block-error"></div>
						</div>
						<div class="form-group field-customer-customer_origin required">
							<label class="control-label col-sm-2"
								for="customer-customer_origin">客户来源</label>

							<div class="radio-list col-sm-8">
								<input type="hidden" id="selectHidden" value="1" />
								<div id="customer-customer_origin">
									<label class="label-radio inline"> <input type="radio"
										name="Customer[customer_origin]" class="aa" value="1001">
										<span class="custom-radio"></span>网络营销 </label> <label
										class="label-radio inline"> <input type="radio"
										name="Customer[customer_origin]" class="aa" value="1002">
										<span class="custom-radio"></span>公开媒体 </label> <label
										class="label-radio inline"> <input type="radio"
										name="Customer[customer_origin]" class="aa" value="1002">
										<span class="custom-radio"></span>合作伙伴 </label> <label
										class="label-radio inline"> <input type="radio"
										name="Customer[customer_origin]" class="aa" value="1003">
										<span class="custom-radio"></span>员工介绍 </label> <label
										class="label-radio inline"> <input type="radio"
										name="Customer[customer_origin]" class="aa" value="1004">
										<span class="custom-radio"></span>广告 </label> <label
										class="label-radio inline"> <input type="radio"
										name="Customer[customer_origin]" class="aa" value="1005">
										<span class="custom-radio"></span>推销电话 </label> <label
										class="label-radio inline"> <input type="radio"
										name="Customer[customer_origin]" class="aa" value="1006">
										<span class="custom-radio"></span>朋友推荐 </label> <label
										class="label-radio inline"> <input type="radio"
										name="Customer[customer_origin]" class="aa" value="1007">
										<span class="custom-radio"></span>同事介绍 </label> <label
										class="label-radio inline"> <input type="radio"
										name="Customer[customer_origin]" class="aa" value="1008">
										<span class="custom-radio"></span>其他 </label>
								</div>
								<div class="help-block help-block-error"></div>
							</div>
						</div>
						<div class="form-group field-customer-annual_revenue">
							<label class="control-label col-sm-2"
								for="customer-annual_revenue">年营业额</label>

							<div class="col-sm-8">
								<input type="hidden" id="busubessVolume" value="阿桑" /> <select
									id="BUSUBESS_VOLUME" class="form-control"
									name="Customer[annual_revenue]">
									<option value="">请选择...</option>
									<option value="2001">1000万以上</option>
									<option value="2002">500万以上</option>
									<option value="2003">100万以上</option>
									<option value="2004">10万以上</option>
									<option value="2005">10万以下</option>
								</select>
							</div>
							<div class="help-block help-block-error"></div>
						</div>
						<div class="form-group field-customer-customer_employee required">
							<label class="control-label col-sm-2"
								for="customer-customer_employee">员工数</label>

							<div class="col-sm-8">
								<select id="EMPLOYEE_NUMBERS" class="form-control"
									name="Customer[customer_employee]">
									<option value="">请选择...</option>
									<option value="5-20" selected>5-20</option>
									<option value="21-50">21-50</option>
									<option value="50以上">50以上</option>
								</select>
							</div>
							<div class="help-block help-block-error"></div>
						</div>
						<div class="form-group field-customer-customer_tag required">
							<label class="control-label col-sm-2" for="customer-customer_tag">标签</label>

							<div class="col-sm-8">
								<input type="text" id="TAG" class="form-control"
									name="Customer[customer_tag]" value="1"
									onfocus="$(this).onlypressnum();" placeholder="标签">
							</div>
							<div class="help-block help-block-error"></div>
						</div>
						<div class="form-group field-customer-customer_remarks">
							<label class="control-label col-sm-2"
								for="customer-customer_remarks">备注</label>

							<div class="col-sm-8">
								<textarea id="REMARKS" class="form-control"
									name="Customer[customer_remarks]" style="height: 80px;"
									placeholder="备注">大客户,提成5w+</textarea>
							</div>
							<div class="help-block help-block-error"></div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label"></label>

							<div class="col-lg-10">
								<button type="button" class="btn btn-primary"
									onclick="updateCustomer()">修改</button>
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
	<!-- 	联系人模态框 -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<input type="hidden" id="referencepositionId"
						name="referencepositionId">
					<h4 class="modal-title" id="myModalLabel">选择负责人</h4>
				</div>
				<div class="modal-body">
					<table class="table" id="example">
						<!-- 							<caption>响应式表格布局</caption> -->
						<thead>
							<tr>
								<th>选择</th>
								<th>姓名</th>
								<th>职位</th>
								<th>手机号码</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td><input type="radio" name="radioName"
									style=" opacity: 1;margin-top: -6px " id="1" /></td>
								<td id="1_lg">阿萨德</td>
								<td>洒出</td>
								<td>123</td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="modal-footer">
					<button type="button" id="checkPass" class="btn btn-primary"
						onclick="postCheckInfo()()">确认</button>
					<button type="button" id="checkRefuse" class="btn btn-primary"
						onclick="postCheckDocInfo()">取消</button>
				</div>
			</div>

		</div>
	</div>

</body>
<script type="text/javascript">
	function selectLinkMan() {
		$("#myModal").modal("show");
		// 	$("#divshow").show();
	}

	function postCheckInfo() {
		var list = document.getElementsByName("radioName");
		for ( var i = 0; i < list.length; i++) {
			if (list[i].checked == true) {
				var id = list[i].id;
				var textName = document.getElementById(id + "_lg").innerText;
				$("#PRINPICAL").val("");
				$("#linkManId").val("");
				$("#PRINPICAL").val(textName);
				$("#linkManId").val(id);
			}
		}
		$("#myModal").modal("hide");
	}

	function postCheckDocInfo() {
		$("#myModal").modal("hide");
	}

	var list = document.getElementsByName("Customer[customer_origin]");
	var valueId = $("#selectHidden").val();
	for ( var i = 0; i < list.length; i++) {
		if (list[i].value == valueId) {
			list[i].checked = true;
		}
	}
	var fileId = $("#fieldId").val();
	if (fileId == "1") {
		$("#FIELD_ID ").get(0).options[1].selected = true;
	} else if (fileId == "2") {
		$("#FIELD_ID ").get(0).options[2].selected = true;
	} else if (fileId == "3") {
		$("#FIELD_ID ").get(0).options[3].selected = true;
	} else if (fileId == "4") {
		$("#FIELD_ID ").get(0).options[4].selected = true;
	} else if (fileId == "5") {
		$("#FIELD_ID ").get(0).options[5].selected = true;
	} else if (fileId == "6") {
		$("#FIELD_ID ").get(0).options[6].selected = true;
	}

	var busubessVolume = $("#busubessVolume").val();
	if (busubessVolume == "2001") {
		$("#BUSUBESS_VOLUME").get(0).options[1].selected = true;
	} else if (busubessVolume == "2002") {
		$("#BUSUBESS_VOLUME").get(0).options[2].selected = true;
	} else if (busubessVolume == "2003") {
		$("#BUSUBESS_VOLUME").get(0).options[3].selected = true;
	} else if (busubessVolume == "2004") {
		$("#BUSUBESS_VOLUME").get(0).options[4].selected = true;
	} else if (busubessVolume == "2005") {
		$("#BUSUBESS_VOLUME").get(0).options[5].selected = true;
	}
	function updateCustomer() {
		
	}
</script>

<script type="text/javascript">
	$.extend($.fn.dataTable.defaults, {
		"searching" : false,
		"ordering" : false
	});
	$('#example').DataTable({
		language : {
			"sProcessing" : "处理中...",
			"sLengthMenu" : "显示 _MENU_ 项结果",
			"sZeroRecords" : "没有匹配结果",
			"sInfo" : "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
			"sInfoEmpty" : "显示第 0 至 0 项结果，共 0 项",
			"sInfoFiltered" : "(由 _MAX_ 项结果过滤)",
			"sInfoPostFix" : "",
			"sSearch" : "搜索:",
			"sUrl" : "",
			"sEmptyTable" : "表中数据为空",
			"sLoadingRecords" : "载入中...",
			"sInfoThousands" : ",",
			"oPaginate" : {
				"sFirst" : "首页",
				"sPrevious" : "上页",
				"sNext" : "下页",
				"sLast" : "末页"
			}
		}
	});
</script>
</html>
