/* 菜单栏 */

var _menuBox = {
    defaultData:{
        menuTitle:'菜单栏',
        menuBody:[
            {
                menuName:'用户管理',htmlHref:'#',menuLevel:1,hasChild:true,showChild:false,children:[
                    {menuName:'系统管理',htmlHref:'#',menuLevel:2,hasChild:true,showChild:false,children:[
                        {menuName:'插件管理',htmlHref:'#',menuLevel:3,hasChild:false,showChild:false,children:[]},
                        {menuName:'图片管理',htmlHref:'#',menuLevel:3,hasChild:false,showChild:false,children:[]}
                    ]},
                    {menuName:'系统管理',htmlHref:'#',menuLevel:2,hasChild:false,showChild:false,children:[]}
                ]
            },
            {
                menuName:'系统管理',htmlHref:'#',menuLevel:1,hasChild:false,showChild:false,children:[{}]
            },
            {
                menuName:'文章管理',htmlHref:'#',menuLevel:1,hasChild:false,showChild:false,children:[{}]
            },
            {
                menuName:'访客管理',htmlHref:'#',menuLevel:1,hasChild:false,showChild:false,children:[{}]
            }
        ]
    },
    init:function(){
        /*var menuData = this.defaultData.menuBody;
        var html =
            '<div class="menu-box">' +
                '<div class="menu-title"><i class="pull-left glyphicon glyphicon-menu-hamburger"></i> '+this.defaultData.menuTitle+'</div>' +
                '<div class="menu-body">' +
                    '<ul class="menu-items" data-menu-level="1" data-show-child="true">' +
                        (function(){
                            var htm = '';
                            for(var i = 0,length = menuData.length;i < length;i ++){
                                htm += '<li class="menu-item">' +
                                    menuData[i].hasChild?'<div class="menu-name"><i class="glyphicon glyphicon-triangle-right"></i> <a href="'+menuData[i].htmlHref+'">'+menuData[i].menuName+'</a></div>':'<a href="'+menuData[i].htmlHref+'">'+menuData[i].menuName+'</a></div>' +

                            }
                        })() +
                    '</ul>' +
                '</div>' +
            '</div>';*/

        // 菜单栏菜单项相对位置调整
        $(".menu-body .menu-items").each(function(){
            var menuLevel = $(this).data("menuLevel");
            $(this).find(".menu-name").css({
                paddingLeft:menuLevel * 20
            });
        });

        this.bindEvent();

    },
    bindEvent:function(){

        //菜单栏收缩
        $(".menu-item").click(function(){
            if($(this).children(".menu-items").length < 1) return false;
            var isHide = $(this).children(".menu-items").is(":hidden");
            if(isHide){
                $(this).children(".menu-items").show();
                $(this).children(".menu-name").find("i").removeClass("glyphicon-triangle-right").addClass("glyphicon-triangle-bottom");
            }else{
                $(this).children(".menu-items").hide();
                $(this).children(".menu-name").find("i").removeClass("glyphicon-triangle-bottom").addClass("glyphicon-triangle-right");
            }
            return false;
        })

    }

}

_menuBox.init();