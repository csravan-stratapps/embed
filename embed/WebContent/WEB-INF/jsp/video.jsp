<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta property="og:title" content="${video.title}"/>
<meta property="og:type" content="product" />
<meta property="og:image" content="${video.gifImagePath}"/>
<meta property="og:image:secure_url" content="${video.gifImagePath}" />
<meta property="og:image:type" content="image/gif" />
<meta property="og:image:width" content="480" />
<meta property="og:image:height" content="270" />
<meta property="og:description" content="${video.description}" />
<meta property="fb:app_id" content="1348853938538956" />


<title>${video.title}</title>
</head>
<body>
	Redirecting...
<script type="text/javascript">
	window.location.href = "${clientUrl}/share/${video.shortenerURLAlias}";
</script>
</body>
</html>