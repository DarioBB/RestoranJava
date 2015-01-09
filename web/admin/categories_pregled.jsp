<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="include/jsp/header.jsp" />

<div class="content">

<div class="breadcr">Nalazite se na: Kategorije &raquo; <strong>Pregled</strong></div>

<%--
	if( isset($_GET['status']) && $_GET['status'] == 'success' )
	{
		echo '<div class="success">Uspješno ste spremili podatke.</div>';
	}
	elseif( isset($_GET['status']) && $_GET['status'] == 'delete_ok')
	{
		echo '<div class="success">Uspješno obrisali podatke.</div>';
	}
--%>

<div class="main">

	<table cellspacing="0" cellpadding="0" border="0" class="pregled_table3">
        <tr id="pregled_title">
          <td class="td2">Kategorije</td>
          <td class="td3"></td>
          <td class="td3"></td>
          <td class="td3"></td>
        </tr>
        <tr>
          <td colspan="4" class="line1"> </td>
        </tr>
		<tr>
        	<td colspan="4">
			  	<div style="text-align:left; font-size:13px;">
	
				</div>
			</td>
        </tr>	
	</table>
</div>
</div>


<jsp:include page="include/jsp/footer.jsp" />