$(function(){

    //退出登录
    $("#logoutOption").on('click',function(){
        layer.confirm('确定要退出吗?', {icon: 3, title:'提示'}, function(index){
            layer.close(index);
            location.href = '/logout';
        });

    })

})