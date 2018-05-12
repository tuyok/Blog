$(function(){
    _initFrameWork();

    $(window).resize(function(){
        _initFrameWork();
        console.log("bb");
    })

    document.addEventListener('DOMContentLoaded', function () {
        // _initFrameWork();
        console.log("aa");
    })

    function _initFrameWork(){
        $("#contentHtml").css({padding:"0px"});

        $("#main-container").height($(window).height() - 60);

        if($(window).width() < $(document).width()){
            $("#main-content").width($(document).width() - 220);
        }else{
            $("#main-content").width($(window).width() - 220);
        }

        $("#contentHtml").width($("#main-content").width()).css({padding:"20px"});

    }
})