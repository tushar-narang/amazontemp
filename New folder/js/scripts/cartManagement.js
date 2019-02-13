function displayCart(){
	
	if(localStorage.getItem("directBuy") != null) {
		var api = "http://localhost:8055/amazon.com/webapi/ProductController/productsById/"+localStorage.getItem("cartProduct");
		var product;

		//Ajax Call for getting individual product
		$.get(api , function(data, status){
		    product = data[0];
		    console.log(product);
		    $('#cartItems').append(generateItem(product.product_images[0].url, product.productname, product.id, localStorage.getItem("cartProductQty"), product.discountedprice));
		    var discount = (product.price - product.discountedprice)*parseInt(localStorage.getItem("cartProductQty"));
		    
		    $('#discount').html(discount);
		    $('#price').html(product.discountedprice*localStorage.getItem("cartProductQty"));
		});
		
	} else {
		window.location.href = "404.html";
	}
}


function generateItem(productImageUrl, productName, productId, productQuantity, productPrice) {
	var myItem = "<tr><td><figure class='media'><div class='img-wrap'>";
	myItem = myItem + "<img src='"+ productImageUrl+ "' class='img-thumbnail img-sm'>";
	myItem = myItem + "</div><figcaption class='media-body'>";
	myItem = myItem + "<h6 class='title text-truncate'>"+productName+"</h6>";
	myItem = myItem + "</figcaption></figure></td><td>";
	myItem = myItem + "<strong>"+productQuantity+"</strong>";
	myItem = myItem + "</td><td><div class='price-wrap'><var class='price'>INR "+productPrice*productQuantity+"</var>"; 
	myItem = myItem + "</div></td><td class='text-right'><a href='#' onclick='removeProduct();' class='btn btn-outline-danger'> × Remove</a></td></tr>";
	return myItem;
}

function removeProduct() {
	localStorage.removeItem("cartProductQty");
	localStorage.removeItem("cartProductValue");
	localStorage.removeItem("cartProduct");
	localStorage.removeItem("directBuy");
	window.location.href="home.jsp";

}