var app = {
    /* 初始化 */
    init:function(option){
        $.extend(this.defaultData,option); //如果没有覆盖变量则使用默认变量

        this.defaultEventBind(); //绑定默认事件

        this.util.loadHtml($(this.defaultData.contentHtmlId),this.defaultData.homeHref); //初始化首页内容

        // this.testCode();
    },
    /* 默认值 */
    defaultData:{
        contentHtmlId:"#contentHtml",
        footerHtmlId:"#footerHtml",
        homeHref:"/html/index-child/home.html",
    },
    /* 封装工具对象 */
    util:{
        //切换内容区的内容
        changeContent:function(el){
            var $el = $(el); //获取jq对象
            if($el.data("html")){
                app.util.loadHtml($(app.defaultData.contentHtmlId),$el.data("html"));
            }
        },
        loadHtml:function($el,htmlHref){  //在jq的load方法上再封装一层，解决底部版权信息的展示问题
            $el.load(htmlHref,{},function(response,status){
                if(status == "success"){
                    if($("body").height() >= ($(window).height() - 100)){
                        $(app.defaultData.footerHtmlId).show();
                    }else{
                        $(app.defaultData.footerHtmlId).hide();
                    }
                }
            })
        }
    },
    /* 默认的事件绑定 */
    defaultEventBind:function(){
        //默认所有的带有a标签并且包含data-html属性的对象点击可以切换内容页面
        $("a[data-html]").click(function(){
            app.util.changeContent(this);
        })
    },
    /* 编写测试代码 */
    testCode:function(){
        console.log(" ========== 测试代码开始 ========== ");

        console.log("window 宽 高"+$(window).width()+" "+$(window).height());
        console.log("document 宽 高"+$(document).width()+" "+$(document).height());
        console.log("body 宽 高"+$("body").width()+" "+$("body").height());


        console.log(" ========== 测试代码结束 ========== ")
    }
}