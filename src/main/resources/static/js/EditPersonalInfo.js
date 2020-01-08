$(document).ready(function(){
    $("#change_btn").click(function(){
        var pwd=$("#psw").val();
        var phone=$("#phone").val();
        var email=$("#email").val();
        var sex=$("#sex").val();
        $.ajax({
            url:"change_personal_info",
            dataType:"json",
            type:"post",
            async:false,
            data:{
                password:pwd,
                phone:phone,
                email:email,
                sex:sex,
            },
            success:function(res){
                alert(res.num);
                alert("success")
                // if(res.num==4)
                // {
                //     alert("ok");
                //     window.location.href="http://localhost:8080/edit_personal_info";
                //     alert("修改成功");
                // }
                // else if(res.num==1)
                // {
                //     alert("邮箱格式错误");
                // }
                // else if(res.num==2){
                //     alert("请填写boy或girl作为性别");
                // }
                // else if(res.num==2){
                //     alert("电话号码格式错误");
                // }
            },
            error:function(res){
                alert(res.num)
                alert("error")
            }
        });
    });
});