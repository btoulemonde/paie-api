function listerBulletins() {
	fetch('bulletin').then(function(resp) {
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