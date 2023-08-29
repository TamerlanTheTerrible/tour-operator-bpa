package me.timur.touroperatorbpa.application.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.timur.touroperatorbpa.application.model.ApplicationDto;
import me.timur.touroperatorbpa.application.model.ApplicationCreate;
import me.timur.touroperatorbpa.application.model.accommodation.AccommodationApplicationDto;
import me.timur.touroperatorbpa.application.model.transport.TransportApplicationCreateDto;
import me.timur.touroperatorbpa.application.model.transport.TransportApplicationDtoDto;
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
public class TransportApplicationService<T extends ApplicationCreate, R extends ApplicationDto> implements ApplicationService<TransportApplicationCreateDto, TransportApplicationDtoDto> {
    @Override
    public TransportApplicationDtoDto create(TransportApplicationCreateDto transportApplicationCreateDto, User user) {
        return null;
    }

    @Override
    public List<TransportApplicationDtoDto> update(TransportApplicationDtoDto transportApplicationDto, User user) {
        return null;
    }

    @Override
    public void changeStatus(Long id) {

    }

    @Override
    public void cancelByGroupId(Long groupId) {

    }

    @Override
    public TransportApplicationDtoDto get(Long id, User user) {
        return null;
    }

    @Override
    public List<TransportApplicationDtoDto> getByGroupId(Long groupId, User user) {
        return null;
    }

    @Override
    public PageableList<AccommodationApplicationDto.AccommodationItem> getAllFiltered(PageableFilter filter) {
        return null;
    }
}
