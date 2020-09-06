
$(document).ready(function(){
	$.get('LoggedInUserServlet', function(data) {
        console.log(data);
        
       if (data.status == 'success') {
            window.location.replace('Welcome.html');
            return;
       }
});

var app = new Vue({
	el:'#app',
	data: {
		username: '',
		password: '',
		message: '',
		showMessage: false
	},
	methods: {
		loginSubmit: function(event) {
        	
			if(!this.validation()) {
                this.showMessage = true;
                this.username = '';
                this.password = '';
            }
            else {
                this.message = '';
                this.showMessage = false;
                var params = {
                        'username': this.username,
                        'password': this.password
                }
                
                $.post('LoginServlet', params, function(data) {
                    console.log(data);
                    
                    if (data.status == 'failure') {
                        if (data.message == 'username') {
                            app.message = 'Incorrect username.';
                            app.username = '';
                            app.password = '';
                        }
                        else if (data.message == 'password') {
                            app.message = 'Incorrect password.';
                            app.password = '';
                        }
                        else if (data.message == 'deactivated') {
                        	app.message = 'Account deactivated.';
                        }
                        else
                            app.message = data.message;
                            
                        app.showMessage = true;
                        

                    }
                    else if (data.status == 'success') {
                        window.location.replace('Welcome.html');
                    }
                });
            }
            
            return false;
        },
        validation: function () {
            if (this.username === '') {
                this.message = 'Username field must not be empty!';
            }
            else if (this.password === '') {
                this.message = 'Password field must not be empty!';
            }
            else {
                return true;
            }
            return false;
        }
    }
	
  })
});
