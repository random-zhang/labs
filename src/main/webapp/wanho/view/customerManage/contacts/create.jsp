<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
                <i class="icon-reorder"></i> 添加联系人 <i class="icon-reply pull-right" onclick="history.go(-1);"></i>
            </div>
            <div class="widget-content padded clearfix">
                <form id="actionform" class="form-horizontal" action="view/customerManage/contacts/index.jsp"
                      method="get">
                    <input type="hidden" name="_csrf" value="d2JWMWFEWmEVFQBaWD0ZGSYMZmEsfDUqKAgnVA0UMzkANhh5EwIgVg==">

             <!--        <div class="form-group field-contacts-selectcustomer required">
                        <label class="control-label col-sm-2" for="contacts-selectcustomer">所属客户</label>

                        <div class="col-sm-8">
                            <input type="text" id="contacts-selectcustomer"
                                   class="selectCustomer form-control weiBoxs-dialog" name="Contacts[selectCustomer]"
                                   dialog-size="large" placeholder="点击选择客户" dialog-title="所属客户"
                                   dialog-url="/customer/dialog-list.html" onclick="selectCustomer()"><input type="hidden" id="customerId" />
                       <input type="text" id="business-selectcustomer"
										class="selectCustomer form-control weiBoxs-dialog"
										name="Business[selectCustomer]" placeholder="点击选择客户"
										dialog-callback="actionCallBacks" dialog-title="选择客户"
										dialog-url="/customer/dialog-list.html"
										onclick="selectCustomer()"> <input type="hidden" id="customerId" />
                        </div>
                        <div class="help-block help-block-error"></div>
                    </div> -->
                    <input type="hidden" id="customer_id" name="Contacts[customer_id]" value="1">

                    <div class="form-group field-contacts-name required">
                        <label class="control-label col-sm-2" for="contacts-name">姓名</label>

                        <div class="col-sm-8">
                            <input type="text" id="contacts-name2" class="form-control" name="Contacts[name]" 
                                   maxlength="20" placeholder="姓名">
                        </div>
                        <div class="help-block help-block-error"></div>
                    </div>
                    <div class="form-group field-contacts-sex required">
                        <label class="control-label col-sm-2" for="contacts-sex">性别</label>

                        <div class="col-sm-8">
                            <input type="hidden" name="Contacts[sex]" value="">

                            <div id="contacts-sex">
                                <label class="label-radio inline">
                                    <input type="radio" name="Contacts[sex]" value="1"> <span
                                        class="custom-radio" ></span>男</label>
                                <label class="label-radio inline">
                                    <input type="radio" name="Contacts[sex]" value="2" checked> <span
                                        class="custom-radio"></span>女</label>
                            </div>
                        </div>
                        <div class="help-block help-block-error"></div>
                    </div>
                    <div class="form-group field-contacts-honorific required">
                        <label class="control-label col-sm-2" for="contacts-honorific">尊称</label>

                        <div class="col-sm-8">
                            <input type="text" id="contacts-honorific" class="form-control" name="Contacts[honorific]"
                                   maxlength="20" placeholder="尊称">
                        </div>
                        <div class="help-block help-block-error"></div>
                    </div>
                    <div class="form-group field-contacts-post">
                        <label class="control-label col-sm-2" for="contacts-post">职位</label>

                        <div class="col-sm-8">
                            <input type="text" id="contacts-post" class="form-control" name="Contacts[post]" 
                                   maxlength="20" placeholder="职位">
                        </div>
                        <div class="help-block help-block-error"></div>
                    </div>
                    <div class="form-group field-contacts-mobile required">
                        <label class="control-label col-sm-2" for="contacts-mobile">手机</label>

                        <div class="col-sm-8">
                            <input type="text" id="contacts-mobile" class="form-control" name="Contacts[mobile]"
                                    placeholder="手机">
                        </div>
                        <div class="help-block help-block-error"></div>
                    </div>
                    <div class="form-group field-contacts-email required">
                        <label class="control-label col-sm-2" for="contacts-email">电子邮箱</label>

                        <div class="col-sm-8">
                            <input type="text" id="contacts-email" class="form-control" name="Contacts[email]"
                                    placeholder="电子邮箱">
                        </div>
                        <div class="help-block help-block-error"></div>
                    </div>
                    <div class="form-group field-contacts-qq">
                        <label class="control-label col-sm-2" for="contacts-qq">QQ</label>

                        <div class="col-sm-8">
                            <input type="text" id="contacts-qq" class="form-control" name="Contacts[qq]"
                                   placeholder="QQ">
                        </div>
                        <div class="help-block help-block-error"></div>
                    </div>
                    <div class="form-group field-contacts-remarks">
                        <label class="control-label col-sm-2" for="contacts-remarks">备注</label>

                        <div class="col-sm-8">
                            <textarea id="contacts-remarks" class="form-control" name="Contacts[remarks]"
                                      maxlength="255" placeholder="备注"></textarea>
                        </div>
                        <div class="help-block help-block-error"></div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label"></label>

                        <div class="col-lg-10">
                            <button type="button" onclick="addContacts()" class="btn btn-primary">添加</button>
                            <button type="button" class="btn btn-default" onClick="history.go(-1);">返回</button>
                            <input type="hidden" name="reback"></div>
                        <!-- /.col -->
                    </div>
                </form>
            </div>
        </div>
   
<!-- 	模态框 -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<input type="hidden" id="referencepositionId" name="referencepositionId">
					<h4 class="modal-title" id="myModalLabel">选择关联菜单</h4>
				</div>
				<div class="modal-body">
					<table id="example" class="table">
						<thead>
							<tr>
									<th>选择</th>
									<th>客户名称</th>
									<th>所属行业</th>
									<th>客户来源</th>
								</tr>
						</thead>
							<tbody>
									<tr>
										<td>
											<input type="radio"  name ="radioNameCustomer" style=" opacity: 1;margin-top: -6px " id="1"/>
											
										</td>
										<td id="1_gk">噶事
											
										</td>
										<td>安管</td>
										<td>峰会</td>
									</tr>
							</tbody>
					</table>
				</div>
			<div class="modal-footer">
					<button type="button" id="checkPass" class="btn btn-primary"
						onclick="postCheckInfoCustomer()">确认</button>
					<button type="button" id="checkRefuse" class="btn btn-primary"
						onclick="postCheckDocInfoCustomer()">取消</button>
				</div>
			</div>
			
		</div>
	</div>
</body>
<script type="text/javascript">
/* function selectCustomer(){
		$("#myModal").modal("show");
	}
	function postCheckInfoCustomer(){
		var list=document.getElementsByName("radioNameCustomer");
		for ( var i = 0; i < list.length; i++) {
			if (list[i].checked==true) {
				var id=list[i].id;
				var textName=document.getElementById(id+"_gk").innerText;
				$("#business-selectcustomer").val("");
				$("#customerId").val("");
				$("#business-selectcustomer").val(textName);
				$("#customerId").val(id);
			}
		}
		$("#myModal").modal("hide");
	}
	
	function postCheckDocInfoCustomer(){
		$("#myModal").modal("hide");
	} */
function addContacts(){
	
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
