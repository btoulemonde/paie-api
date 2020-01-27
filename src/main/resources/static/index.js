function listerBulletins() {
	fetch('bulletins').then(function(resp) {
		return resp.json();
	}).then(
			function(listeBulletins) {
				var bulletins = listeBulletins.map(
						function(bulletin) {
							return '<tr><td>' + bulletin.dateCreation + '</td>'
									+ '<td>' + bulletin.periode.dateDebut +" - " + bulletin.periode.dateFin + '</td>'
									+ '<td>' + bulletin.matricule + '</td>'
									+ '<td>' + bulletin.salaireBrut + '</td>'
									+ '<td>' + bulletin.netImposable + '</td>'
									+ '<td>' + bulletin.netAPayer + '</td><td></td></tr>';
						}).join('');
				document.querySelector('tbody').innerHTML = bulletins;
			})

}

function listerPeriodes(){
	fetch('periodes')
	.then(function(resp) {
		return resp.json();
	})
	.then(
			function(listePeriodes) {
				var periodes = listePeriodes.map(
						function(periode) {
							return '<option>' + periode.dateDebut + " - "
									+ periode.dateFin + '</option>';
						}).join('');
				document.getElementById('periode').innerHTML = ' <label for="periode">periode</label><select class="form-control" name="periode" id="periode">'
						+ periodes + '</select>';
			})
}