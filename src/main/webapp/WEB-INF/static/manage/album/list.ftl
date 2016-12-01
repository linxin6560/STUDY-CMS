<#assign menu="album">
<#assign submenu="album_list">
<#include "/manage/head.ftl">
<style type="text/css">
    .photo_album {
        padding-left: 20px;
        padding-right: 20px;
        padding-bottom: 20px;
    }

    .mydiv {
        text-align: center;
        margin: 10px;
        padding: 5px;
        background: #fff;
        vertical-align: middle;
        filter: progid:DXImageTransform.Microsoft.Shadow(color=#909090, direction=120, strength=3); /*ie*/
        -moz-box-shadow: 2px 2px 10px #909090; /*firefox*/
        -webkit-box-shadow: 2px 2px 10px #909090; /*safari或chrome*/
        box-shadow: 2px 2px 10px #909090; /*opera或ie9*/
    }

    .title {
        text-align: center;
        font-size: 14px;
        font-weight: bold;
        text-overflow: ellipsis;
        overflow: hidden;
    }
</style>
<section id="main-content">
    <section class="wrapper">
        <!-- page start-->
        <section class="panel">
            <header class="panel-heading" style="font-size: 18px;">
                <div class="row">
                    <div class="col-lg-4">
                        <p>相册列表</p>
                    </div>
                    <div class="col-lg-8">
                        <a class="btn btn-primary" style="float:right;"
                           href="${BASE_PATH}/manage/album/add.htm">增加相册</a>
                    </div>
                </div>
            </header>
            <div style="height: 30px; margin: 20px;">
                <div class="pagination">${albumPage.pageNumHtml}</div>
            </div>
            <div class="row photo_album">
            <#list albumPage.list as album>
                <div class="col-md-3 mydiv">
                    <a href="${BASE_PATH}/manage/photo/list.htm?albumId=${album.id}">
                        <img src="${BASE_PATH}/${album.cover}" style="height:160px;width: 220px">
                    </a>
                    <div class="title">${album.title}</div>
                    <a href="${BASE_PATH}/manage/album/update.htm?albumId=${album.id}">编辑</a>
                    |
                    <a href="javascript:void(0);" class="js_album_delete" albumId="${album.id}" title="是否删除相册">删除</a>
                </div>
            </#list>
            </div>
        </section>
    </section>
</section>
<#include "/manage/foot.ftl">
<script>
    $(function () {
        $('.js_album_delete').click(function () {
            var albumId = $(this).attr('albumId');
            bootbox.dialog({
                message: $(this).attr('title'),
                title: "提示",
                buttons: {
                    "delete": {
                        label: "确定",
                        className: "btn-success",
                        callback: function () {
                            $.post("${BASE_PATH}/manage/album/delete.json", {"albumId": albumId}, function (data) {
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