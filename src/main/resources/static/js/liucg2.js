//切换侧边栏显示隐藏
function toggleSidebar(){
  $("#body").toggleClass("big-page");//toggleClass 用于清除或者设置 css
  return false;
}
//tab页面操作
/**
 * 初始化tabs
 * @param tabsId  tabs id
 * @param menu_iter 菜单点击事件 menu下所有的菜单项（class=menu_item）
 * @param menu_name 菜单名称类名，当前点击的li，获取的菜单名，赋值给tab-header
 * @param table_init_function_name bootstrap_table初始化函数名称
 * 使用说明
 * 1 页面定义tabs div（高度100%，子集高度100%）
 * 2 规定所有的菜单项 用（class=menu_item），用于点击
 * 3 规定所有的菜单名称 用(class=menu_name)，用户获取菜单名称---设置tab_header中的标题
 * 4 规定菜单id值规范为 menu_数据库中id，新增的tab-content 的id为(tabs-menu_数据库中id)，对应tab-header的href
 */
function init_tab(tabsId,menu_iter,menu_name,table_init_function_name) {
  // 1) 初始化tabs
  var tabs=$('#'+tabsId).tabs();
  // 2) tab模板定义
  var tabTemplate = "<li><a href='#{href}'>#{label}</a> <span class='fa fa-times-circle menu_close'></span></li>";
  // 3) 菜单点击事件 menu下所有的菜单项（class=menu_item）
  $('.'+menu_iter).click(function(){
    // a、设置tab-header下a[href]的值，建立 tab—header 和 tab-content 之间的关系
     var id="#tabs-"+this.id;
     // b、激活
    // tabs初始化时就有一个li（首页）,所以要减1，添加时index会返回-1，再减1变为-2，可根据实际情况而定。
    // 这里实际上是通过Id定位#id所在li的位置，然后设置active
    var index=$("#tabs").find(id).index()-1; //获取menu li 的index
    $( "#tabs" ).tabs('option','active',index); // 将此index对应的 tab的index  进行激活
    // index没有对应的tab  则新增
    if (index==-2){
      addTab(tabs,$(this).find('.'+menu_name).text(),this.id,tabTemplate,table_init_function_name);
    }
    // 4)初始化关闭
    removeTab(tabsId,tabs);
  });
}
/**
 * 新增tab页面
 * @param tabs =$(#tabs").tabs();
 * @param tabTitle 页面标题。同菜单的名称
 * @param id 菜单id
 * @param tabTemplate tab-header 模板
 * @param table_init_function_name  bootstrap_table 初始化表格
 */
function addTab(tabs,tabTitle,id,tabTemplate,table_init_function_name) {
  // 1)初始化tab_header的标题和id
  var label = tabTitle,
      id = "tabs-" + id, //tab-content 的id，tab-header 的href
      li = $( tabTemplate.replace( /#\{href\}/g, "#" + id ).replace( /#\{label\}/g, label ) );//替换模板中的id和标题
  // 2)初始化tab_content的内容和id（此处根据需要，选填）
  var  tabContentHtml = $("."+id).html();//隐藏域中类，由此可见 这里的 .id  要与菜单的id相同
  // 3)判断tabs中是否存在新建的[id='XXX'] tab页面
  var existing=tabs.find("[id='"+id+"']");
  if(existing.length==0){
    //新增tab-header
    tabs.find( ".ui-tabs-nav" ).append( li );// ui-tabs-nav 系统自带的为tab ul赋予的类
    //新增 tab-content
    tabs.append( "<div id='" + id + "' style='height:100%'>" + tabContentHtml + "</div>" );// tabContentHtml隐藏定义的content内容，没有什么实质作用。当然异步另说
    tabs.tabs( "refresh" );// 刷新
    var table_init_function=table_init_function_name+'()';
    //eval(table_init_function);//动态加载bootstrap-table
  }
  // 4)存在就激活tab
  var index=tabs.find('.ui-tabs-nav li').index(existing);//返回所有li数量
  //添加时总是返回-1
  tabs.tabs('option','active',index);//激活状态

}

/**
 *
 * @param tabUrl
 */
function removeTab(tabsId,tabs){
  $('#'+tabsId).on('click','.menu_close',function () {
    var panelId = $( this ).closest( "li" ).remove().attr( "aria-controls" );
    $( "#" + panelId ).remove();
    tabs.tabs( "refresh" );
  })
}
/********************table表格处理**************************/
//文本框中格式化
function inputFormatter(value, row, index){
	 return ["<input type='text' id='"+row.id+"' name='"+row.id+"' value='"+value+"' data='"+value+"'>"];
} 

  //操作栏的格式化
    function actionFormatter(value, row, index) {
        var id = value;
        var result = "";
        result += "<a href='javascript:;' class='btn btn-xs green' οnclick=\"EditViewById('" + id + "', view='view')\" title='查看'><span class='glyphicon glyphicon-search'></span></a>";
        result += "<a href='javascript:;' class='btn btn-xs blue' οnclick=\"EditViewById('" + id + "')\" title='编辑'><span class='glyphicon glyphicon-pencil'></span></a>";
        result += "<a href='javascript:;' class='btn btn-xs red' οnclick=\"DeleteByIds('" + id + "')\" title='删除'><span class='glyphicon glyphicon-remove'></span></a>";

        return result;
    }

    //连接字段格式化
    function linkFormatter(value, row, index) {
        return "<a href='" + value + "' title='单击打开连接' target='_blank'>" + value + "</a>";
    }

    //Email字段格式化
    function emailFormatter(value, row, index) {
        return "<a href='mailto:" + value + "' title='单击打开连接'>" + value + "</a>";
    }

    //性别字段格式化
    function sexFormatter(value) {
        if (value == "女") {
            color = 'Red';
        } else if (value == "男") {
            color = 'Green';
        } else {
            color = 'Yellow';
        }

        return '<div  style="color: ' + color + '">' + value + '</div>';
    }