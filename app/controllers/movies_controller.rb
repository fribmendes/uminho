class MoviesController < ApplicationController
  def show
    @movie = Movie.find(params[:id])
    @actors = @movie.cast
  end

  def index
    @movies = Movie.all
  end
end
