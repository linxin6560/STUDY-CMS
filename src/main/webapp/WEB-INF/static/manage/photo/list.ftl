<#assign menu="album">
<#assign submenu="photo_list">
<#include "/manage/head.ftl">
<style type="text/css">
    .photo_album {
        width: 800px;
        padding: 10px;
    }

    .clear {
        zoom: 1;
    }

    .mydiv {
        margin: 10px;
        padding: 5px;
        width: 170px;
        background: #fff;
        vertical-align: middle;
        filter: progid:DXImageTransform.Microsoft.Shadow(color=#909090, direction=120, strength=3); /*ie*/
        -moz-box-shadow: 2px 2px 10px #909090; /*firefox*/
        -webkit-box-shadow: 2px 2px 10px #909090; /*safari或chrome*/
        box-shadow: 2px 2px 10px #909090; /*opera或ie9*/
    }

    .pagination {
        border-radius: 4px;
        display: inline-block;
        margin: 0;
        padding-left: 0;
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
                    <div class="col-lg-8">
                        <a class="btn btn-primary" style="float:right;"
                           href="${BASE_PATH}/manage/photo/add.htm">增加照片</a>
                    </div>
                </div>
            </header>
            <ul class="breadcrumb photo_album clear">
            <#list photoPage.list as photo>
                <li>
                    <div class="row">
                        <div class="col-md-3">
                            <a href="${BASE_PATH}/${photo.filename}" data-lightbox="gallery" data-title="Bald Eagle">
                                <img src="${BASE_PATH}/${photo.filename}" class="img-thumbnail" alt="">
                            </a>
                            <div class="title">${photo.title}</div>
                        </div>
                </li>
            </#list>
            </ul>
        </section>
    </section>
</section>
<#include "/manage/foot.ftl">
