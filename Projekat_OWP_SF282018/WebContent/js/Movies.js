$(document).ready(function(){

	
var app = new Vue({
	
	el: '#app',
	data: {
		movies: [],
		currentMovie: {},
		searchTitle: '',
		searchDirector: '',
		searchGenre: '',
		searchDistributor: '',
		searchCountry: '',
		durationMax: undefined,
		durationMin: undefined,
		dateMin: undefined,
		dateMax: undefined,
		sortOptions: [''],
        selectedSort: '',
        sortOption: '',
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
				console.log('title: ' + this.searchTitle);
			var params = {
	                    'searchTitle': this.searchTitle,
	                    'searchDirector': this.searchDirector,
	                    'searchGenre': this.searchGenre,
	                    'searchDistributor': this.searchDistributor,
	                    'searchCountry': this.searchCountry,
	                    'durationMin': this.durationMin,
	                    'durationMax': this.durationMax,
	                    'dateMin': this.dateMin,
	                    'dateMax': this.dateMax
	                }
			$.get('MoviesServlet',params, function(data){
				console.log(data);
			 if(data.status == 'success') {
					app.movies = data.filteredMovies;
				}
			});
			
			},
			
			getSortOptions: function() {
            	this.sortOptions = [
                	'Auto',
                	'Title ASC',
                	'Title DESC',
                	'Director ASC',
                	'Director DESC',
                	'Genre ASC',
                    'Genre DESC',
                    'Duration ASC',
                    'Duration DESC',
                    'ReleaseDate ASC',
                    'ReleaseDate DESC',
                    'Distributor ASC',
                    'Distributor DESC',
                    'Country ASC',
                    'Country DESC',
                ];
            	this.selectedSort = 'Auto';
            },
            refreshMovies: function(event){
            	
            	if (app.durationMin <= 0 || app.durationMin > 500) {
                    app.durationMin = undefined;
                }
            	if (app.durationMax <= 0 || app.durationMax > 500) {
                    app.durationMax = undefined;
                }
            	if (app.dateMin <= 0 || app.dateMin > 3000) {
                    app.dateMin = undefined;
                }
            	if (app.dateMax <= 0 || app.dateMax > 3000) {
                    app.dateMax = undefined;
                }
            	
            	this.getMovies();
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
            },
			
		},
		computed: {
            orderedMovies: function() {
                if (this.selectedSort === 'Auto')
            		return this.movies;
            	else if (this.selectedSort === 'Title ASC')
            		return _.orderBy(this.movies, 'title');
            	else if (this.selectedSort === 'Title DESC')
                    return _.orderBy(this.movies, 'title').reverse();
            	else if (this.selectedSort === 'Director ASC')
            		return _.orderBy(this.movies, 'director');
            	else if (this.selectedSort === 'Director DESC')
                    return _.orderBy(this.movies, 'director').reverse();
                else if (this.selectedSort === 'Duration ASC')
            		return _.orderBy(this.movies, 'duration');
            	else if (this.selectedSort === 'Duration DESC')
                    return _.orderBy(this.movies, 'duration').reverse();
            	else if (this.selectedSort === 'Genre ASC')
            		return _.orderBy(this.movies, 'genre');
            	else if (this.selectedSort === 'Genre DESC')
                    return _.orderBy(this.movies, 'genre').reverse();
                else if (this.selectedSort === 'ReleaseDate ASC')
            		return _.orderBy(this.movies, 'year');
            	else if (this.selectedSort === 'ReleaseDate DESC')
                    return _.orderBy(this.movies, 'year').reverse();
                else if (this.selectedSort === 'Distributor ASC')
            		return _.orderBy(this.movies, 'distributor');
            	else if (this.selectedSort === 'Distributor DESC')
                    return _.orderBy(this.movies, 'distributor').reverse();
                else if (this.selectedSort === 'Country ASC')
            		return _.orderBy(this.movies, 'country');
            	else if (this.selectedSort === 'Country DESC')
            		return _.orderBy(this.movies, 'country').reverse();
            }
        },
        watch: {
	    	sortOptions: function(newValues, oldValues){
	    		this.$nextTick(function(){ $('#sortInput').selectpicker('refresh'); });
	    	}
	    }
		
})

app.getSortOptions();
app.getMovies();

});