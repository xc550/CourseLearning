<%@page import="java.math.BigDecimal"%>
<%@page import="com.cl.dao.KnowledgeWeight"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.cl.dao.Section"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>UpdateWeight</title>
<link rel="stylesheet" href="public/bower_components/bootstrap/dist/css/bootstrap.css">
</head>
<body>
	<div class="container">
		<div class="row">
			<s:action name="sidebar_topframe" executeResult="true"></s:action>
		</div>
		<div class="row">
			<div class="col-md-3">
				<s:action name="sidebar_tea_sectionlist" executeResult="true"></s:action>
			</div>
			<div class="col-md-8 col-md-offset-1">
				<div class="row">
					<!-- 前端需要传到后台之前的数组中课程号和章节号 -->
					<form action="teacher_updatesectionweight?section_id=0" method="post">
						<table class="table">
							<%
								ArrayList<String> columns = (ArrayList<String>) request.getAttribute("columns");
								ArrayList<Section> res = (ArrayList<Section>)request.getSession().getAttribute("weightlist");
								int row = (res.size() % 4 == 0 ? res.size() / 4 : res.size() / 4 + 1);
								for (int i = 0; i < row * 2; i++) {
							%>
								<tr>
							<%
									for (int j = 0; j < 4; j++) {
										int ind = (i / 2) * 4 + j;
										if (ind >= res.size())
											continue;
										String title = columns.get(ind);
										double val = new BigDecimal(res.get(ind).getSection_weight()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
										if (i % 2 == 0) {
							%>
									<td><%=title %></td>
							<%
										} else {
							%>
									<td><input name="section[<%=ind %>].section_weight" value="<%=val %>" type="number" min=0 max=1 step=0.01></td> 
							<%
										}
									}
								}
							%>
								</tr>
						</table>
						<button type="submit" class="btn btn-success">更新</button>
					</form>
				</div>
			</div>
		</div>		
	</div>
</body>
</html>