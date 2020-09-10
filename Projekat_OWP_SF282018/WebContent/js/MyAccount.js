$(document).ready(function() {
	var username = window.location.search.slice(1).split('&')[0].split('=')[1];
	
    var app = new Vue({
        el: '#app',
        data: {
        	loggedInUser: {},
    
        },
        methods: {
        	getLoggedInUser: function() {
        		
	    		$.post('LoggedInUserServlet',{'username': username}, function(data) {
	    	        console.log(data);
	    	        
	    	        if (data.status == 'success') {
	    	        	app.loggedInUser = data.loggedUser;
	    	        }
	    	    });
	    	}
   
        }

    })
    
    app.getLoggedInUser();
});