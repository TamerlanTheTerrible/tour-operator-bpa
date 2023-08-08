package me.timur.touroperatorbpa.group.service;

import me.timur.touroperatorbpa.domain.entity.User;
import me.timur.touroperatorbpa.model.PageableList;
import me.timur.touroperatorbpa.group.model.GroupCreateDto;
import me.timur.touroperatorbpa.group.model.GroupDto;
import me.timur.touroperatorbpa.group.model.GroupFilter;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Temurbek Ismoilov on 27/07/23.
 */

public interface GroupService {
    GroupDto create(GroupCreateDto groupCreateDto, User user);

    @Transactional
    GroupDto update(GroupDto groupDto);

    @Transactional
    void cancel(Long id);

    GroupDto get(Long id);

    List<GroupDto> getAllByOperatorId(Long id);

    PageableList<GroupDto> getAllByFiltered(GroupFilter filter);
}
