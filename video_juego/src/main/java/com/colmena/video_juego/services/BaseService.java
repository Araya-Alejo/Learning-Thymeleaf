package com.colmena.video_juego.services;

import java.util.List;

public interface BaseService<E> {

    List<E> findAll() throws Exception;
    E findById(long id) throws Exception;
    E save(E entity) throws Exception;
    E update(E entity, long id) throws Exception;
    boolean deleteById(long id) throws Exception;

}
