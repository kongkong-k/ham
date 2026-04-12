<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp" %>
<html>
<head>
    <title>音乐是生活的调味剂</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <!-- Bootstrap -->
    <link href="../../css/bootstrap.css" rel="stylesheet" media="screen"/>
    <link href="../../css/thin-admin.css" rel="stylesheet" media="screen"/>
    <link href="../../css/font-awesome.css" rel="stylesheet" media="screen"/>
    <link href="../../style/style.css" rel="stylesheet"/>
    <link href="../../style/dashboard.css" rel="stylesheet"/>

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
          <script src="../../assets/js/html5shiv.js"></script>
          <script src="../../assets/js/respond.min.js"></script>-->
    <!--[endif]-->
    <script src="../../js/jquery.js"></script>
    <script src="../../js/bootstrap.min.js"></script>
    <script>
        $(function () {
            $("#toggleSearch").click(function () {
                var flag = $(this).attr("flag");
                if (flag == 1) {
                    $("#find").show(500);
                    $(this).attr("flag", 2);
                    $(this).html("收缩↑");
                } else {
                    $("#find").hide(500)
                    $(this).attr("flag", 1);
                    $(this).html("展开↓");
                }
            });

            // 搜索按钮：重置页码为1（pageNo → pageNum）
            $("#search").click(function () {
                $("#pageNum").val(1); // 关键修改：pageNo → pageNum
                $("#txForm").submit();
            })

            /**
             * 分页逻辑：所有 pageNo 替换为 pageNum
             */
            var pageNum = $("#pageNum").val(); // 关键修改：pageNo → pageNum
            var totalPage = $("#totalPage").val();

            pageNum = parseInt(pageNum);
            totalPage = parseInt(totalPage);

            // 禁用状态判断（变量已改为 pageNum，逻辑不变）
            if (pageNum == 1 && pageNum == totalPage) {
                $("#prev").addClass("disabled");
                $("#next").addClass("disabled");
            }
            if (pageNum == 1 && pageNum < totalPage) {
                $("#prev").addClass("disabled");
                $("#next").removeClass("disabled");
            }
            if (pageNum > 1 && pageNum < totalPage) {
                $("#prev").removeClass("disabled");
                $("#next").removeClass("disabled");
            }
            if (pageNum > 1 && pageNum == totalPage) {
                $("#prev").removeClass("disabled");
                $("#next").addClass("disabled");
            }

            // 上一页（pageNo → pageNum）
            $("#prev").click(function () {
                if ($(this).hasClass("disabled")) return; // 新增：阻止禁用状态点击
                $("#pageNum").val(--pageNum); // 关键修改：pageNo → pageNum
                $("#txForm").submit();
            })

            // 下一页（pageNo → pageNum）
            $("#next").click(function () {
                if ($(this).hasClass("disabled")) return; // 新增：阻止禁用状态点击
                $("#pageNum").val(++pageNum); // 关键修改：pageNo → pageNum
                $("#txForm").submit();
            })

            // 页码按钮
            $("a[pageNumButton]").click(function () {
                var pageNum = $(this).html();
                $("#pageNum").val(pageNum);
                $("#txForm").submit();
            })

            // 以下是添加/修改/删除逻辑（无 pageNo 相关，无需修改）
            var pop;
            $("#addSong").click(function () {
                pop = layer.open({
                    type: 1,
                    area: [600, 350],
                    content: $('#mtypePop')
                });
            })

            layui.use('form', function () {
                var form = layui.form;
                form.on('submit(demo1)', function (data) {
                    $.ajax({
                        url: "/mtype/addMtype",
                        type: "post",
                        data: data.field,
                        dataType: "text",
                        success: function (text) {
                            if (text == "success") {
                                layer.msg("添加成功");
                                layer.close(pop);
                                $("#txForm").submit(); // 新增：添加成功后刷新列表
                            }
                        }
                    })
                    //阻止页面跳转 防止同步提交 使用ajax异步提交表现
                    return false;
                });
            });

            var pop1;
            $("[modify]").click(function () {
                var tid = $(this).attr("tid");
                $.ajax({
                    url: "/mtype/getMtype",
                    type: "post",
                    data: {tid: tid},
                    dataType: "json",
                    success: function (jsonObj) {
                        $("#tid").val(jsonObj.tid);
                        $("#ptname").val(jsonObj.tname);
                        $("#ptdesc").val(jsonObj.tdesc);
                    }
                })
                pop1 = layer.open({
                    type: 1,
                    area: [600, 350],
                    content: $('#mtypePop1')
                });
            })

            layui.use('form', function () {
                var form = layui.form;
                form.on('submit(demo2)', function (data) {
                    $.ajax({
                        url: "/mtype/updateMtype",
                        type: "post",
                        data: data.field,
                        dataType: "text",
                        success: function (text) {
                            if (text == "success") {
                                layer.msg("修改成功");
                                layer.close(pop1);
                                $("#txForm").submit();
                            }
                        }
                    })
                    return false;
                });
            })

            $(".btn-warning").click(function () {
                var tid = $(this).attr("tid");
                layer.confirm('是否确认删除?', {icon: 3, title: '提示'}, function (index) {
                    $.ajax({
                        url: "/mtype/delMtype",
                        type: "post",
                        data: {tid: tid},
                        dataType: "text",
                        success: function (text) {
                            if (text == "success") {
                                layer.msg("删除成功");
                                layer.close(index);
                                //将分页条件查询的页码值归1
                                $("#pageNum").val(1);
                                $("#txForm").submit();
                            }
                        }
                    })
                });
            })
        });
    </script>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
</head>
<body>
<div class="container">
    <!-- 顶部导航栏（无修改） -->
    <div class="top-navbar header b-b"><a data-original-title="Toggle navigation" class="toggle-side-nav pull-left" href="#"><i class="icon-reorder"></i> </a>
        <div class="brand pull-left"><a href="index.html"><img src="../../images/logo.png" width="147" height="33"/></a></div>
        <ul class="nav navbar-nav navbar-right  hidden-xs">
            <!-- 导航栏内容（无修改） -->
        </ul>
        <form role="search" class="navbar-form pull-right" id="search-form"/>
        <input type="search" placeholder="Search..." class="search-query" id="search-input"/>
        </form>
    </div>
</div>
<div class="wrapper">
    <jsp:include page="menu.jsp"></jsp:include>
    <div class="page-content">
        <div class="content container">
            <div class="row">
                <div class="col-lg-12">
                    <h2 class="page-title">流派列表 <small>favor song</small></h2>
                </div>
            </div>

            <!-- 主表单（无修改，仅分页部分通过 include 引入） -->
            <form id="txForm" action="/mtype/list" method="post" class="form-horizontal">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="widget">
                            <div class="widget-header"><i class="icon-list-ol"></i><h3>搜索条件</h3></div>
                            <div class="widget-content">
                                <fieldset id="find">
                                    <div class="control-group">
                                        <label for="tname" class="control-label">流派</label>
                                        <div class="controls form-group">
                                            <div class="input-group"><span class="input-group-addon"><i class="icon-music"></i></span>
                                                <input type="text" placeholder="如：摇滚" name="tname" value="${mq.tname}" id="tname" class="form-control"/>
                                            </div>
                                        </div>
                                    </div>
                                </fieldset>
                                <div class="form-actions text-right">
                                    <div>
                                        <button class="btn btn-primary" id="search">搜索</button>
                                        <button id="addSong" class="btn btn-primary" data-target="#myModal2" type="button">添加流派</button>
                                        <button id="toggleSearch" flag="2" class="btn btn-default" type="button">收缩↑</button>
                                    </div>
                                </div>
                            </div>
                            <div class="widget-content">
                                <div class="body">
                                    <table class="table table-striped table-images" style="color: white;font-size: 14px">
                                        <thead>
                                        <tr>
                                            <th class="hidden-xs-portrait">序号</th>
                                            <th class="hidden-xs">流派</th>
                                            <th class="hidden-xs">描述</th>
                                            <th></th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${page.list}" var="mtype" varStatus="status">
                                            <tr>
                                                <td class="hidden-xs-portrait">${mtype.tid}</td>
                                                <td class="hidden-xs-portrait">${mtype.tname}</td>
                                                <td class="hidden-xs"> ${mtype.tdesc} </td>
                                                <td>
                                                    <button class="btn btn-sm btn-primary" type="button" modify tid="${mtype.tid}"> 修改</button>
                                                    <button data-toggle="button" class="btn btn-sm btn-warning" tid="${mtype.tid}"> 删除</button>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>

                                    <jsp:include page="pagination.jsp"></jsp:include>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<div class="bottom-nav footer"> 青城博雅教育出品 </div>
<script>$("#songtype").addClass("current");</script>

<!-- 添加/修改弹窗（无 pageNo 相关，无需修改） -->
<div id="mtypePop" style="margin-right: 50px;margin-top: 50px; display: none">
    <form id="addMtypeForm" class="layui-form" method="post" action="/mtype/addMtype" lay-filter="example">
        <div class="layui-form-item">
            <label class="layui-form-label">流派</label>
            <div class="layui-input-block">
                <input type="text" name="tname" style="color: black;" lay-verify="title" autocomplete="off" placeholder="请输入流派名" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">描述</label>
            <div class="layui-input-block">
                <textarea style="color: black;" placeholder="请输入流派描述" class="layui-textarea" name="tdesc"></textarea>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn layui-btn-normal layui-btn-radius" lay-submit="" lay-filter="demo1">添加流派</button>
            </div>
        </div>
    </form>
</div>

<div id="mtypePop1" style="margin-right: 50px;margin-top: 50px; display: none">
    <form id="updateMtypeForm" class="layui-form" method="post" action="/mtype/updateMtype" lay-filter="example">
        <input type="hidden" name="tid" id="tid">
        <div class="layui-form-item">
            <label class="layui-form-label">输入框</label>
            <div class="layui-input-block">
                <input id="ptname" type="text" name="tname" style="color: black;" lay-verify="title" autocomplete="off" placeholder="请输入流派名" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">文本域</label>
            <div class="layui-input-block">
                <textarea id="ptdesc" style="color: black;" placeholder="请输入流派描述" class="layui-textarea" name="tdesc"></textarea>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn layui-btn-normal layui-btn-radius" lay-submit="" lay-filter="demo2">修改流派</button>
            </div>
        </div>
    </form>
</div>

</body>
</html>