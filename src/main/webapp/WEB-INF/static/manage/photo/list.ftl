<#assign menu="album">
<#assign submenu="photo_list">
<#include "/manage/head.ftl">
<style type="text/css">
    .photo_album {
        padding: 20px;
    }

    .title {
        text-align: center;
        font-size: 14px;
        margin-bottom: 20px;
        font-weight: bold;
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
                          action="${BASE_PATH}/manage/photo/add.json?album_id=${album_id}"
                          class="dropzone"></form>
                </div>
            </header>
            <div class="row photo_album">
            <#list photoPage.list as photo>
                <div class="col-md-3">
                    <a href="${BASE_PATH}/${photo.filename}" data-lightbox="gallery" data-title="${photo.title}">
                        <img src="${BASE_PATH}/${photo.filename}" style="height:160px;width: 220px"
                             class="img-thumbnail" alt="">
                    </a>
                    <div class="title">${photo.title}</div>
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
<#include "/manage/foot.ftl">
