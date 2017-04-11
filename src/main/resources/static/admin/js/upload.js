/**
 * Created by jm on 2017/3/15.
 */
/*<![CDATA[*/
var editor = new wangEditor('textarea1');
// 上传图片
editor.config.uploadImgUrl = '/fundnet/uploadImg';
editor.config.uploadImgFileName = 'myFileName';
// 配置 onchange 事件
editor.onchange = function () {
    // 编辑区域内容变化时，实时打印出当前内容
    var len = this.$txt.text().length;
    var pattern = '已输入 ' + len + ' 个字 (200~1万字)';
    if(len>=200){
        $('.word_count').html(pattern).css("color","black");
    }else{
        $('.word_count').html(pattern).css("color","red");
    }
};
editor.create();
$('#textarea1').attr('style','height:auto;');

$("#chklen").click(function(){
    var content = editor.$txt.text();
    var pattern = '已输入 ' + content.length + ' 个字 (200~1万字)';
    $('.word_count').html(pattern).css("color","red");
    $("#content").val($("#textarea1").html());
    if(content.length<200)
    {
        alert("设计说明不能少于200字");
        return;
    }
    else{
        $('.word_count').html(pattern).css("color","black");
    }
});
/*]]>*/


$(document).ready(function(){
    // 验证
    $("#form").validate({
        rules: {
            stylistName: {
                required: true,
                maxlength:10
            },
            mobile: {
                required: true,
                number:true,
                digits:true
            },
            worksName: {
                required: true,
                maxlength:50
            },
            picPath: {
                required: true
            },
            content: {
                required: true,
                minlength:200
            },
            chkName:{
                required:true
            }
        },
        messages:{
            stylistName:{
                required:"请输入设计师姓名",
                maxlength:"设计师名称不能大于10个字符"
            },
            mobile:{
                required:"请输入联系电话"
            },
            worksName:{
                required:"请输入作品名称",
                maxlength:"作品名称不能大于50个字符"
            },
            picPath:{
                required:"请上传作品"
            },
            content:{
                required:"请输入设计说明",
                minlength:"设计说明不能少于200个字符"
            },
            chkName:{
                required:"请仔细阅读并勾选相关权益"
            }
        }
    });
});