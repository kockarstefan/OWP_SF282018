$(document).ready(function(){
var app = new Vue({
	
	el: '#app',
	data: {
		users: [],
		currentUser: {},
		showEditModal: false,
		showDeleteModal: false,
		userRoleOptions: ['USER', 'ADMIN'],
        selectedUserRole: 'USER',
        userRoleOption: '',
		newPassword: '',
		repeatedPassword: '',
		
	},
	methods: {
		getUsers:function(){
			
			$.get('UserServlet', function(data){
				console.log(data);
			 if(data.status == 'success') {
				 	
					app.users = data.users;
					
				}
			});
			
			},
		selectedUser: function(user) {
				app.currentUser = user;
			},
			
		updateUser: function(event) {
			var params = {
					'action': 'update',
					'username': this.currentUser.username,
					'newPassword': this.newPassword,
					'repeatedPassword': this.repeatedPassword,
					'newUserRole': this.selectedUserRole,
			}
			$.post('UserServlet', params, function(data){
				if(data.status == 'success') {
					this.showEditModal = false;
				}
				
				
			});
				
			
		},
		
		deleteUser: function(event) {
			var params = {
					'action': 'delete',
					'username': this.currentUser.username
			}
			$.post('UserServlet', params, function(data) {
				if(data.status=='success') {
					this.showDeleteModal = false;
					window.location.replace('Welcome.html');
				}
			});
		}
		}
		
})

app.getUsers();

});