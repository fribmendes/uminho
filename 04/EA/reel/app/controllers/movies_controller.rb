class MoviesController < ApplicationController
  before_action :authenticate_user!

  def show
    @movie = Movie.find(params[:id]).decorate
    @actors = @movie.cast
    if request.xhr?
      render partial: 'movies/reviews'
    end

    render layout: 'application'
  end

  def trending
    @movies = Movie.trending
  end

  def popular
    @movies = Movie.popular
  end

  def releases
    @movies = Movie.releases
  end

  def upcoming
    @movies = Movie.upcoming
  end
end
