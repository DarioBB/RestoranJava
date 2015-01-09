<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="include/jsp/header.jsp" />

<div class="content">

	<div class="breadcr">Nalazite se na: <strong>Naslovna</strong></div>

	<div class="main">
	<p>
	Dobrodošli na VirtusCMS sučelje za administraciju web stranica <strong>Ime site-a...</strong>.
	</p>

	<p>Ovdje možete pregledati statistike najnovijih logiranja i aktivnosti, kao i ukupne statistike stranice. </p>

	</div>

	<div class="stat_box">
		
		<h3>Posljednja logiranja</h3>
		
		<table class="pregled_table3">
			<tr id="pregled_title">
				<td class="td5"><a>Datum i vrijeme</a></td>
				<td><a>IP računala</a></td>
				<td><a>Korisnik</a></td>
			</tr>
		</table>
	</div>

	<div class="stat_box">
		
		<h3>Zadnje aktivnosti</h3>
		
		<table class="pregled_table3">
			<tr id="pregled_title">
				<td class="td5"><a>Datum i vrijeme</a></td>
				<td><a>IP računala</a></td>
				<td><a>Korisnik</a></td>
				<td><a>Cjelina</a></td>
				<td><a>Zapis</a></td>
				<td><a>Akcija</a></td>
			</tr>

		</table>
		
	</div>


	<div class="stat_box">
		
		<h3>Statistike</h3>
		
		<ul>

		</ul>
		
	</div>


</div>

<jsp:include page="include/jsp/footer.jsp" />