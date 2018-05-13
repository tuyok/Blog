_initFrameWork();

$(window).on('resize',function(){
    _initFrameWork();
})

function _initFrameWork(){

    var w_height = $(window).height();
    var _height = $("#login-box").height();

    $("#login-box").css({
        marginTop:(w_height - _height) / 2,
    })

}

$(function(){
    $("#loginBtn").click(function () {
        $.ajax({
            url:'/login',
            type:'post',
            dataType:'json',
            data:{
                username:$("#username").val(),
                password:$("#password").val()
            },
            success:function(data){
                if(data.code == 200){
                    location.href = "/admin.html";
                }else{
                    layer.msg(data.message,{offset: '250px'});
                }
            }
        })
    })
})