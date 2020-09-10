$(document).ready(function() {
    var app = new Vue({
        el: '#app',
        data: {
        	loggedInUser: { username: '', userRole: ''},
            
            
        },
        methods: {
        	getLoggedInUser: function() {
	    		$.get('LoggedInUserServlet', function(data) {
	    	        console.log(data);
	    	        
	    	        if (data.status == 'success') {
	    	        	app.loggedInUser.username = data.loggedInUser.username;
	    	            app.loggedInUser.userRole = data.loggedInUser.userRole;
	    	            return;
	    	        }
	    	    });
	    	}   
        }
    })
    
    app.getLoggedInUser();
});
