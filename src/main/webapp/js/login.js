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