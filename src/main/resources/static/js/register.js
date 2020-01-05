$(document).ready(function(){
    $("#register_btn").click(function(){
        alert("oasjd")
        var username=$("#username").val();
        var pwd=$("#psw").val();
        var pwd_check=$("#psw_check").val();
        var phone=$("#phone").val();
        var email=$("#email").val();
        var sex=$("#sex").val();
        alert(phone);
        alert(email);
        alert(sex);
        $.ajax({
            url:"register_info",
            dataType:"json",
            type:"post",
            async:false,
            data:{
                name:username,
                password:pwd,
                password_check:pwd_check,
                phone:phone,
                email:email,
                sex:sex,
            },
            success:function(res){
                if(res.num==1)
                {
                    alert("注册成功");
                }
                else if(res.num==2)
                {
                    alert("用户名已被注册");
                }
                else if(res.num==3){
                    alert("两次输入的密码不同");
                }
            },
            error:function(){
                alert('error');
            }
        });
    });
});