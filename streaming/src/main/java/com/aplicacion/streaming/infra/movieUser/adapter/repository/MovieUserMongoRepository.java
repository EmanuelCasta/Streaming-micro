package com.aplicacion.streaming.infra.movieUser.adapter.repository;

import com.aplicacion.streaming.domain.model.MovieUser;
import com.aplicacion.streaming.domain.port.in.repository.IMovieUserRepository;
import com.aplicacion.streaming.infra.movieUser.builder.MovieUserBuilder;
import com.aplicacion.streaming.infra.movieUser.entity.MovieUserEntity;
import com.aplicacion.streaming.infra.movieUser.entity.MovieUserId;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public class MovieUserMongoRepository implements IMovieUserRepository {

    private IMovieUserMongoRepository iMovieUserMongoRepository;


    public MovieUserMongoRepository(IMovieUserMongoRepository iMovieUserMongoRepository) {
        this.iMovieUserMongoRepository = iMovieUserMongoRepository;
    }

    @Override
    public void createReview(MovieUser user) {
        this.iMovieUserMongoRepository.save(MovieUserBuilder.convertEntity(user));

    }

    @Override
    public MovieUser getMovieReview(MovieUser user) {
        MovieUserId movieUserId = MovieUserBuilder.convertEntity(user.getUser().getId(),user.getMovie().getId());
        Optional<MovieUserEntity> userEntity= this.iMovieUserMongoRepository.findById(movieUserId);
        return userEntity.map(MovieUserBuilder::convertModel).orElse(null);
    }


}
