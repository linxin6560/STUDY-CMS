<#assign menu="album">
<#assign submenu="photo_list">
<#include "/manage/head.ftl">
<style type="text/css">
    .pagination {
        border-radius: 4px;
        display: inline-block;
        margin: 0;
        padding-left: 0;
    }

    .photo_album {
        padding-left: 20px;
        padding-right: 20px;
        padding-bottom: 20px;
    }

    .title {
        text-align: center;
        font-size: 14px;
        font-weight: bold;
        text-overflow: ellipsis;
        overflow: hidden;
    }

    .mydiv {
        text-align: center;
        margin: 10px;
        width: 220px;
        padding: 10px;
        background: #fff;
        vertical-align: middle;
        filter: progid:DXImageTransform.Microsoft.Shadow(color=#909090, direction=120, strength=3); /*ie*/
        -moz-box-shadow: 2px 2px 10px #909090; /*firefox*/
        -webkit-box-shadow: 2px 2px 10px #909090; /*safari或chrome*/
        box-shadow: 2px 2px 10px #909090; /*opera或ie9*/
    }
</style>
<section id="main-content">
    <section class="wrapper">
        <!-- page start-->
        <section class="panel">
            <header class="panel-heading" style="font-size: 18px;">
                <div class="row">
                    <div class="col-lg-4">
                        <p>照片列表</p>
                    </div>
                </div>
                <div class="panel-body">
                    <form id="add_photo_form"
                          action="${BASE_PATH}/manage/photo/add.json?albumId=${albumId}"
                          class="dropzone"></form>
                </div>
            </header>
            <div style="height: 30px;margin: 20px;">
                <div class="pagination">${photoPage.pageNumHtml}</div>
            </div>
            <div class="row photo_album">
            <#list photoPage.list as photo>
                <div class="col-md-3 mydiv">
                    <a href="${BASE_PATH}/${photo.filename}" data-lightbox="gallery" data-title="${photo.title}">
                        <img src="${BASE_PATH}/${photo.filename}" style="height:140px;width: 200px" alt="">
                    </a>
                    <div class="title">${photo.title}</div>
                    <a role="menuitem" tabindex="-1" href="#">编辑</a>
                    |
                    <a href="javascript:void(0);" class="js_photo_delete" photoId="${photo.id}" title="是否删除照片">删除</a>
                </div>
            </#list>
            </div>
        </section>
    </section>
</section>
<script type="text/javascript">
    $(function () {
        $('#add_photo_form').ajaxForm({
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
<script>
    $(function () {
        $('.js_photo_delete').click(function () {
            var photoId = $(this).attr('photoId');
            bootbox.dialog({
                message: $(this).attr('title'),
                title: "提示",
                buttons: {
                    "delete": {
                        label: "确定",
                        className: "btn-success",
                        callback: function () {
                            $.post("${BASE_PATH}/manage/photo/delete.json", {"photoId": photoId}, function (data) {
                                window.location.reload();
                            }, "json");
                        }
                    },
                    "cancel": {
                        label: "取消",
                        className: "btn-primary",
                        callback: function () {

                        }
                    }
                }
            });
        });
    });
</script>
<#include "/manage/foot.ftl">
