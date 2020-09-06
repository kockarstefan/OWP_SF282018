$(document).ready(function(){

var id = window.location.search.slice(1)
console.log(id);
	
var app = new Vue({
	
	el: '#app',
	data: {
		movies: [],
		currentMovie: {},
		title: '',
		director: '',
		genre: '',
		duration: '',
		distributor: '',
		country: '',
		releaseDate: '',
		showEditModal: false,
		showDeleteModal: false,
		showAddModal: false,
		showMessage: false,
		message: ''
	},
	methods: {
			getMovies:function(){
			
			$.get('MoviesServlet', function(data){
				console.log(data);
			 if(data.status == 'success') {
					app.movies = data.movies;
				}
			});
			
			},
			
			selectedMovie: function(movie) {
				app.currentMovie = movie;
			},
			
			updateMovie: function(event) {
				if(!this.validation()) {
	                this.showMessage = true;
	                
	            }else {
	            	 this.message = '';
	                 this.showMessage = false;
	                 
	                 var params = {
	 						'action': 'update',
	 						'id': this.currentMovie.id,
	 						'title': this.currentMovie.title,
	 						'director': this.currentMovie.director,
	 						'genre': this.currentMovie.genre,
	 						'duration': this.currentMovie.duration,
	 						'distributor': this.currentMovie.distributor,
	 						'country': this.currentMovie.country,
	 						'releaseDate': this.currentMovie.releaseDate,	
	 				}
				$.post('MoviesServlet', params, function(data){
					 if (data.status == 'failure') {
	                        app.message = data.message;
	                        app.showMessage = true;
	                    }
					 else if(data.status == 'success') {
						this.showEditModal = false;
					}
				});
	            }
			},
			
			deleteMovie: function(event) {
				var params = {
						'action': 'delete',
						'id':  this.currentMovie.id
				}
				$.post('MoviesServlet', params, function(data) {
					if(data.status == 'success') {
						this.showDeleteModal = false;
						window.location.replace('Welcome.html')
					}
				
			});
			},
			
			addMovie: function(event) {
				if(!this.validation2()) {
	                this.showMessage = true;
	                
	            }else {
	            	 this.message = '';
	                 this.showMessage = false;
	                 
	                 var params = {
	 						'action': 'add',
	 						'title': this.title,
	 						'director': this.director,
	 						'genre': this.genre,
	 						'duration': this.duration,
	 						'distributor': this.distributor,
	 						'country': this.country,
	 						'releaseDate': this.releaseDate,	
	 				}
	                 
	                $.post('MoviesServlet', params, function(data) {
	                	if (data.status == 'failure') {
	                        app.message = data.message;
	                        app.showMessage = true;
	                    }
					 else if(data.status == 'success') {
						this.showAddModal = false;
						window.location.replace('Welcome.html')
					}
	                });
	            }
				
				
			},
			
			validation: function () {
	            if (this.currentMovie.title === '') {
	                this.message = 'Title input field must not be empty!';
                }
                else if (this.currentMovie.director === '') {
	                this.message = 'Director input field must not be empty!';
                }
                else if (this.currentMovie.genre === '') {
	                this.message = 'Genre input field must not be empty!';	
	            }
                else if (typeof this.currentMovie.duration === 'undefined') {
	                this.message = 'Duration input field must not be empty';
                }
                else if (this.currentMovie.distributor === '') {
	                this.message = 'Distributor input field must not be empty!';
	            }
                else if (this.currentMovie.country === '') {
	                this.message = 'Country field must not be empty!';	
	            }
                else if (typeof this.currentMovie.releaseDate === 'undefined') {
	                this.message = 'Release date field must not be empty!';
                }
	            else {
	                return true;
	            }
	            return false;
            },
            
            validation2: function () {
	            if (this.title === '') {
	                this.message = 'Title input field must not be empty!';
                }
                else if (this.director === '') {
	                this.message = 'Director input field must not be empty!';
                }
                else if (this.genre === '') {
	                this.message = 'Genre input field must not be empty!';	
	            }
                else if (typeof this.duration === 'undefined') {
	                this.message = 'Duration input field must not be empty';
                }
                else if (this.distributor === '') {
	                this.message = 'Distributor input field must not be empty!';
	            }
                else if (this.country === '') {
	                this.message = 'Country field must not be empty!';	
	            }
                else if (typeof this.releaseDate === 'undefined') {
	                this.message = 'Release date field must not be empty!';
                }
	            else {
	                return true;
	            }
	            return false;
            }
			
		}
		
})

app.getMovies();

});