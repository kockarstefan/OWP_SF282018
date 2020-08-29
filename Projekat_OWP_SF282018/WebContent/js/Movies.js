$(document).ready(function(){

var app = new Vue({
	
	el: '#app',
	data: {
		movies: []
	},
	methods: {
		getMovies:function(){
			
			$.post('MoviesServlet', function(data){
				console.log(data);
			 if(data.status == 'success') {
					app.movies = data.movies;
				}
			});
			
			}
		}
		
})

app.getMovies();

});