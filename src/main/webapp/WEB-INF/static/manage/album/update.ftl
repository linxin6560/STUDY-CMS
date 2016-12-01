<#assign menu="album">
<#assign submenu="update_album">
<#include "/manage/head.ftl">
<section id="main-content">
    <section class="wrapper">
        <div class="row">
            <div class="col-lg-12">
                <section class="panel">
                    <header class="panel-heading">
                        添加相册
                    </header>
                    <div class="panel-body">
                        <form id="update_album_form" class="form-horizontal"
                              action="${BASE_PATH}/manage/album/update.json" method="post">
                            <input type="hidden" name="albumId" value="${album.id}">
                            <div class="form-group">
                                <label class="col-sm-2 col-sm-2 control-label">选择封面</label>
                                <div class="col-sm-10">
                                    <img id="imageShow" src="${BASE_PATH}/${album.cover}" alt="img04"
                                         style="height:120px;">
                                    <input id="file" type="file" name="file">
                                    <input id="imageHidden" type="hidden" name="pic"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 col-sm-2 control-label">相册标题</label>
                                <div class="col-sm-10">
                                    <input type="text" style="font-size:15px;width: 300px;" class="form-control"
                                           name="title" value="${album.title}" placeholder="相册标题" id="title">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-lg-offset-2 col-lg-10">
                                    <button class="btn btn-shadow btn-primary" type="submit">发布</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </section>
            </div>
        </div>
    </section>
</section>
<script type="text/javascript">
    $(function () {
        singleImage("#file", "#imageShow", "#imageHidden");
    });
    $(document).ready(function () {
        $("form").validate({
            rules: {
                title: {required: true}
            },
            messages: {
                title: {required: "相册名称不能为空"}
            },
            submitHandler: function () {
                ajaxSubmit($("form"), function (json) {
                    if (json.result) {
                        bootbox.alert("保存成功，将刷新页面", function () {
                            window.location.reload();
                        });
                    } else {
                        showErrors($("form"), data.errors);
                    }
                });
                return false;
            }
        });
    });
</script>
<#include "/manage/foot.ftl">