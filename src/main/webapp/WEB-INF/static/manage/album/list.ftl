<#assign menu="album">
<#assign submenu="album_list">
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
                        <p>相册列表</p>
                    </div>
                    <div class="col-lg-8">
                        <a class="btn btn-primary" style="float:right;"
                           href="${BASE_PATH}/manage/album/add.htm">增加相册</a>
                    </div>
                </div>
            </header>
            <ul class="breadcrumb photo_album clear">
            <#list albumPage.list as album>
                <li>
                    <div class="mydiv">
                        <img src="${BASE_PATH}/${album.cover}" style="height:155px;width: 160px">
                        <span style="font-size: 18px;">${album.title}</span>
                    </div>
                </li>
            </#list>
            </ul>
        </section>
    </section>
</section>
<#include "/manage/foot.ftl">
