package me.timur.touroperatorbpa.group.service;

import me.timur.touroperatorbpa.domain.entity.User;
import me.timur.touroperatorbpa.model.PageableList;
import me.timur.touroperatorbpa.group.model.GroupCreateDto;
import me.timur.touroperatorbpa.group.model.GroupDto;
import me.timur.touroperatorbpa.group.model.GroupFilter;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Temurbek Ismoilov on 27/07/23.
 */

public interface GroupService {
    GroupDto create(GroupCreateDto groupCreateDto, User user);

    @Transactional
    GroupDto update(GroupDto groupDto, User user);

    @Transactional
    void cancel(Long id, User user);

    GroupDto get(Long id, User user);

    PageableList<GroupDto> getAllByFiltered(GroupFilter filter, User user);
}
