class RatingsController < ApplicationController
  before_action :authenticate_user!

  def create
    @review = Review.find(params[:review_id]).decorate
    @review.vote_by voter: current_user, vote: params[:review_rating]
    respond_to do |format|
      format.html { redirect_to movie_path(@review.movie) }
      format.js
    end
  end

  def destroy
    @review = Review.find(params[:review_id]).decorate
    @review.unvote_by current_user
    respond_to do |format|
      format.html { redirect_to movie_path(@review.movie) }
      format.js
    end
  end
end
