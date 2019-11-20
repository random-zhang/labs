var t=0;
function selectSingle(){//选中一个
    t=0;
    var obj = document.getElementsByName("cartCheckBox");
    //var check_val = [];
    for(var k in obj) {
        if (obj[k].checked) {
           // check_val.push(obj[k].value);//value为1003,1002...
            var id="total"+obj[k].value;
            t+=parseFloat(document.getElementById(id).innerText);//获取选中物品的总价
        }
    }
    document.getElementById("total").innerText=t.toFixed(2);
}

function clickminus(no,price){
           var th= document.getElementById("num"+no);//num1001/num1002
           var num=parseInt(th.value)-1;
           th.setAttribute("value",num);
           var total=document.getElementById("total"+no);//total1001
           total.innerText=price*num;
          window.location.href="../../../DoCarServlet?action=minus&bookNo="+no;
}
function clickadd(no,price){
    var th= document.getElementById("num"+no);
    var num=parseInt(th.value)+1;
    th.setAttribute("value",num);
    var total=document.getElementById("total"+no);
    total.innerText=price*num;
    window.location.href="../../../DoCarServlet?action=add&bookNo="+no;
}
 function purchase(){
    window.location.href="../../../DoCarServlet?";
 }
function  cancelSelected(){//删除所选
    var obj = document.getElementsByName("cartCheckBox");
    var check_val = [];
    for(var k in obj) {
        if (obj[k].checked) {
            check_val.push(obj[k].value);//value为1003,1002...
        }
    }
    window.location.href="../../../DoCarServlet?action=removeselect&bookNos="+check_val;
}

function selectAll(){
    var  button=document.getElementById("allCheckBox");
    var obj = document.getElementsByName("cartCheckBox");
    for(var k=0;k<=obj.length;k++) {
        if(button.checked){
            selectSingle();
            obj[k].checked = true;
        }else{
            document.getElementById("total").innerText='0';
            obj[k].checked = false;
        }
    }
}
