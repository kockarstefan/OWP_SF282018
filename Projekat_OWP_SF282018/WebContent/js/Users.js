$(document).ready(function(){

var app = new Vue({
	
	el: '#app',
	data: {
		users: []
	},
	methods: {
		getUsers:function(){
			
			$.post('UserServlet', function(data){
				console.log(data);
			 if(data.status == 'success') {
					app.users = data.users;
				}
			});
			
			}
		}
		
})

app.getUsers();

});