<%@page language="java" contentType="text/html;charset=utf-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title></title>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
</head>
<body>
    <div id="resp" ></div>
    <input type="button" onclick="req()" value="请求"/>
<script src="assets/js/jquery-3.2.1.js" type="text/javascript"></script>
<script>
    function req() {
        $.ajax({
            url:"convert",
            data:"1-wangyunfei",        //1
            type:"POST",
            contentType:"application/x-wisely",     //2
            success:function (data) {
                $("resp").html(data);
            }
        });
    }
</script>
</body>
</html>
