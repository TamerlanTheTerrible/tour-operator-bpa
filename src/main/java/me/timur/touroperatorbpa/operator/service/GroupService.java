package me.timur.touroperatorbpa.operator.service;

import me.timur.touroperatorbpa.model.group.GroupCreateDto;
import me.timur.touroperatorbpa.model.group.GroupDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Temurbek Ismoilov on 27/07/23.
 */

public interface GroupService {
    GroupDto create(GroupCreateDto groupCreateDto);

    @Transactional
    GroupDto update(GroupDto groupDto);

    @Transactional
    void cancel(Long id);

    GroupDto get(Long id);

    List<GroupDto> getAllByOperatorId(Long id);
}
