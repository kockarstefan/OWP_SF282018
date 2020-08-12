$(document).ready(function() {

	var app = new Vue({
	    el: '#app',
	    data: {
	        username: '',
	        password: '',
	        repeatedPassword: '',
	        message: '',
	        showMessage: false
	    },
	    methods: {
	        registerSubmit: function(event) {
	        	
	            if(!this.validation()) {
	                this.showMessage = true;
	                this.username = '';
	                this.password = '';
	                this.repeatedPassword = '';
	            }
	            else {
	                this.message = '';
	                this.showMessage = false;
	                var params = {
	                        'username': this.username,
	                        'password': this.password
	                }
	                
	                $.post('RegisterServlet', params, function(data) {
	                    
	                    if (data.status == 'failure') {
	                        app.message = data.message;
	                        app.showMessage = true;
	                    }
	                    else if (data.status == 'success') {
	                    	
	                        window.location.replace('Index.html');
	                        
	                    }
	                });
	            }
	            
	            return false;
	        },
	        validation: function () {
	            if (this.username === '') {
	                this.message = 'Korisničko ime mora biti uneto!';
	            }
	            else if (this.password === '') {
	                this.message = 'Lozinka mora biti uneta!';
	            }
	            else if (this.password != this.repeatedPassword) {
	                this.message = 'Lozinke nisu iste!';
	            }
	            else {
	                return true;
	            }
	            return false;
	        }
	    }
	})
});