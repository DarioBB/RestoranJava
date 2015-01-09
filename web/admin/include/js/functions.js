/*function del_img(site_url, name, id)
{
	$.post(site_url+"AdminActionServlet",{delete_content:name,delete_content_id:id},function(result){
		name = name.replace(".", "");
		$("#img_holder_"+name+"").fadeOut("slow");
	});
}*/

function del_file(site_url, name, id, table)
{
	$.post(site_url+"AdminActionServlet",{delete_content:name, delete_content_id:id, content_table:table},function(result){
		if(table == 'site_photos'){
			$("#img_holder_"+id+"").fadeOut("slow");
		} else if(table == 'site_files'){
			$("#file_holder_"+id+"").fadeOut("slow");
		}
	});
}