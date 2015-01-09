function showHide(id){
	var table = document.getElementById(id);
	if (table.style.display == "none") table.style.display = "block";
	else table.style.display = "none";
}

function ShowHide(id){
	var table_name = 'sub' + id;
	var table = document.getElementById(table_name);
	if (table.style.display == "none") table.style.display = "block";
	else table.style.display = "none";
}

function ShowHideNew(id, ukupno){
	for (var i=1; i<=ukupno; i++){
	
		if(document.getElementById('sub'+i))
		{
			var vise = document.getElementById('sub'+i);
			var link = document.getElementById('li'+i);
			if (i==id){
				vise.style.display = "block";
				link.className = "cli2";
			}else{
				vise.style.display = "none";	
				link.className = "cli1";		
			}
		}
	}
}

function CloseNew(id){

		var vise = document.getElementById('more'+id);
		vise.style.display = "none";

}

function show_hide_2(container, id, id2){
	//alert(container);
	//var table = document.getElementById(id);
	//if (table.style.display == "none") table.style.display = "block";
	//else table.style.display = "none";
	
	//var tabs = getElementsByClassName(document.body,container);
	//getElementsByClassName(document.body,container).style.display='none';
	//alert(document.getElementsByClassName(container));
	//var test = document.getElementsByClassName(container);
	//alert(test);
	
	for(var i = 0; i <= (1000); i++)
	{
		//alert(container+id+'_'+i);
		if(document.getElementById(container+id+'_'+i))
		{
			//alert(container+id+i);
			//alert(document.getElementById(container+id+'_'+i);
			var table = document.getElementById(container+id+'_'+i);
			//var table2 = document.getElementById(container+id+'_'+i+'_2');
			//var table3 = document.getElementById(container+id+'_'+i+'_3');
			if (table.style.display == "none") 
			{
				table.style.display = "block";
				//table2.style.display = "block";
				//table3.style.display = "block";
			}
			else 
			{
				table.style.display = "none";
				//table2.style.display = "none";
				//table3.style.display = "none";
			}
		}
		
		if(document.getElementById(container+id+'_'+i+'_2'))
		{
			//alert(container+id+i);
			//alert(document.getElementById(container+id+'_'+i);
			var table2 = document.getElementById(container+id+'_'+i+'_2');
			if (table2.style.display == "none") 
			{
				table2.style.display = "block";
			}
			else 
			{
				table2.style.display = "none";
			}
		}
		
		if(document.getElementById(container+id+'_'+i+'_3'))
		{
			//alert(container+id+i);
			//alert(document.getElementById(container+id+'_'+i);
			var table3 = document.getElementById(container+id+'_'+i+'_3');
			if (table3.style.display == "none") 
			{
				table3.style.display = "block";
			}
			else 
			{
				table3.style.display = "none";
			}
		}
	}
}

/*function getElementsByClassName(node, classname) {
    var a = [];
    var re = new RegExp('(^| )'+classname+'( |$)');
    var els = node.getElementsByTagName("*");
    for(var i=0,j=els.length; i<j; i++)
        if(re.test(els[i].className))a.push(els[i]);
    return a;
}*/

//tabs = getElementsByClassName(document.body,'tab');

function selektiraj_termin(id,container) {
	window.document.getElementById(container).value = id;
}
/*function spremi_termin2222(id,id2,id3,url,container,container2,poruka,link_id,smjestaji_id) {
	var datum = id;
	var datum2 = id2;
	var jedinica_broj = id3;
	var termin_status_id = window.document.getElementById(container2).value;
	if(jedinica_broj == ''){
		jedinica_broj = 0;
	}
	//alert(datum);
	//alert(jedinica_broj);
	//alert(termin_status_id);
	if(termin_status_id == ''){
		alert(poruka);
	} else {
		if(termin_status_id == 1){ // id 1
			window.document.getElementById(link_id).className = 'kalendar_rez';
		} else if (termin_status_id == 2){ // id 2
			window.document.getElementById(link_id).className = '';
		} else { // id 3
			window.document.getElementById(link_id).className = 'kalendar_ned';
		}
		//makerequest(url+'/ajax/spremi_termin.php?id=1&datum='+datum+'&datum2='+datum2+'&jedinica_broj='+jedinica_broj+'&termin_status_id='+termin_status_id+'&smjestaji_id='+smjestaji_id+'', container);
		
		$.ajax({
			type: 'GET',
			data: ({datum : datum, datum2 : datum2, jedinica_broj : jedinica_broj, termin_status_id : termin_status_id, smjestaji_id : smjestaji_id}),
			url: '../ajax/prikaz_ponude.php',
			success: function(data){
				alert('test');
			}
		});
	}
}*/