/* 导航栏 */
var _navBox = {

    defaultData:{
        url:'/sys/sysNav/findAll',
        data:{},
        thisId:'#navBox',
        maxShow:5,
    },

    init:function(data){
        $.extend(_navBox.defaultData,data);
        console.log(_navBox.defaultData);
        _navBox.getData(_navBox.loadData);
        _navBox.bindEvent();
    },

    /* 获取用户导航栏数据 */
    getData:function(callBack){
        $.get(_navBox.defaultData.url,function(data){
            if(data.code == 200) {
                _navBox.defaultData.data = data.data;
                callBack(_navBox.defaultData.data);
            }
        },'json');
    },

    /* 加载数据 */
    loadData:function(data){
        var html = '';
        var length = data.length;
        if(length > _navBox.defaultData.maxShow) length = _navBox.defaultData.maxShow;
        for(var i = 0; i < length; i++){
            html += '<li index="'+(i+1)+'" class="nav-item"><a href="javascript:void(0);" data-nav-id = "'+ data[i].id +'" >'+ data[i].navName +'</a></li>&nbsp;';
        }
        $(_navBox.defaultData.thisId).find(".nav-content").html(html);
    },

    /* 激活导航项 */
    activeNavItem:function(index){
        $(_navBox.defaultData.thisId).find(".nav-content li[index="+index+"]").addClass("active");
    },

    /* 绑定事件 */
    bindEvent:function(){
        $(_navBox.defaultData.thisId).find(".nav-content li").on('click',function(){
            _navBox.activeNavItem($(this).attr("index"));
        })
    }

}

_navBox.init();