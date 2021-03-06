<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>webuploader上传</title>
    <link rel="stylesheet" type="text/css" href="css/webuploader.css">
    <link rel="stylesheet" type="text/css" href="css/bootstrap.css">
    <script type="text/javascript" src="js/jquery-1.10.2.min.js"></script>
    <script type="text/javascript" src="js/webuploader.js"></script>

</head>
<body>
<div id="uploader" class="wu-example">
    <!--用来存放文件信息-->
    <div id="thelist" class="uploader-list"></div>
    <div class="btns">
        <div id="picker">选择文件</div>
        <button id="btn" class="btn btn-default">开始上传</button>
    </div>
</div>



<script type="text/javascript">
    var fileMd5;
    var $list=$("#thelist");
    var state = 'pending';//初始按钮状态
    var $btn=$("#btn");
    //监听分块上传过程中的三个时间点
    WebUploader.Uploader.register({
        "before-send-file" : "beforeSendFile",
        "before-send" : "beforeSend",
        "after-send-file" : "afterSendFile",
    }, {
        //时间点1：所有分块进行上传之前调用此函数
        beforeSendFile : function(file) {
            var deferred = WebUploader.Deferred();
            //1、计算文件的唯一标记，用于断点续传
            (new WebUploader.Uploader()).md5File(file, 0, 10 * 1024 * 1024)
                    .progress(function(percentage) {
                        $('#' + file.id).find("p.state").text("正在读取文件信息...");
                    }).then(function(val) {
                fileMd5 = val;
                $('#' + file.id).find("p.state").text("成功获取文件信息...");
                //获取文件信息后进入下一步
                deferred.resolve();
            });
            return deferred.promise();
        },
        //时间点2：如果有分块上传，则每个分块上传之前调用此函数
        beforeSend : function(block, file) {
            var deferred = WebUploader.Deferred();
            //添加自定义的数据，在发送分块文件的时候同时发送
            uploader.options.formData.savePath = "1/aaa";
            uploader.options.formData.chunkNum = block.chunk;
            var rec = {

                savePath: "1/aaa",

                fileName : "aaa.jpg",
                //文件唯一标记
                fileMd5 : fileMd5,
                //当前分块下标
                chunkNum : block.chunk,
                //当前分块大小
                chunkSize : block.end - block.start
            };
            console.log(JSON.stringify(rec));
            //检查分片文件是否存在
            $.ajax({
                type : "POST",
                contentType: "application/json",
                url : "http://localhost:8080/cloud/files/fileExist",
                data : JSON.stringify(rec),
                dataType : "json",
                success : function(response) {
                    if (response.data) {
                        //分块存在，跳过
                        deferred.reject();
                    } else {
                        //分块不存在或不完整，重新发送该分块内容
                        deferred.resolve();
                    }
                }
            });

            this.owner.options.formData.fileMd5 = fileMd5;
            deferred.resolve();
            return deferred.promise();
        },
        //时间点3：所有分块上传成功后调用此函数
        afterSendFile : function(file) {
            //如果分块上传成功，则通知后台合并分块
            var rec = {

                uploadId : 1,

                userId : 1,

                savePath: "1/aaa",

                fileName : "aaa.jpg",

                fileSize : file.size,

                uploadDate : "2018-01-10 12:12:12",

                uploadedSliceNum : 20,

                uploadedSize :  file.size
            };
            console.log(JSON.stringify(rec));
            $.ajax({
                type : "POST",
                contentType: "application/json",
                url : "http://localhost:8080/cloud/files/mergeFile",
                data : JSON.stringify(rec),
                success : function(response) {
                    alert("上传成功");
                }
            });
        }
    });

    var uploader = WebUploader
            .create({
                // swf文件路径
                swf : 'js/Uploader.swf',
                // 文件接收服务端。
                server : 'http://localhost:8080/cloud/files/uploadFile',
                threads: 3, //上传并发数
                // 选择文件的按钮。可选。
                // 内部根据当前运行是创建，可能是input元素，也可能是flash.
                pick : {
                    id : '#picker',//这个id是你要点击上传文件的id
                    multiple : false
                },
                // 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
                resize : true,
                auto : true,
                //开启分片上传
                chunked : true,
                chunkSize : 2 * 1024 * 1024,

                accept : {
                    extensions : "txt,jpg,jpeg,bmp,png,zip,rar,war,pdf,cebx,doc,docx,ppt,pptx,xls,xlsx",
                    mimeTypes : '.txt,.jpg,.jpeg,.bmp,.png,.zip,.rar,.war,.pdf,.cebx,.doc,.docx,.ppt,.pptx,.xls,.xlsx'
                }
            });

    // 当有文件被添加进队列的时候
    uploader.on('fileQueued', function(file) {
        $list.append(
                '<div id="' + file.id + '" class="item">'
                + '<p class="info">' + file.name + '</p>'
                + '<p class="state">等待上传...</p></div>');
    });

    // 文件上传过程中创建进度条实时显示。
    uploader.on('uploadProgress', function(file, percentage) {
        $('#' + file.id).find('p.state').text(
                '上传中 ' + Math.round(percentage * 100) + '%');
    });

    uploader.on('uploadSuccess', function(file) {
        $('#' + file.id).find('p.state').text('已上传');
    });

    uploader.on('uploadError', function(file) {
        $('#' + file.id).find('p.state').text('上传出错');
    });

    uploader.on('uploadComplete', function(file) {
        $('#' + file.id).find('.progress').fadeOut();
    });


    uploader.on('all', function(type) {
        if (type === 'startUpload') {
            state = 'uploading';
        } else if (type === 'stopUpload') {
            state = 'paused';
        } else if (type === 'uploadFinished') {
            state = 'done';
        }

        if (state === 'uploading') {
            $btn.text('暂停上传');
        } else {
            $btn.text('开始上传');
        }
    });

    $btn.on('click', function(){
        if (state === 'uploading'){
            uploader.stop(true);
            //暂停、更新数据库中的上传进度
            var rec = {

                uploadId : 1,

                userId : 1,

                savePath: "1/aaa",

                fileName : "aaa.jpg"

            };
            console.log(JSON.stringify(rec));
            $.ajax({
                type : "POST",
                contentType: "application/json",
                url : "http://localhost:8080/cloud/fileUploadRecs/stopUpload",
                data : JSON.stringify(rec),
                success : function(response) {
                    alert("暂停成功");
                }
            });
        } else {
            uploader.upload();
        }
    });
</script>
</body>
</html>