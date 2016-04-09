<%@ page language="java" contentType="text/html; charset=GB2312"
    pageEncoding="GB2312"%>
<jsp:directive.page import="java.lang.Integer"/>
<jsp:directive.page import="cn.edu.ccnu.search.util.*"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
  	<title>搜索引擎测试</title>
  	<link rel="stylesheet" href="./style.css" type="text/css" />
  </head>
  
  <body>
   <div>
   <table width=600>
    <tr>
     <td valign="middle">
     <a href="index.html"><img vspace="0" hspace="0" border="0" src="search.png"></a>
     </td>
     <td height="80px">
     <table height="80px">
     <tr><td valign="bottom"><font size="4" color="#FF0000" face="Verdana"><strong>搜索引擎&nbsp;</font>
     <font color="#3333FF" size="2" face="Verdana">Search Engine</font></td></tr>
     <tr><td>
     <form action="s.jsp" method="GET">
        <input type="text" name="keys" style="height:30px;width:300px" maxlength="100"><input type="submit" style="color:white;background-color: #38f;height:36px;width:80px;border:0px" value="查找">
     </form>
     </td></tr>
     </table>
     </td>
    </tr>
   </table>
   </div>
   


   
   <div><hr></div>
   <div id="inf" class="inf" style="visibility:visible"><font face="Verdana">Loading...</font></div>
   <div class="inf">
   <%
      String param = request.getParameter("keys");
	  param = new String(param.getBytes("iso-8859-1"), "gb2312");
	  out.print("您提交的关键字：" + param);
    %>
   </div>
   <div><h2>查找结果：</h2></div>
   <div id="result" class="result" style="visibility:hidden;width:400px">
   <%
     // 提取关键字
     String keys = CharacterHandle.trans(param);
     String[] results = new Query().getQueryResult(keys.split(" "));
     // 结果总数
     int length = results.length;
     // 显示记录的启示序号
     int start = 1;
     // 显示记录的终止序号
     int end = 0;
     // 提取显示页数，如果为空，表示从第一页显示
     if(request.getParameter("page") != null){
    	 start = Integer.parseInt(request.getParameter("page"));
     }
     // 计算页数同序号的对应关系
     start = (start - 1) * 10;
     end = start + 10;
     
     if(end >= length){
    	 end = length;
     }
     // 从返回的查询结果中提取需要显示的部分
     for(int i = start; i < end; i++){
    	 int position = results[i].indexOf("|");
    	 String title = results[i].substring(0, position);
    	 String url = results[i].substring(position + 1, results[i].indexOf("|", position + 1));
    	 position = results[i].indexOf("|", position + 1);
    	 String context = results[i].substring(position + 1);
    	 context = context.replace(" ", "");  
    	 context = context.replace("&nbsp;", " ");
    	 String summary = "";
    	 String[] k = keys.split(" ");
    	 int maxChar = 100;
    	 int l = k.length;
    	 int summaryStart = 0;
    	 int summaryEnd = 0;
    	 // 单关键字
    	 if(l == 1){
    		 summaryStart = context.indexOf(k[0]) + k[0].length();
    		 summaryEnd = summaryStart + maxChar;
    		 if(summaryEnd > context.length()){
    			 summaryEnd = context.length();
    		 }
    		 summary = context.substring(summaryStart, summaryEnd);
    		 // 将关键字置位红色
    		 summary = "<font color=\"#FF0000\">" + k[0] + "</font>" + summary;
    	 }
    	 // 多关键字
    	 else{
    		 int count = maxChar / l;
    		 if(count < 5){
    			 count = 5;
    		 }
    		 for(int j = 0; j < l; j++){
    			 summaryStart = context.indexOf(k[j]) + k[0].length();
    			 summaryEnd = summaryStart + count;
    			 if(summaryEnd > context.length()){
        			 summaryEnd = context.length();
        		 }
    			 if("".equals(summary)){
    				 summary = "<font color=\"#FF0000\">" + k[j] + "</font>" + context.substring(summaryStart, summaryEnd);
    			 }
    			 else{
    				 summary = summary + "..." + "<font color=\"#FF0000\">" + k[j] + "</font>" + context.substring(summaryStart, summaryEnd);
    			 }
    			 
    		 }
    	 }
    	 // 输出查询结果
    	 out.print("<a href=\"http://" + url + "\">" + "<font color=\"#0033CC\">" + title + "</font>" + "</a><br />");
    	 out.print("<font size=2>" + summary + "</font>");
    	 out.print("<font color=\"#00CC33\">" + url + "</font><br /><br /><br />");
     
     }
   %>
   </div>
   <div id="page">
   <%
     // 显示页数导航栏
     for(int j = 0; j <= length; j += 10)
     out.print("<a href=\"s.jsp?keys=" + keys + "&page=" + (j / 10 + 1 ) + "\">[" + (j / 10 + 1 )+ "]</a>&nbsp;");
   %>
   </div>
   
   <%-- 显示结果 --%>
   <script type="text/javascript">
   document.getElementById("inf").style.visibility="hidden";
   document.getElementById("result").style.visibility="visible";
   </script>
   
   
  </body>
</html>
