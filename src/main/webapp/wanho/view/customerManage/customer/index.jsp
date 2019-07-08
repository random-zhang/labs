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
		<input type="hidden" id="inputId" value="1" />
		<div class="container-fluid main-content">
			<div class="widget-container fluid-height clearfix">
				<div class="heading clearfix">
					<i class="icon-table"></i> 客户管理 <a id="add-row"
						class="btn btn-sm btn-primary-outline pull-right"
						href="CustomerServlet?method=getAllZd"><i class="icon-plus"></i>
						新建客户</a>
				</div>
				<div class="widget-content padded clearfix">
					<div class="dataTables_filter">
						<form class="form-inline search-form" id="actionFormSo"
							action="CustomerServlet?method=getAllZd" method="post">
							<div class="btn-group">
								<select id="field" class="form-control"
									onchange="changeCondition()" name="field">
									<option class="" value="">--请选择筛选条件--</option>
									<option class="text" value="customer_name" rel="1"
										url="/fields/boxfield.html">客户名称</option>
									<option class="box" value="customer_industry" rel="1"
										url="/fields/boxfield.html">所属行业</option>
									<option class="box" value="customer_origin" rel="1"
										url="/fields/boxfield.html">客户来源</option>
<!-- 									<option class="owner" value="owner_id" -->
<!-- 										url="/manage/ajax-user.html">负责人</option> -->
									<option class="date" value="created_at">创建时间</option>
									<option class="date" value="updated_at">修改时间</option>
								</select>

								<div class="form-group" id="conditionContent"
									style="display: none">
									<input type="text" id="hiddenId" class="form-control" />
								</div>
								<div class="form-group" id="conditionContentRq"
									style="display: none">
									<input type="date" id="hiddenIdRq" class="form-control" />
								</div>
								<div class="form-group" id="searchContent" style="display: none">
									<select id="selectId" class="form-control">
									</select>
								</div>
							
							</div>
							<div class="btn-group">
								<button type="button" class="btn btn-success" onclick="soo()">搜索</button>
							</div>
						</form>
					</div>

					<div id="w0" class="grid-view">
						<div class="table-responsive">
							<table class="table table-hover">
								<thead>
									<tr>
										<th><label class="label-checkbox"> <input
												type="checkbox" class="select-on-check-all"
												name="selection_all" value="1"><span
												class="custom-checkbox"></span> </label>
										</th>
										<th nowrap>负责人</th>
										<th nowrap>客户名称</th>
										<th nowrap>所属行业</th>
										<th nowrap>客户来源</th>
										<th nowrap>创建时间</th>
										<th nowrap>更新时间</th>
										<th class="action-column">操作</th>
									</tr>
								</thead>
								<tbody>
										<tr data-key="13">
											<td><label class="label-checkbox"> <input
													type="checkbox" name="selection[]" value="13"><span
													class="custom-checkbox"></span> </label>
											</td>
											<td>黄秋</td>
											<td>一号店</td>
											<td>集团</td>
											<td>网络营销</td>
											<td>2017-06-01</td>
											<td>2019-04-25</td>

											<td>
												<div>
													<button type="button" " name="updatebtn"
														class="btn btn-warning btn-sm"
														id="1_ct"
														onclick="checkCustomer(this.id)">查看</button>
													<button type="button" " name="updatebtn"
														class="btn btn-warning btn-sm"
														id="1_xg"
														onclick="updateCustomer(this.id)">修改</button>
												</div></td>
										</tr>
										<tr data-key="13">
											<td><label class="label-checkbox"> <input
													type="checkbox" name="selection[]" value="13"><span
													class="custom-checkbox"></span> </label>
											</td>
											<td>邱水平</td>
											<td>百度</td>
											<td>上市公司</td>
											<td>广告</td>
											<td>2017-06-01</td>
											<td>2022	-04-25</td>

											<td>
												<div>
													<button type="button" " name="updatebtn"
														class="btn btn-warning btn-sm"
														id="1_ct"
														onclick="checkCustomer(this.id)">查看</button>
													<button type="button" " name="updatebtn"
														class="btn btn-warning btn-sm"
														id="1_xg"
														onclick="updateCustomer(this.id)">修改</button>
												</div></td>
										</tr>
										<tr data-key="13">
											<td><label class="label-checkbox"> <input
													type="checkbox" name="selection[]" value="13"><span
													class="custom-checkbox"></span> </label>
											</td>
											<td>秦祎翾</td>
											<td>华为</td>
											<td>大型企业</td>
											<td>同事介绍</td>
											<td>2017-06-01</td>
											<td>2019-04-25</td>

											<td>
												<div>
													<button type="button" " name="updatebtn"
														class="btn btn-warning btn-sm"
														id="1_ct"
														onclick="checkCustomer(this.id)">查看</button>
													<button type="button" " name="updatebtn"
														class="btn btn-warning btn-sm"
														id="1_xg"
														onclick="updateCustomer(this.id)">修改</button>
												</div></td>
										</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		function checkCustomer(id) {
			var customerId = id.split("_ct")[0];
			location.href = 'CustomerServlet?method=selectByPrimaryKey';
		}
		function updateCustomer(id) {
			var customerId = id.split("_xg")[0];
			location.href = 'CustomerServlet?method=selectByPrimaryKey';
		}
		function changeCondition() {
			var selectTest = $("#field").find("option:selected").text();
			if (selectTest == "客户名称") {
				$("#conditionContent").show();
				$("#searchContent").hide();
				$("#selectId").empty();
				$("#hiddenId").attr("type", "text");
				$("#hiddenId").val("");
				$("#conditionContentRq").hide();
			} else if (selectTest == "创建时间" || selectTest == "修改时间") {
				$("#conditionContent").show();//conditionContentRq
				$("#conditionContentRq").show();
				$("#searchContent").hide();
				$("#selectId").empty();
				$("#hiddenId").val("");
				$("#hiddenId").attr("type", "date");
			} else if (selectTest == "所属行业") {
				$("#searchContent").show();
				$("#conditionContent").hide();
				$("#conditionContentRq").hide();
				$("#selectId").empty();
				$("#hiddenId").attr("type", "text");
				$("#selectId").append(
						"<option value='0001' selected>集团/上市公司</option>");
				$("#selectId").append("<option value='0002'>大型企业</option>");
				$("#selectId").append("<option value='0003'>设计院/研究所</option>");
				$("#selectId").append("<option value='0004'>政府/学校/社团</option>");
				$("#selectId").append("<option value='0005'>成长性企业</option>");
				$("#selectId").append("<option value='0006'>其他</option>");
			} else if (selectTest == "客户来源") {
				$("#searchContent").show();
				$("#conditionContent").hide();
				$("#conditionContentRq").hide();
				$("#selectId").empty();
				$("#hiddenId").attr("type", "text");
				$("#selectId").append("<option value='1001'>网络营销</option>");
				$("#selectId").append("<option value='1002'>公开媒体</option>");
				$("#selectId").append(
						"<option value='1003' selected>合作伙伴</option>");
				$("#selectId").append(
						"<option value='1004' selected>员工介绍</option>");
				$("#selectId").append("<option value='1005'>广告</option>");
				$("#selectId").append("<option value='1006'>推销电话</option>");
				$("#selectId").append("<option value='1007'>朋友推荐</option>");
				$("#selectId").append("<option value='1008'>同事介绍</option>");
				$("#selectId").append("<option value='1009'>其他</option>");
			}
		}
		function soo() {//查询
			var selectTest = $("#field").find("option:selected").text();
			if ($("#searchContent").css('display') == "inline-block") {
				var text = $("#selectId").find("option:selected").val();
				if (selectTest == "所属行业") {
					$("#actionFormSo").attr(
							"action",
							"CustomerServlet?method=getSomeCustomer&text="
									+ text + "&type=2 ");
					$("#actionFormSo").submit();
				} else if (selectTest == "客户来源") {
					$("#actionFormSo").attr(
							"action",
							"CustomerServlet?method=getSomeCustomer&text="
									+ text + "&type=3 ");
					$("#actionFormSo").submit();
				} else if (selectTest == "负责人") {
					$("#actionFormSo").attr(
							"action",
							"CustomerServlet?method=getSomeCustomer&text="
									+ text + "&type=4");
					$("#actionFormSo").submit();
				}
			} else if ($("#conditionContent").css('display') == "inline-block") {
				var text = $("#hiddenId").val();
				var textTime=$("#hiddenIdRq").val();
				if (text != null && text != "undefined" && text != "") {
					if (selectTest == "客户名称") {
						$("#actionFormSo").attr(
								"action",
								"CustomerServlet?method=getSomeCustomer&text="+encodeURI(encodeURI(text))+ "&type=1 ");
						$("#actionFormSo").submit();
					} else if (selectTest == "创建时间") {
						if (text>textTime) {
							alert("前面输入时间不能大于后面输入时间");
							return;
						}
						$("#actionFormSo").attr(
								"action",
								"CustomerServlet?method=getSomeCustomer&text="
										+ text + "&type=5"+"&texTime="+textTime);
						$("#actionFormSo").submit();
					} else if (selectTest == "修改时间") {
						if (text>textTime) {
							alert("前面输入时间不能大于后面输入时间");
							return;
						}
						$("#actionFormSo").attr(
								"action",
								"CustomerServlet?method=getSomeCustomer&text="
										+ text + "&type=6"+"&texTime="+textTime);
						$("#actionFormSo").submit();
					}

				}

			}
		}
	</script>
</body>

</html>