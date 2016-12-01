function singleImage(id, imageShow, imageHidden) {
    if (typeof(imageShow) == "undefined")
        imageShow = "#imageShow";
    if (typeof(imageHidden) == "undefined")
        imageHidden = "#imageHidden";
    $(id).uploadify({
        'height': 27,
        'width': 80,
        'buttonText': '选择图片',
        'fileObjName': 'file',
        'swf': '/static/manage/js/uploadify/uploadify.swf',
        'uploader': '/common/upload',
        'auto': true,
        'multi': false,
        'fileTypeExts': '*.jpg;*.jpge;*.gif;*.png',
        'fileSizeLimit': 1024 * 10,
        'overrideEvents': ['onSelectError', 'onUploadSuccess'],
        'onSelectError': function (file, errorCode, errorMsg) {
            switch (errorCode) {
                case SWFUpload.QUEUE_ERROR.FILE_EXCEEDS_SIZE_LIMIT:
                    this.queueData.errorMsg = "图片大小不能超过10M";
                    break;
            }
            return false;
        },
        'onUploadSuccess': function (file, data, response) {
            var data = eval('(' + data + ')');
            var filename = data.fileName;
            var src = "/" + filename;
            $(imageHidden).val(filename);
            $(imageShow).attr("src", src);
            $(imageShow).show();
            $(imageShow).removeClass("hide");
        }
    });
}
function multipleImage(id, folder, name, control, queueID) {

    if (typeof(control) == "undefined")
        control = "#picControl";
    if (typeof(queueID) == "undefined")
        queueID = "queueDiv";
    $(id).uploadify({
        'uploader': '/common/upload',
        'formData': {'folder': folder},
        'queueID': queueID,
        'fileSizeLimit': 1024,
        'overrideEvents': ['onSelectError'],
        'onSelectError': function (file, errorCode, errorMsg) {
            switch (errorCode) {
                case -110:
                    this.queueData.errorMsg = "图片大小不能超过1M";
                    break;
            }
            return false;
        },
        'onUploadSuccess': function (file, data, response) {
            var data = eval('(' + data + ')')
            $(name).val($(name).val() + "," + data.nname);
            $(control).append("<img " + (typeof(data.size) == "undefined" ? "" : "size=\"" + data.size + "\"") + " data=\"" + data.nname + "\" src=\"" + data.src + "\" class=\"fl wh200-150 mr10 mt10\" /><i class=\"fl remove mtr2-18\"></i>");
            $(name).siblings(".error").remove();
        },
        'onUploadComplete': function () {
            parent.document.getElementById("iframepage").height = $(".content").height();
        }
    });
    $(document).on("click", control + " i", function () {
        removeImg(this, name);
    });
    new Sortable(picControl, {
        group: "photo",
        draggable: "img",
        onStart: function (evt) {
            $(evt.item).siblings("i").remove();
        },
        onEnd: function (evt) {
            $(control + " img").after("<i class=\"fl remove mtr2-18\"></i>");
            $(name).val("");
            $(control + " img").each(function (index) {
                $(name).val($(name).val() + (index == 0 ? "" : ",") + $(this).attr("data"));
            });
        }
    });
}
function removeImg(target, input) {
    $(target).prev("img").remove();
    $(input).val("");
    $(target).siblings("img").each(function (i) {
        $(input).val((i == 0 ? "" : $(input).val() + ",") + $(this).attr("data"));
    });
    $(target).remove();
}