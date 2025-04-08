package me.timur.touroperatorbpa.application.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.timur.touroperatorbpa.application.model.ApplicationDto;
import me.timur.touroperatorbpa.application.model.ApplicationCreate;
import me.timur.touroperatorbpa.application.model.accommodation.AccommodationApplicationDto;
import me.timur.touroperatorbpa.application.model.transport.TransportApplicationCreateDto;
import me.timur.touroperatorbpa.application.model.transport.TransportApplicationDto;
import me.timur.touroperatorbpa.application.service.ApplicationService;
import me.timur.touroperatorbpa.domain.entity.User;
import me.timur.touroperatorbpa.model.PageableFilter;
import me.timur.touroperatorbpa.model.PageableList;
import me.timur.touroperatorbpa.model.enums.ApplicationType;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Temurbek Ismoilov on 03/08/23.
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class TransportApplicationService<T extends ApplicationCreate, R extends ApplicationDto> implements ApplicationService<TransportApplicationCreateDto, TransportApplicationDto> {
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
    public TransportApplicationDto get(Long id, User user) {
        return null;
    }

    @Override
    public TransportApplicationDto getByGroupId(Long groupId, User user) {
        return null;
    }

    @Override
    public ApplicationType getType() {
        return ApplicationType.TRANSPORT;
    }

    @Override
    public PageableList<AccommodationApplicationDto.AccommodationItem> getAllFiltered(PageableFilter filter) {
        return null;
    }
}
