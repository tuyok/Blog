/* 标签栏 */

var _tagBox = {

    defaultData:{
        contentHtmlId:'#contentHtml',
        index:0,
        thisId:"#tag-box",
    },

    init:function(){
        this.createTagBox('首页','/_blank.html');
    },

    /* 根据标签名称和标签地址创建一个标签，如果标签名称已存在则打开该标签 */
    createTagBox:function(title,htmlHref){ //创建新标签
        var isOpenNewTag = true;
        $(_tagBox.defaultData.thisId).find(".tag-item").each(function(){
            if($(this).data("title") == title){
                _tagBox.openTagBox($(this).attr("id"));
                isOpenNewTag = false;
            }
        });
        if(!isOpenNewTag) return;

        var id = "_tagBox"+_tagBox.defaultData.index;
        var html = '<li id="'+id+'" data-title="'+title+'" data-html-href="'+htmlHref+'" class="tag-item active">'+title
            +'<i class="glyphicon glyphicon-remove-circle"></i></li>';
        $(".tag-item").removeClass("active");
        $(_tagBox.defaultData.thisId).append(html);
        $(_tagBox.defaultData.contentHtmlId).load(htmlHref);
        $("#"+id).on('click',function(){
            $(".tag-item").removeClass("active");
            $(this).addClass("active");
            if(!htmlHref) htmlHref="/_blank.html";
            $(_tagBox.defaultData.contentHtmlId).load(htmlHref);
        });
        $("#"+id).on('click','i',function(){
            _tagBox.delTagBox(id);
            return false;
        })
        _tagBox.defaultData.index ++;
    },

    /* 根据id打开某个标签 */
    openTagBox:function(id){
        $(".tag-item").removeClass("active");
        $("#"+id).addClass("active");
        $(_tagBox.defaultData.contentHtmlId).load($("#"+id).data("htmlHref"));
    },

    /* 删除标签,并打开前一个标签，主页标签禁止删除 */
    delTagBox:function(id){ //
        if(id == "_tagBox0") return;
        var prevId = $("#"+id).prev().attr("id");
        $("#"+id).remove();
        _tagBox.openTagBox(prevId);
    }

}

_tagBox.init();

/* 使用方法 */

/*

<div class="menu-name" onclick="_tagBox.createTagBox('文件管理','/html/index-child/home.html');"><i class="glyphicon glyphicon-triangle-right"></i> 文件管理</div>

*/