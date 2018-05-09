/* 下拉菜单 */
var _selectBox = {
    defaultData:{

    },
    init:function(){
        this.bindEvent();
    },
    bindEvent:function(){

        /* 点击触发下拉的文本 */
        $(".select-text").click(function(){
            $(this).find("i").removeClass("glyphicon-triangle-bottom").addClass("glyphicon-triangle-top");
            $(this).siblings(".select-content").show();
            return false; //禁止冒泡
        });

        /* 点击页面其他地方隐藏 */
        $(document).click(function(){
            $(".select-text").find("i").removeClass("glyphicon-triangle-top").addClass("glyphicon-triangle-bottom");
            $(".select-content").hide(); //下拉的菜单点击其他的地方隐藏
        });

    }
}

_selectBox.init();