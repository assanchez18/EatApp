function onScanSuccess(qrCodeMessage) {
	if (isNaN(qrCodeMessage)) {
		alert("QR no válido");
		return;
	}
	document.getElementById("code").value = qrCodeMessage;
	document.getElementById("scanner").submit();
}

var html5QrcodeScanner = new Html5QrcodeScanner("reader", {
	fps : 10,
	qrbox : 250
});
html5QrcodeScanner.render(onScanSuccess);
