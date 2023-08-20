package me.timur.touroperatorbpa.application.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.timur.touroperatorbpa.application.model.Application;
import me.timur.touroperatorbpa.application.model.ApplicationCreate;
import me.timur.touroperatorbpa.application.model.accommodation.AccommodationApplicationDto;
import me.timur.touroperatorbpa.application.model.transport.TransportApplicationCreateDto;
import me.timur.touroperatorbpa.application.model.transport.TransportApplicationDto;
import me.timur.touroperatorbpa.application.service.ApplicationService;
import me.timur.touroperatorbpa.domain.entity.User;
import me.timur.touroperatorbpa.model.PageableFilter;
import me.timur.touroperatorbpa.model.PageableList;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Temurbek Ismoilov on 03/08/23.
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class TransportApplicationService<T extends ApplicationCreate, R extends Application> implements ApplicationService<TransportApplicationCreateDto, TransportApplicationDto> {
    @Override
    public TransportApplicationDto create(TransportApplicationCreateDto transportApplicationCreateDto, User user) {
        return null;
    }

    @Override
    public List<TransportApplicationDto> update(TransportApplicationDto transportApplicationDto, User user) {
        return null;
    }

    @Override
    public void changeStatus(Long id) {

    }

    @Override
    public void cancelByGroupId(Long groupId) {

    }

    @Override
    public TransportApplicationDto get(Long id) {
        return null;
    }

    @Override
    public List<TransportApplicationDto> getByGroupId(Long groupId) {
        return null;
    }

    @Override
    public PageableList<AccommodationApplicationDto.AccommodationItem> getAllFiltered(PageableFilter filter) {
        return null;
    }
}
