<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@page language = "java" contentType="text/html; charset=UTF-8" 
	import="java.util.*,restoran.config.*, restoran.admin.*" pageEncoding="UTF-8"%>
<% 
request.setCharacterEncoding("UTF-8");
String table = "categories";
Config conf = new Config();
AdminCategories ac = new AdminCategories();
AdminData data = new AdminData();

int id = 0;

int files_num = 2;
boolean files_titles = true;
int files_left = 0;

int img_num = 5;
int[] img_sizes = {800,600,400,300};
boolean img_titles = true;
int imgs_left = 0;

int img_static_num = 1;
int[] img_static_sizes = {800,600,400,300};
boolean img_static_titles = true;

int google_map = 1; // 1 or 0

//id = (int)$_GET[id];

//int id = 111;
String[] data_all = null; // dohvaća podatke

/*String[] data = data_all["data"];
String[] imgs = data_all["imgs"];
String[] imgs_static = data_all["imgs_static"];
String[] files = data_all["files"];*/

int parent_id = 0;
if(request.getParameter("id") != null)
{
	id = Integer.parseInt(request.getParameter("id"));
	if(id > 0){
		LinkedHashMap<String,String> map = data.getData(table, id, "");
		request.setAttribute("map", map);
		
		LinkedHashMap<String,String> map_site_photos = data.getData(table, id, "site_photos");
		request.setAttribute("map_site_photos", map_site_photos);
		imgs_left = img_num - Integer.parseInt(map_site_photos.get("count"));
		
		LinkedHashMap<String,String> map_site_files = data.getData(table, id, "site_files");
		request.setAttribute("map_site_files", map_site_files);
		files_left = files_num - Integer.parseInt(map_site_files.get("count"));
		
		parent_id = Integer.parseInt(map.get("parent_id"));
	}
}
%>
<jsp:include page="include/jsp/header.jsp" />

<div class="content">

<div class="breadcr">Nalazite se na: Kategorije &raquo; <strong>Unos</strong></div>

<%
if( (request.getParameter("success") != null) && request.getParameter("success").equals("1") )
{
	out.print("<div class=\"success\">Uspješno ste spremili podatke!</div>");
}
%>
<div class="main">

<form accept-charset="UTF-8" name="novost" id="novost" action="<%=request.getContextPath()%>/AdminCrudServlet" method="post" enctype="multipart/form-data">
	<%-- <input type="hidden" name="e_id" id="e_id" value="<% if( ($_GET["id"] != "") ){ out.println($_GET["id"]); } %>" />--%>
	
  <%--
  <c:forEach var="i" items="${map}">
    Polje: <c:out value="${i.key}" />, 
    vrijednost: <c:out value="${i.value}" /> <br />
    aaaa: <c:out value="${map['title_hr']}" /> <br />
  </c:forEach> 
  bbbbb: <input type="text" value="${map['title_hr']}" /> <br />
  --%>
  <%-- 
  <c:forEach var="c" items="${map}">
    Country aaa: ${c.key}  - Capital: ${c.value}
  </c:forEach>
  --%>
	<%
	int m = 1;
	for(int i = 0; i < img_sizes.length; i++)
	{
		String type = ((i % 2) == 0) ? "w" : "h";
		%>
		<input type="hidden" name="<% out.print(type);%>_<% out.print(m);%>" id="<% out.print(type);%>_<% out.print(m);%>" value="<% out.print(String.format("%s", img_sizes[i]));%>" />
		<%
		if((i % 2) != 0)
		{
			m++;
		}
	}
	%>
	<input type="hidden" name="table" id="table" value="<% out.print(table); %>" />
	<input type="hidden" name="id" id="id" value="<% out.print(id); %>" />
	<table cellspacing="0" cellpadding="0" border="0">
		<tr>
			<td>
				<h2><a href="javascript:;" onclick="showHide('unos1');"><img src="images/h2_arr.gif" alt="Prikaži / Sakrij" /> Sadržaj</a></h2>
				<div id="unos1">
					<table cellspacing="5" cellpadding="5" border="0">
						
						<tr>
							<td width="150"><strong>U kategoriji :</strong> </td>
							<td width="569" valign="top">
								<select name="parent_id" id="parent_id">
									<option value="0">--nije u podkategoriji--</option>
									<% 
									out.println(ac.display_tree_select2(0, 1, parent_id));
									%>
								</select>
							</td>
						</tr>
						<tr>
							<td colspan="2" class="line1"> </td>
						</tr>
						<%
						for(int i = 0; i < conf.langs_arr.length; i++)
						{
							String item_lng = String.format("%s", conf.langs_arr[i]);
							request.setAttribute("item_lng", "title_"+item_lng);
						%>
							<tr>
								<td width="150"><strong>Naslov kategorije [<% out.println(conf.langs_arr[i].toUpperCase());%>] :</strong> </td>
								<td width="569" valign="top"><input id="title_<% out.print(item_lng);%>" name="title_<% out.print(item_lng);%>" type="text" class="txt" value="${map[item_lng]}" /></td>
							</tr>
						<%
						}
						%>
						<tr>
							<td colspan="2" class="line1"> </td>
						</tr>
						<%
						for(int i = 0; i < conf.langs_arr.length; i++)
						{
							String item_lng = String.format("%s", conf.langs_arr[i]);
							request.setAttribute("item_lng", "text1_"+item_lng);
						%>
							<tr>
								<td width="150"><strong>Tekst [<% out.println(conf.langs_arr[i].toUpperCase());%>] :</strong> </td>
								<td width="569" valign="top"><textarea id="text1_<% out.print(item_lng);%>" name="text1_<% out.print(item_lng);%>" cols="" rows="15" class="txt ckeditor">${map[item_lng]}</textarea></td>
							</tr>
						<%
						}
						%>
						<tr>
							<td colspan="2" class="line1"> </td>
						</tr>
					</table>	
				</div>


				<%
				if(img_num > 0)
				{
				%>
				<h2><a href="javascript:;" onclick="showHide('unos2');"><img src="images/h2_arr.gif" alt="Prikaži / Sakrij" /> Slike</a></h2>
				<div id="unos2">
				<table cellspacing="0" cellpadding="10" border="0">
					<tr>
						<td width="150" valign="top"><strong>Slike (prva slika će biti na početnoj stranici) :</strong></td>
						<td valign="top" id="img_holder_td">
						  <%-- <c:forEach var="p" items="${map_site_photos}">
						    Polje: <c:out value="${p.key}" />, 
						    vrijednost: <c:out value="${p.value}" /> <br />
						  </c:forEach> --%>
						<%
							//int imgs_left = img_num;
							imgs_left = ((id > 0) && (imgs_left < img_num)) ? imgs_left : img_num;
							imgs_left = (imgs_left > 5) ? 5 : imgs_left ;
							
							if( imgs_left > 0 )
							{
								for(int i=1; i<=imgs_left; i++)
								{
									out.println("<input type=\"file\" class=\"browse\" id=\"img_"+i+"\" name=\"img_"+i+"\" /><br/>");
								}
							}
						%>
						</td>
		            </tr>
					<tr>
						<td class="line1" colspan="2"> </td>
		            </tr>
		            <tr>
						<td colspan="2"> 
							<div class="slike">
							
								<c:forEach var="p" items="${map_site_photos}">
									<c:set var="string" value="${p.key}"/>
									<c:if test="${fn:startsWith(string, 'photo_name')}">
									   <%--<p>Ovo je slika: <c:out value="${p.value}" /></p>--%>
									   <c:set var="img_name_id" value="${p.key}"/>
									   <c:set var="img_id" value="${fn:replace(img_name_id, 'photo_name_', '')}" />
									   <%--<c:set var="img_name" value="${p.value}"/>
									   <c:set var="img_name_without_dot" value="${fn:replace(img_name, '.', '')}" />--%>
									   <div class="unos_slika" id="img_holder_<c:out value="${img_id}" />">
											<input type="button" name="del" value="" class="delete_btn" onclick="if(confirm('Jeste li sigurni da želite obrisati sliku?')){ del_file('<%=conf._SITE_URL%>', '<c:out value="${p.value}" />', '<c:out value="${img_id}" />', 'site_photos'); }" />
											<div class="unos_slika_img"><img src="<%=conf._SITE_IMG_URL%>upload_data/site_photos/th_<c:out value="${p.value}" />" height="90"/></div>
											<table></table>
										</div>
									</c:if>
								</c:forEach> 
								
							</div>
						</td>
					</tr>
				</table>
				</div>
				<%
				}
				%>
				
				<%
				if(files_num > 0)
				{
				%>
				<h2><a href="javascript:;" onclick="showHide('unos3');"><img src="images/h2_arr.gif" alt="Prikaži / Sakrij" /> Dokumenti</a></h2>
				<div id="unos3">
				<table cellspacing="0" cellpadding="10" border="0">
					<tr>
						<td width="150" valign="top"><strong>Dokumenti :</strong></td>
						<td valign="top" id="img_holder_td">
						<%
							files_left = ((id > 0) && (files_left < files_num)) ? files_left : files_num;
							files_left = (files_left > 5) ? 5 : files_left ;
							
							if( files_left > 0 )
							{
								for(int i=1; i<=files_left; i++)
								{
									out.println("<input type=\"file\" class=\"browse\" id=\"file_"+i+"\" name=\"file_"+i+"\" /><br/>");
								}
							}
						%>
						</td>
		            </tr>
					<tr>
						<td class="line1" colspan="2"> </td>
		            </tr>
		            <tr>
						<td colspan="2"> 
							<div class="slike">
							
								<c:forEach var="p" items="${map_site_files}">
									<c:set var="string" value="${p.key}"/>
									<c:if test="${fn:startsWith(string, 'file_name')}">
									   <%--<p>Ovo je slika: <c:out value="${p.value}" /></p>--%>
									   <c:set var="file_name_id" value="${p.key}"/>
									   <c:set var="file_id" value="${fn:replace(file_name_id, 'file_name_', '')}" />
									   <div class="unos_slika" id="file_holder_<c:out value="${file_id}" />">
											<input type="button" name="del" value="" class="delete_btn" onclick="if(confirm('Jeste li sigurni da želite obrisati dokument?')){ del_file('<%=conf._SITE_URL%>', '<c:out value="${p.value}" />', '<c:out value="${file_id}" />', 'site_files'); }" />
											<div class="unos_slika_img">
												<a href="<%=conf._SITE_URL%>upload_data/site_files/<c:out value="${p.value}" />"><c:out value="${p.value}" /></a>
											</div>
											<table></table>
										</div>
									</c:if>
								</c:forEach> 
								
							</div>
						</td>
					</tr>
				</table>
				</div>
				<%
				}
				%>
				
				<table>
					<tr>
						<td colspan="2" class="line1"> </td>
					</tr>
					<tr>
						<td colspan="2">
							<input type="submit" name="spremi1" class="spremi_n" value="Spremi i unesi novu" />
							<input type="submit" name="spremi2" class="spremi_pr" value="Spremi i pregledaj sve" />
							<input type="submit" name="spremi3" class="spremi_s" value="Spremi" />
						</td>
					</tr>
				</table>
				
			</td>
		</tr>
	</table>
</form>

</div>

</div>

<jsp:include page="include/jsp/footer.jsp" />