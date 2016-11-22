<#assign menu="album">
<#assign submenu="add_album">
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
                        <form id="add_album_form" class="form-horizontal" autocomplete="off" method="post"
                              action="${BASE_PATH}/manage/album/add.json"
                              enctype="multipart/form-data">
                            <div class="form-group">
                                <label class="col-sm-2 col-sm-2 control-label">选择封面</label>
                                <div class="col-sm-10">
                                    <input type="file" name="file" id="file">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 col-sm-2 control-label">相册标题</label>
                                <div class="col-sm-10">
                                    <input type="text" style="font-size:15px;width: 300px;" class="form-control"
                                           name="title"
                                           placeholder="相册标题" id="title">
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
        $('#add_album_form').ajaxForm({
            dataType: 'json',
            success: function (data) {
                if (data.result) {
                    bootbox.alert("保存成功，将刷新页面", function () {
                        window.location.reload();
                    });
                } else {
                    showErrors($('#add_album_form'), data.errors);
                }
            }
        });
    });
</script>
<#include "/manage/foot.ftl">