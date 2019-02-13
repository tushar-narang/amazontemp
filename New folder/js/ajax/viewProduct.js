
function viewProduct() {
	if(localStorage.getItem("viewingProduct") != "") {
		var api = "http://localhost:8055/amazon.com/webapi/ProductController/productsById/"+localStorage.getItem("viewingProduct");
		var product;

		//Ajax Call for getting individual product
		$.get(api , function(data, status){
		    product = data[0];
		    
		    localStorage.setItem("seller_id", product.seller_id);
		    //Inserting places to div
		    $('#productTitle').html(product.productname);
		    $('#originalPrice').html(product.price);
		    $('#discountedPrice').html(product.discountedprice);
		    $('#description').html(product.description);
		    $('#itemImage').html("<img src='"+product.product_images[0].url+"'/>");
		   // $('#product_hidden').html("<input type='hidden' name='product_id' value='"+localStorage.getItem("viewingProduct")+"' id='product_id' />");
		});
		
	} else {
		window.location.href = "404.html";
	}
}