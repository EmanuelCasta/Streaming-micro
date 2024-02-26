package com.aplicacion.streaming.domain.service;

import com.aplicacion.streaming.domain.model.Movie;
import com.aplicacion.streaming.domain.model.MovieUser;
import com.aplicacion.streaming.domain.port.in.repository.IMovieRepository;
import com.aplicacion.streaming.domain.port.in.repository.IMovieUserRepository;
import com.aplicacion.streaming.domain.port.in.services.IMovieUserService;
import com.aplicacion.streaming.domain.port.out.IMovieApplicationServer;

public class MovieUserService implements IMovieUserService {
    private final IMovieUserRepository iMovieUserRepository;

    private final IMovieRepository iMovieRepository;



    public MovieUserService(IMovieUserRepository iMovieUserRepository,IMovieRepository iMovieRepository) {
        this.iMovieUserRepository = iMovieUserRepository;
        this.iMovieRepository = iMovieRepository;
    }

    @Override
    public void createReview(MovieUser movieUser) {
        MovieUser movieUserObtein = this.iMovieUserRepository.getMovieReview(movieUser);
        if(movieUserObtein== null){
            movieUser.setNoViewViews(0L);
            movieUser.setScore(null);
            movieUser.setEsVista(false);
            this.iMovieUserRepository.createReview(movieUser);
        }

    }
    @Override
    public void updateReview(MovieUser movieUser,Boolean esClickVideo) {
        MovieUser movieUserObtein = this.iMovieUserRepository.getMovieReview(movieUser);

        if(movieUserObtein!= null){

            this.validateUser(movieUser);

            this.changeQualifer(movieUserObtein, movieUser);

            this.changeScore(movieUserObtein, movieUser);

            this.changeVisualization(esClickVideo, movieUser, movieUserObtein);

            this.iMovieUserRepository.createReview(movieUser);


        }
    }
    private void changeQualifer(MovieUser movieUserObtein,MovieUser movieUser){
        if(movieUserObtein.getScore()==null && movieUser.getScore()!=null){
            this.iMovieRepository.sumQualifer(movieUserObtein.getMovie().getId());
        }
        if(movieUserObtein.getScore()!=null && movieUser.getScore()==null){
          this.iMovieRepository.lessQualider(movieUserObtein.getMovie().getId());
        }
    }
    private void validateUser(MovieUser movieUser){
        movieUser.validateScore();
    }
    private void changeScore(MovieUser movieUserObtein,MovieUser movieUser){
        if(movieUser.getScore()!=null ){
            if(movieUserObtein.getScore()!=null){
                if( !movieUserObtein.getScore().equals(movieUser.getScore())){
                    this.iMovieRepository.changeScore(movieUserObtein.getMovie().getId(),movieUserObtein.getScore(),movieUser.getScore());
                }
            }else{
                this.iMovieRepository.changeScore(movieUserObtein.getMovie().getId(),0L,movieUser.getScore());
            }
        }
    }
    private void changeVisualization(Boolean esClickVideo,MovieUser movieUser,MovieUser movieUserObtein){
        if(esClickVideo){
            Long currentNoViewViews = movieUserObtein.getNoViewViews();
            movieUser.setNoViewViews((currentNoViewViews == null ? 0 : currentNoViewViews) + 1);
            movieUser.setEsVista(true);
        }else{
            movieUser.setNoViewViews(movieUserObtein.getNoViewViews());
            movieUser.setEsVista(movieUserObtein.getEsVista());

        }

        if(!movieUserObtein.getEsVista() && movieUser.getEsVista()){
            this.iMovieRepository.sumView(movieUserObtein.getMovie().getId());
        }
    }

}
