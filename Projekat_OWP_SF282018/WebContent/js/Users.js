$(document).ready(function(){
var app = new Vue({
	
	el: '#app',
	data: {
		users: [],
		searchUsername: '',
		selectedSort: '',
		sortOptions: [''],
		sortOption: '',
        selectedUserRoleSearch: '',
        searchUserRoleOptions: [
        	{ text: 'All', value: ''},
        	{ text: 'User', value: 'USER' },
        	{ text: 'Admin', value: 'ADMIN' }
        ],
        searchUserRoleOption: '',
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
			
			var params = {
                    'username': this.searchUsername,
                    'userRole': this.selectedUserRoleSearch
                }
			$.get('UserServlet', params, function(data){
				console.log(data);
			 if(data.status == 'success') {
				 	
					app.users = data.filteredUsers;
					
				}
			});
			
			},
			
			getSortOptions: function() {
            	this.sortOptions = [
                	'Auto',
                	'UsernameASC',
                	'UsernameDESC',
                	'RoleASC',
                	'RoleDESC',
                ];
            	this.selectedSort = 'Auto';
            },
            refreshUsers: function(event) {
            	this.getUsers();
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
		},
		computed: {
            orderedUsers: function () {
            	if (this.selectedSort === 'Auto')
            		return this.users;
            	else if (this.selectedSort === 'UsernameASC')
            		return _.orderBy(this.users, 'username');
            	else if (this.selectedSort === 'UsernameDESC')
            		return _.orderBy(this.users, 'username').reverse();
            	else if (this.selectedSort === 'RoleASC')
            		return _.orderBy(this.users, 'role');
            	else if (this.selectedSort === 'RoleDESC')
            		return _.orderBy(this.users, 'role').reverse();
              }
        	}
		
})

app.getSortOptions();
app.getUsers();

});