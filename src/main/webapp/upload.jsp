<%--
  Created by IntelliJ IDEA.
  User: Linh
  Date: 5/30/2022
  Time: 8:31 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<input type="file" onchange="upload()" id="upload">
<body>
<form method="post" enctype="multipart/form-data">
    <input type="hidden" id="fileName" name="fileName">
    <input type="file" id="file" name="file" style="display: none">
    <input type="text" name="name">
    <button>Submit</button>
</form>
<script>
    function upload() {
        var fullPath = document.getElementById('upload').value;
        if (fullPath) {
            var startIndex = (fullPath.indexOf('\\') >= 0 ? fullPath.lastIndexOf('\\') : fullPath.lastIndexOf('/'));
            var filename = fullPath.substring(startIndex);
            if (filename.indexOf('\\') === 0 || filename.indexOf('/') === 0) {
                filename = filename.substring(1);
            }
            alert(filename);
            document.getElementById('fileName').value = filename;
            document.getElementById('file').value = filename;
        }
    }
</script>
</body>
</html>
