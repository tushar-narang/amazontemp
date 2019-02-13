function viewProductDetails(id) {
	localStorage.setItem("viewingProduct", id);
	window.location.href = "product_detail.html";
}